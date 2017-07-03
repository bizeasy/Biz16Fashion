package common;

import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.product.store.ProductStoreWorker;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.product.store.ProductStoreWorker;
import org.apache.ofbiz.entity.util.EntityQuery;

partyId = null;
userLogin = context.userLogin;
partyProfileDefault = null;

productStore = ProductStoreWorker.getProductStore(request);
context.productStoreId = productStore.productStoreId;
context.productStore = productStore;

context.createAllowPassword = "Y".equals(productStore.allowPassword);
context.getUsername = !"Y".equals(productStore.usePrimaryEmailUsername);
context.userLoginId="";

previousParams = parameters._PREVIOUS_PARAMS_;
if (UtilValidate.isNotEmpty(previousParams)) 
{
    previousParams = "?" + previousParams;
} else 
{
    previousParams = "";
}
context.previousParams = previousParams;

if (UtilValidate.isNotEmpty(userLogin)) 
{
    partyId = userLogin.partyId;
    context.userLoginId=userLogin.userLoginId;
}

if (UtilValidate.isNotEmpty(partyId)) 
{

    //party = EntityQuery.use(delegator).from("Party").where([partyId : partyId]).cache().queryOne();;
    party = EntityQuery.use(delegator).from("Party").where([partyId : partyId]).cache().queryOne();
    if (UtilValidate.isNotEmpty(party)) 
    {
        context.party = party;
        context.partyId = partyId;
        context.person = party.getRelatedOne("Person",true);

		productStoreId = ProductStoreWorker.getProductStoreId(request);
	    partyProfileDefault = delegator.findOne("PartyProfileDefault", UtilMisc.toMap("partyId", party.partyId, "productStoreId", productStoreId), true);

        
        partyContactMechPurpose = party.getRelated("PartyContactMechPurpose",null,null,true);
        partyContactMechPurpose = EntityUtil.filterByDate(partyContactMechPurpose,true);

        partyBillingLocations = EntityUtil.filterByAnd(partyContactMechPurpose, UtilMisc.toMap("contactMechPurposeTypeId", "BILLING_LOCATION"));
        partyBillingLocations = EntityUtil.getRelated("PartyContactMech", partyBillingLocations, null, true);
        partyBillingLocations = EntityUtil.filterByDate(partyBillingLocations,true);
        partyBillingLocations = EntityUtil.orderBy(partyBillingLocations, UtilMisc.toList("fromDate DESC"));
        if (UtilValidate.isNotEmpty(partyBillingLocations)) 
        {
        	partyBillingLocation = EntityUtil.getFirst(partyBillingLocations);
        	billingPostalAddress = partyBillingLocation.getRelatedOne("PostalAddress",true);
            billingContactMechList = EntityUtil.getRelated("ContactMech",partyBillingLocations, null, false);

            context.BILLINGPostalAddress = billingPostalAddress;
            context.billingContactMechId = billingPostalAddress.contactMechId;
            context.BILLINGContactMechList = billingContactMechList;
			context.PERSONALPostalAddress = billingPostalAddress;
			context.personalContactMechId = billingPostalAddress.contactMechId;
			context.PERSONALContactMechList = billingContactMechList;
        }
        
        partyShippingLocations = EntityUtil.filterByAnd(partyContactMechPurpose, UtilMisc.toMap("contactMechPurposeTypeId", "SHIPPING_LOCATION"));
        partyShippingLocations = EntityUtil.getRelated("PartyContactMech", partyShippingLocations, null, true);
        partyShippingLocations = EntityUtil.filterByDate(partyShippingLocations,true);
        partyShippingLocations = EntityUtil.orderBy(partyShippingLocations, UtilMisc.toList("fromDate DESC"));
        if (UtilValidate.isNotEmpty(partyShippingLocations)) 
        {
			tempPartyShippingLocations = partyShippingLocations;
			if (UtilValidate.isNotEmpty(partyProfileDefault) && UtilValidate.isNotEmpty(partyProfileDefault.defaultShipAddr))
			{
				tempPartyShippingLocations = EntityUtil.filterByAnd(partyShippingLocations, UtilMisc.toMap("contactMechId", partyProfileDefault.defaultShipAddr));
				if (UtilValidate.isEmpty(tempPartyShippingLocations))
				{
					tempPartyShippingLocations = partyShippingLocations;
				}
			}
            partyShippingLocation = EntityUtil.getFirst(tempPartyShippingLocations);
            shippingPostalAddress = partyShippingLocation.getRelatedOne("PostalAddress",true);
            shippingContactMechList=EntityUtil.getRelated("ContactMech",partyShippingLocations, null, false);

            context.SHIPPINGPostalAddress = shippingPostalAddress;
            context.SHIPPINGContactMechList = shippingContactMechList;
        }
        
        partyPurposeEmails = EntityUtil.filterByAnd(partyContactMechPurpose, UtilMisc.toMap("contactMechPurposeTypeId", "PRIMARY_EMAIL"));
        partyPurposeEmails = EntityUtil.getRelated("PartyContactMech", partyPurposeEmails, null, true);
        partyPurposeEmails = EntityUtil.filterByDate(partyPurposeEmails,true);
        partyPurposeEmails = EntityUtil.orderBy(partyPurposeEmails, UtilMisc.toList("fromDate DESC"));
        if (UtilValidate.isNotEmpty(partyPurposeEmails)) 
        {
        	partyPurposeEmail = EntityUtil.getFirst(partyPurposeEmails);
            contactMech = partyPurposeEmail.getRelatedOne("ContactMech",true);
            userEmailContactMechList= EntityUtil.getRelated("ContactMech",partyPurposeEmails, null, false);

            context.userEmailContactMech = contactMech;
            context.userEmailAddress = contactMech.infoString;
            context.userEmailContactMechList = userEmailContactMechList;
            context.userEmailAllowSolicitation= partyPurposeEmail.allowSolicitation;
            
        }
    }
}

shoppingCart = session.getAttribute("shoppingCart");
if (UtilValidate.isNotEmpty(shoppingCart))
{
    isSameAsBilling = shoppingCart.getAttribute("isSameAsBilling");
    if (UtilValidate.isNotEmpty(isSameAsBilling))
    {
        context.isSameAsBilling = isSameAsBilling;
    }
	shippingApplies = shoppingCart.shippingApplies();
	context.shippingApplies = shippingApplies;
	context.shoppingCart = shoppingCart;
}
context.deliveryOption = shoppingCart.getOrderAttribute("DELIVERY_OPTION");
context.partyProfileDefault = partyProfileDefault;
