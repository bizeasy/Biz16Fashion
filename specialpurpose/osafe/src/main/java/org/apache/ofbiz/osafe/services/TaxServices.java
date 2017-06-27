/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package org.apache.ofbiz.osafe.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;



import org.apache.ofbiz.accounting.tax.TaxAuthorityServices;
import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.GeneralException;
import org.apache.ofbiz.base.util.StringUtil;
import org.apache.ofbiz.base.util.UtilDateTime;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.base.util.UtilNumber;
import org.apache.ofbiz.base.util.UtilProperties;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.common.geo.GeoWorker;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.condition.EntityCondition;
import org.apache.ofbiz.entity.condition.EntityConditionList;
import org.apache.ofbiz.entity.condition.EntityOperator;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.entity.util.EntityQuery;
import org.apache.ofbiz.order.order.OrderReadHelper;
import org.apache.ofbiz.order.shoppingcart.ShoppingCart;
import org.apache.ofbiz.order.shoppingcart.ShoppingCartItem;
import org.apache.ofbiz.party.contact.ContactMechWorker;
import org.apache.ofbiz.product.product.ProductWorker;
import org.apache.ofbiz.security.Security;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.service.ModelService;
import org.apache.ofbiz.service.ServiceUtil;

/**
 * Services for Tax maintenance
 */
public class TaxServices {

    public final static String module = TaxServices.class.getName();
    public static final BigDecimal PERCENT_SCALE = new BigDecimal("100.000");
    public static int salestaxFinalDecimals = UtilNumber.getBigDecimalScale("salestax.final.decimals");
    public static int salestaxCalcDecimals = UtilNumber.getBigDecimalScale("salestax.calc.decimals");
    public static int salestaxRounding = UtilNumber.getBigDecimalRoundingMode("salestax.rounding");


