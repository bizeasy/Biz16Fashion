import org.apache.ofbiz.order.order.OrderReadHelper;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.base.util.*;
import java.lang.*;
import java.text.NumberFormat;
import org.apache.ofbiz.webapp.taglib.*;
import org.apache.ofbiz.webapp.stats.VisitHandler;
import org.apache.ofbiz.product.catalog.*;
import org.apache.ofbiz.product.category.*;
import org.apache.ofbiz.product.store.*;
import java.util.HashMap;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.product.product.ProductContentWrapper;
import org.apache.ofbiz.product.category.CategoryContentWrapper;
import org.apache.ofbiz.product.product.ProductWorker;
import org.apache.ofbiz.order.shoppingcart.ShoppingCartEvents;
import org.apache.ofbiz.entity.*;
import org.apache.ofbiz.entity.condition.*;

googleTransMap = [:];
transItemList = [];
    if (UtilValidate.isNotEmpty(context.orderHeader)) 
    {
        orderId = orderHeader.orderId;
        googleTransMap.orderId = orderId;
        googleTransMap.storeName = productStore.storeName;
        orderReadHelper = OrderReadHelper.getHelper(orderHeader);
        orderItems = orderReadHelper.getOrderItems();
        orderAdjustments = orderReadHelper.getAdjustments();
        orderSubTotal = orderReadHelper.getOrderItemsSubTotal();
        otherAdjAmount = orderReadHelper.calcOrderAdjustments(orderAdjustments, orderSubTotal, true, false, false);
        shippingAmount = orderReadHelper.getAllOrderItemsAdjustmentsTotal(orderItems, orderAdjustments, false, false, true);
        googleTransMap.shippingAmount = shippingAmount.add(orderReadHelper.calcOrderAdjustments(orderAdjustments, orderSubTotal, false, false, true));
        googleTransMap.taxAmount = orderReadHelper.getOrderTaxByTaxAuthGeoAndParty(orderAdjustments).taxGrandTotal;
        googleTransMap.grandTotal = orderReadHelper.getOrderGrandTotal(orderItems, orderAdjustments);
        googleTransMap.currencyUom = orderReadHelper.getCurrency();
        shippingLocations = orderReadHelper.getShippingLocations();
        productPromoUse =orderReadHelper.getProductPromoUse();
        if (UtilValidate.isNotEmpty(productPromoUse)) 
        {
            productPromo = EntityUtil.getFirst(productPromoUse);
            googleTransMap.productPromoCode = productPromo.productPromoCodeId;
        }
         
        if (UtilValidate.isNotEmpty(shippingLocations)) 
        {
            shipLocal = EntityUtil.getFirst(shippingLocations);
            googleTransMap.shipCity = shipLocal.city;
            googleTransMap.shipState = shipLocal.stateProvinceGeoId;
            googleTransMap.shipCountry = shipLocal.countryGeoId;
        
        }
        if (orderItems)
        {
            saleCategoryParts=new StringBuffer();
            orderItems.each { orderItem ->
                itemProduct = orderItem.getRelatedOne("Product",true);
                productCategoryId = "";
                categoryName = "";
                transItemMap = [:];
                categoryLookupProduct = itemProduct;
                virtualProductId = ProductWorker.getVariantVirtualId(categoryLookupProduct);
                if (UtilValidate.isNotEmpty(virtualProductId)) 
                {
                    categoryLookupProduct = delegator.findOne("Product", [productId : virtualProductId], true);
                }
                
                if (UtilValidate.isNotEmpty(categoryLookupProduct))
                {
        	        productCategoryMemberList = itemProduct.getRelatedCache("ProductCategoryMember");
                    productCategoryMemberList = EntityUtil.filterByDate(productCategoryMemberList,true);
            	    productCategoryMemberList = EntityUtil.orderBy(productCategoryMemberList,UtilMisc.toList("sequenceNum"));
                    if (UtilValidate.isNotEmpty(productCategoryMemberList))
                    {
        	            productCategoryMember = EntityUtil.getFirst(productCategoryMemberList);
                        productCategory = productCategoryMember.getRelatedOne("ProductCategory",true);
                        categoryName = CategoryContentWrapper.getProductCategoryContentAsText(productCategory, "CATEGORY_NAME", locale, dispatcher);
                        transItemMap.categoryName;
                    }
                }
                transItemMap.productId = categoryLookupProduct.productId;
                transItemMap.skuCode = itemProduct.internalName;
                transItemMap.itemDescription = StringUtil.wrapString(orderItem.itemDescription);
                transItemMap.unitPrice = orderItem.unitPrice;
                transItemMap.quantity = orderItem.quantity;
                transItemMap.orderId = orderItem.orderId;
                if (UtilValidate.isNotEmpty(categoryName))
                {
                    saleCategoryParts.append(categoryName +":" + orderItem.unitPrice + "|");
                }
                     
                transItemList.add(transItemMap);
            }
            if (UtilValidate.isNotEmpty(saleCategoryParts))
            {
                saleCategoryParts.setLength(saleCategoryParts.length() -1);
                googleTransMap.saleCategoryParts = saleCategoryParts.toString();
                
            }
            
        }
    }
context.googleTransMap = googleTransMap;
context.transItemList = transItemList;
