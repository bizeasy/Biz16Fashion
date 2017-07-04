package common;

import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.base.util.UtilValidate;
import java.util.LinkedList;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.party.contact.ContactHelper;
import org.apache.ofbiz.product.store.ProductStoreWorker;
import org.apache.ofbiz.base.util.UtilDateTime;

cart = session.getAttribute("shoppingCart");
party = userLogin.getRelatedOne("Party",true);
partyId = party.partyId;
context.party = party;
partyProfileDefault = null;

shippingContactMechList = LinkedList.newInstance();
billingContactMechList = LinkedList.newInstance();
addressContactMechList = LinkedList.newInstance();
if (UtilValidate.isNotEmpty(party))
{
	
	partyLocations = party.getRelated("PartyContactMech",null,null,true);
	partyLocations = EntityUtil.filterByDate(partyLocations,UtilDateTime.getDayEnd(UtilDateTime.nowTimestamp()),"fromDate","thruDate",true);
	partyLocations = EntityUtil.getFirst(partyLocations).getRelated("PartyContactMechPurpose", null, null, true);
	partyLocations = EntityUtil.filterByDate(partyLocations,UtilDateTime.getDayEnd(UtilDateTime.nowTimestamp()),"fromDate","thruDate",true);
	partyLocations = EntityUtil.orderBy(partyLocations, UtilMisc.toList("fromDate DESC"));
	

	// This should return the current billing address
	partyBillingLocations = EntityUtil.filterByAnd(partyLocations, UtilMisc.toMap("contactMechPurposeTypeId", "BILLING_LOCATION"));
    if (UtilValidate.isNotEmpty(partyBillingLocations)) 
    {
        billingContactMechList = EntityUtil.getFirst(partyBillingLocations).getRelated("ContactMech",null, null, true);
        addressContactMechList.addAll(billingContactMechList);
        billingAddressContactMech = EntityUtil.getFirst(billingContactMechList);
        if (UtilValidate.isNotEmpty(billingAddressContactMech)) 
        {
            billingPostalAddress = delegator.findOne("PostalAddress", [contactMechId : billingAddressContactMech.contactMechId], true);
            context.BILLINGPostalAddress = billingPostalAddress;
            context.firstBillingContactMechId=billingAddressContactMech.contactMechId;
        }
        
    }
	
    partyShippingLocations = EntityUtil.filterByAnd(partyLocations, UtilMisc.toMap("contactMechPurposeTypeId", "SHIPPING_LOCATION"));
    if (UtilValidate.isNotEmpty(partyShippingLocations)) 
    {
        shippingContactMechList=EntityUtil.getFirst(partyShippingLocations).getRelated("ContactMech",null, null, true);
        addressContactMechList.addAll(shippingContactMechList);
        shippingAddressContactMech = EntityUtil.getFirst(shippingContactMechList);
        if (UtilValidate.isNotEmpty(shippingAddressContactMech)) 
        {
            shippingPostalAddress = delegator.findOne("PostalAddress", [contactMechId : shippingAddressContactMech.contactMechId], true);
            context.SHIPPINGPostalAddress = shippingPostalAddress;
        }
    }
	productStoreId = ProductStoreWorker.getProductStoreId(request);
    partyProfileDefault = delegator.findOne("PartyProfileDefault", UtilMisc.toMap("partyId", party.partyId, "productStoreId", productStoreId), true);
    
}



context.shoppingCart = cart;
context.addressContactMechList = addressContactMechList;
context.partyProfileDefault = partyProfileDefault;