    public static Map taxCalculations(DispatchContext dctx, Map context) 
    {
    	Delegator delegator = dctx.getDelegator();
        String productStoreId = (String) context.get("productStoreId");
        String payToPartyId = (String) context.get("payToPartyId");
        String billToPartyId = (String) context.get("billToPartyId");
        List itemProductList = (List) context.get("itemProductList");
        List itemAmountList = (List) context.get("itemAmountList");
        List itemPriceList = (List) context.get("itemPriceList");
        List itemShippingList = (List) context.get("itemShippingList");
        BigDecimal orderShippingAmount = (BigDecimal) context.get("orderShippingAmount");
        BigDecimal orderPromotionsAmount = (BigDecimal) context.get("orderPromotionsAmount");
        BigDecimal orderLoyaltyAmount = (BigDecimal) context.get("orderLoyaltyAmount");
        GenericValue shippingAddress = (GenericValue) context.get("shippingAddress");
        ShoppingCart cart = (ShoppingCart) context.get("shoppingCart");
        Map result = ServiceUtil.returnSuccess();
        List orderAdjustments = new LinkedList<>();
        List itemAdjustments = new LinkedList<>();
      
        // without knowing the TaxAuthority parties, just find all TaxAuthories for the set of IDs...
        Set taxAuthoritySet = new HashSet<>();
        GenericValue productStore = null;
        // Check value productStore *** New
        if (UtilValidate.isNotEmpty(productStoreId)) 
        {
            try 
            {
                productStore = EntityQuery.use(delegator).from("ProductStore").where("productStoreId", productStoreId).queryOne();
                if (UtilValidate.isEmpty(productStore) && UtilValidate.isEmpty(payToPartyId)) 
                {
                	String errMsg="Could not find payToPartyId [" + payToPartyId + "] or ProductStore [" + productStoreId + "] for tax calculation";
                    Debug.logError(errMsg,module);
                    return ServiceUtil.returnError(errMsg);
                }
                Set geoIdSet = new HashSet<>();
                if (shippingAddress != null) 
                {
                    if (UtilValidate.isNotEmpty(shippingAddress.getString("countryGeoId"))) 
                    {
                        geoIdSet.add(shippingAddress.getString("countryGeoId"));
                    }
                    if (UtilValidate.isNotEmpty(shippingAddress.getString("stateProvinceGeoId"))) 
                    {
                        geoIdSet.add(shippingAddress.getString("stateProvinceGeoId"));
                    }
                    if (UtilValidate.isNotEmpty(shippingAddress.getString("countyGeoId"))) 
                    {
                        geoIdSet.add(shippingAddress.getString("countyGeoId"));
                    }
                    String postalCodeGeoId = ContactMechWorker.getPostalAddressPostalCodeGeoId(shippingAddress, delegator);
                    if (UtilValidate.isNotEmpty(postalCodeGeoId)) 
                    {
                        geoIdSet.add(postalCodeGeoId);
                    }
                } 
                else 
                {
                    Debug.logWarning("shippingAddress was null, adding nothing to taxAuthoritySet", module);
                }
                
                //Debug.logInfo("Tax calc geoIdSet before expand:" + geoIdSet + "; this is for shippingAddress=" + shippingAddress, module);
                // get the most granular, or all available, geoIds and then find parents by GeoAssoc with geoAssocTypeId="REGIONS" and geoIdTo=<granular geoId> and find the GeoAssoc.geoId
                geoIdSet = expandGeoRegionDeep(geoIdSet, delegator);
                //Debug.logInfo("Tax calc geoIdSet after expand:" + geoIdSet, module);

                List taxAuthorityRawList = delegator.findList("TaxAuthority", EntityCondition.makeCondition("taxAuthGeoId", EntityOperator.IN, geoIdSet), null, null, null, true);
                taxAuthoritySet.addAll(taxAuthorityRawList);

                Timestamp nowTimestamp = UtilDateTime.nowTimestamp();


                // store expr
                EntityCondition storeCond = null;
                if (UtilValidate.isNotEmpty(productStore)) 
                {
                    storeCond = EntityCondition.makeCondition(EntityCondition.makeCondition("productStoreId", EntityOperator.EQUALS, productStore.get("productStoreId")),EntityOperator.OR,EntityCondition.makeCondition("productStoreId", EntityOperator.EQUALS, null));
                } 
                else 
                {
                    storeCond = EntityCondition.makeCondition("productStoreId", EntityOperator.EQUALS, null);
                }

                // build the TaxAuthority expressions (taxAuthGeoId, taxAuthPartyId)
                List taxAuthCondOrList = new LinkedList<>();
                // start with the _NA_ TaxAuthority...
                taxAuthCondOrList.add(EntityCondition.makeCondition(EntityCondition.makeCondition("taxAuthPartyId", EntityOperator.EQUALS, "_NA_"),EntityOperator.AND,EntityCondition.makeCondition("taxAuthGeoId", EntityOperator.EQUALS, "_NA_")));
                Iterator taxAuthorityIter = taxAuthoritySet.iterator();
                while (taxAuthorityIter.hasNext()) 
                {
                    GenericValue taxAuthority = (GenericValue) taxAuthorityIter.next();
                    EntityCondition taxAuthCond = EntityCondition.makeCondition(EntityCondition.makeCondition("taxAuthPartyId", EntityOperator.EQUALS, taxAuthority.getString("taxAuthPartyId")),EntityOperator.AND,EntityCondition.makeCondition("taxAuthGeoId", EntityOperator.EQUALS, taxAuthority.getString("taxAuthGeoId")));
                    taxAuthCondOrList.add(taxAuthCond);
                }
                EntityCondition taxAuthoritiesCond = EntityCondition.makeCondition(taxAuthCondOrList, EntityOperator.OR);
                      
                List mainExprs = UtilMisc.toList(storeCond, taxAuthoritiesCond);
                EntityCondition mainCondition = EntityCondition.makeCondition(mainExprs, EntityOperator.AND);

                // finally ready... do the rate query
                List taxAuthorityRateProductList = delegator.findList("TaxAuthorityRateProduct", mainCondition, null, null, null, false);
                taxAuthorityRateProductList = EntityUtil.filterByDate(taxAuthorityRateProductList, true);

                if (taxAuthorityRateProductList.size() == 0) 
                {
                    Debug.logWarning("In TaxAuthority Product Rate no records were found for condition:" + mainCondition.toString(), module);
                    //return result;
                }
                else
                {
                	Iterator flIt = taxAuthorityRateProductList.iterator();
                    while (flIt.hasNext()) 
                    {
                        GenericValue taxAuthorityRateProduct = (GenericValue) flIt.next();
                        BigDecimal taxRate = taxAuthorityRateProduct.get("taxPercentage") != null ? taxAuthorityRateProduct.getBigDecimal("taxPercentage") : BigDecimal.ZERO;
                        BigDecimal taxable = BigDecimal.ZERO;

                        if (orderLoyaltyAmount != null) 
                        {
                            taxable = taxable.add(orderLoyaltyAmount);
                        }

                        if (taxable.compareTo(BigDecimal.ZERO) == 0) 
                        {
                            // this should make it less confusing if the taxable flag on the product is not Y/true, and there is no shipping and such
                            continue;
                        }

                        // taxRate is in percentage, so needs to be divided by 100
                        BigDecimal taxAmount = (taxable.multiply(taxRate)).divide(PERCENT_SCALE, salestaxCalcDecimals, salestaxRounding);

                        String taxAuthGeoId = taxAuthorityRateProduct.getString("taxAuthGeoId");
                        String taxAuthPartyId = taxAuthorityRateProduct.getString("taxAuthPartyId");

                        // get glAccountId from TaxAuthorityGlAccount entity using the payToPartyId as the organizationPartyId
                        GenericValue taxAuthorityGlAccount = EntityQuery.use(delegator).from("TaxAuthorityGlAccount").where("taxAuthPartyId", taxAuthPartyId, "taxAuthGeoId", taxAuthGeoId, "organizationPartyId", payToPartyId).queryOne();
                        String taxAuthGlAccountId = null;
                        if (taxAuthorityGlAccount != null) 
                        {
                            taxAuthGlAccountId = taxAuthorityGlAccount.getString("glAccountId");
                        } 

                        GenericValue adjValue = delegator.makeValue("OrderAdjustment");
                        adjValue.set("taxAuthorityRateSeqId", taxAuthorityRateProduct.getString("taxAuthorityRateSeqId"));
                        adjValue.set("amount", taxAmount);
                        adjValue.set("sourcePercentage", taxRate);
                        adjValue.set("orderAdjustmentTypeId", "SALES_TAX");
                        // the primary Geo should be the main jurisdiction that the tax is for, and the secondary would just be to define a parent or wrapping jurisdiction of the primary
                        adjValue.set("primaryGeoId", taxAuthGeoId);
                        adjValue.set("comments", taxAuthorityRateProduct.getString("description"));
                        if (taxAuthPartyId != null)
                        {
                        	adjValue.set("taxAuthPartyId", taxAuthPartyId);
                        }
                        if (taxAuthGlAccountId != null)
                        { 
                        	adjValue.set("overrideGlAccountId", taxAuthGlAccountId);
                        }
                        if (taxAuthGeoId != null)
                        {
                        	adjValue.set("taxAuthGeoId", taxAuthGeoId);
                        }

                        orderAdjustments.add(adjValue);
                    }
                }
            } 
            catch (GenericEntityException e) 
            {
                String errMsg = "Data error getting tax settings: " + e.toString();
                Debug.logError(e, errMsg, module);
                return ServiceUtil.returnError(errMsg);
            }

        }
        else
        {
            String errMsg = "Data error getting tax settings: ";
            return ServiceUtil.returnError(errMsg);
        }        
        result.put("orderAdjustments", orderAdjustments);
        result.put("itemAdjustments", itemAdjustments);
        
        return result;
    }
    
    public static Set<String> expandGeoRegionDeep(Set<String> geoIdSet, Delegator delegator) throws GenericEntityException {
        if (UtilValidate.isEmpty(geoIdSet)) {
            return geoIdSet;
        }
        Set<String> geoIdSetTemp = new HashSet<String>();
        for (String curGeoId: geoIdSet) {
            List<GenericValue> geoAssocList = EntityQuery.use(delegator).from("GeoAssoc").where("geoIdTo", curGeoId, "geoAssocTypeId", "REGIONS").cache().queryList();
            for (GenericValue geoAssoc: geoAssocList) {
                geoIdSetTemp.add(geoAssoc.getString("geoId"));
            }
        }
        geoIdSetTemp = expandGeoRegionDeep(geoIdSetTemp, delegator);
        Set<String> geoIdSetNew = new HashSet<String>();
        geoIdSetNew.addAll(geoIdSet);
        geoIdSetNew.addAll(geoIdSetTemp);
        return geoIdSetNew;
    }
}
