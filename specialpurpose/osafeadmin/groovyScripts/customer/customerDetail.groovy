package customer;

import java.util.LinkedList;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.ofbiz.base.util.UtilProperties;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.entity.util.EntityQuery;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.order.shoppingcart.ShoppingCartEvents;
import org.apache.ofbiz.party.party.PartyHelper;
import org.apache.ofbiz.entity.condition.*;
import org.apache.ofbiz.entity.GenericValue;

//Get the Cart and Prepare Size
shoppingCart = ShoppingCartEvents.getCartObject(request);
context.shoppingCart = shoppingCart;

partyId = StringUtils.trimToEmpty(parameters.partyId);
if(UtilValidate.isNotEmpty(partyId))
{
	context.partyId=partyId;
}

List<String> roleTypeIds = LinkedList.newInstance();
roleTypeIds.add("INTERNAL_ORGANIZATIO");

if (UtilValidate.isNotEmpty(partyId))
{
	party = EntityQuery.use(delegator).from("Party").where("partyId", partyId).queryOne();
	if (UtilValidate.isNotEmpty(party))
	{
        context.party=party;

        person = party.getRelatedOne("Person");
        context.person=person;
        
        partyGroup = party.getRelatedOne("PartyGroup");
        context.partyGroup = partyGroup;

		partyRoles = party.getRelated("ProductStoreRole");
        context.partyRoles=partyRoles;
        // set party product store name in context
        if (UtilValidate.isNotEmpty(partyRoles))
    	{
        	partyRoles = EntityUtil.filterByDate(partyRoles,true);
        	partyRole = EntityUtil.getFirst(partyRoles);
        	partyProductStore = partyRole.getRelatedOne("ProductStore");
            if (UtilValidate.isNotEmpty(partyProductStore))
        	{
                if (UtilValidate.isNotEmpty(partyProductStore.storeName))
            	{
                    productStoreName = partyProductStore.storeName;
            	}
                else
                {
                	productStoreName = partyProductStore.productStoreId;
                }
            	context.productStoreName = productStoreName;
        	}
    	}
        
        userLogins = party.getRelated("UserLogin");
        context.userLogins=userLogins;
		
		
		//Get PARTY BILLING,SHIPPING,PRIMARY EMAIL, TELEPHONE LOCATIONS
        partyContactMechPurpose = party.getRelated("PartyContactMechPurpose");
    	if (UtilValidate.isNotEmpty(partyContactMechPurpose))
    	{
	        partyContactMechPurpose = EntityUtil.filterByDate(partyContactMechPurpose,true);
	
	        shippingContactMechList = LinkedList.newInstance();
	        addrExpr= LinkedList.newInstance();
	        addrExpr.add(EntityCondition.makeCondition("contactMechPurposeTypeId", EntityOperator.EQUALS, "SHIPPING_LOCATION"));
	        addrExpr.add(EntityCondition.makeCondition("contactMechPurposeTypeId", EntityOperator.EQUALS, "GENERAL_LOCATION"));
	        addrExpr.add(EntityCondition.makeCondition("contactMechPurposeTypeId", EntityOperator.EQUALS, "BILLING_LOCATION"));
	        partyShippingLocations = EntityUtil.filterByOr(partyContactMechPurpose,addrExpr);
	        partyShippingLocations = EntityUtil.getFirst(partyShippingLocations).getRelated("PartyContactMech", null, null, false);
	        partyShippingLocations = EntityUtil.filterByDate(partyShippingLocations,true);
	        partyShippingLocations = EntityUtil.orderBy(partyShippingLocations, UtilMisc.toList("fromDate DESC"));
	        if (UtilValidate.isNotEmpty(partyShippingLocations)) 
	        {
	        	partyShippingLocation = EntityUtil.getFirst(partyShippingLocations);
	            context.shippingContactMechId = partyShippingLocation.contactMechId;
	            contactMechList = EntityUtil.getFirst(partyShippingLocations).getRelated("ContactMech",null, null, false);
	            for (GenericValue contactMech: contactMechList) 
	            {
	                if (!shippingContactMechList.contains(contactMech))
	                {
	                	shippingContactMechList.add(contactMech)
	                }

	            }

	        }
	
			context.resultList= shippingContactMechList;
			
	        partyPurposeEmails = EntityUtil.filterByAnd(partyContactMechPurpose, UtilMisc.toMap("contactMechPurposeTypeId", "PRIMARY_EMAIL"));
	        partyPurposeEmails = EntityUtil.getFirst(partyPurposeEmails).getRelated("PartyContactMech", null, null, false);
	        partyPurposeEmails = EntityUtil.filterByDate(partyPurposeEmails,true);
	        partyPurposeEmails = EntityUtil.orderBy(partyPurposeEmails, UtilMisc.toList("fromDate DESC"));
	        if (UtilValidate.isNotEmpty(partyPurposeEmails)) 
	        {
	        	partyPurposeEmail = EntityUtil.getFirst(partyPurposeEmails);
	            contactMech = partyPurposeEmail.getRelatedOne("ContactMech");
	            context.userEmailContactMech = contactMech;
	            context.userEmailAddress = contactMech.infoString;
	            context.userEmailAllowSolicitation= partyPurposeEmail.allowSolicitation;
	            userEmailContactMechList= EntityUtil.getFirst(partyPurposeEmails).getRelated("ContactMech",null, null, false);
	            context.userEmailContactMechList = userEmailContactMechList;
	            
	        }
	        
	        partyPurposeHomePhones = EntityUtil.filterByAnd(partyContactMechPurpose, UtilMisc.toMap("contactMechPurposeTypeId", "PHONE_HOME"));
	        if(UtilValidate.isNotEmpty(partyPurposeHomePhones)){
		        partyPurposeHomePhones = EntityUtil.getFirst(partyPurposeHomePhones).getRelated("PartyContactMech",null,null,false);
		        partyPurposeHomePhones = EntityUtil.filterByDate(partyPurposeHomePhones,true);
		        partyPurposeHomePhones = EntityUtil.orderBy(partyPurposeHomePhones, UtilMisc.toList("fromDate DESC"));
		        if (UtilValidate.isNotEmpty(partyPurposeHomePhones)){
		        	partyPurposePhone = EntityUtil.getFirst(partyPurposeHomePhones);
		        	telecomNumber = partyPurposePhone.getRelatedOne("TelecomNumber");
		            context.phoneHomeTelecomNumber =telecomNumber;
		            context.phoneHomeAreaCode =telecomNumber.areaCode;
		            context.phoneHomeContactNumber =telecomNumber.contactNumber;
		            context.partyPurposeHomePhones =partyPurposeHomePhones;
		        }
	        }
	        
	        partyPurposeWorkPhones = EntityUtil.filterByAnd(partyContactMechPurpose, UtilMisc.toMap("contactMechPurposeTypeId", "PHONE_WORK"));
	        if(UtilValidate.isNotEmpty(partyPurposeWorkPhones)){
		        partyPurposeWorkPhones = EntityUtil.getFirst(partyPurposeWorkPhones).getRelated("PartyContactMech", null, null, false);
		        partyPurposeWorkPhones = EntityUtil.filterByDate(partyPurposeWorkPhones,true);
		        partyPurposeWorkPhones = EntityUtil.orderBy(partyPurposeWorkPhones, UtilMisc.toList("fromDate DESC"));
		        if (UtilValidate.isNotEmpty(partyPurposeWorkPhones)){
		        	partyPurposePhone = EntityUtil.getFirst(partyPurposeWorkPhones);
		        	telecomNumber = partyPurposePhone.getRelatedOne("TelecomNumber");
		            context.partyPurposeWorkPhone =partyPurposePhone;
		            context.phoneWorkTelecomNumber =telecomNumber;
		            context.phoneWorkAreaCode =telecomNumber.areaCode;
		            context.phoneWorkContactNumber =telecomNumber.contactNumber;
		            context.partyPurposeWorkPhones =partyPurposeWorkPhones;
		        }
	        }
	
	        partyPurposeMobilePhones = EntityUtil.filterByAnd(partyContactMechPurpose, UtilMisc.toMap("contactMechPurposeTypeId", "PHONE_MOBILE"));
	        if(UtilValidate.isNotEmpty(partyPurposeMobilePhones)){
		        partyPurposeMobilePhones = EntityUtil.getFirst(partyPurposeMobilePhones).getRelated("PartyContactMech", null, null, false);
		        partyPurposeMobilePhones = EntityUtil.filterByDate(partyPurposeMobilePhones,true);
		        partyPurposeMobilePhones = EntityUtil.orderBy(partyPurposeMobilePhones, UtilMisc.toList("fromDate DESC"));
		        if (UtilValidate.isNotEmpty(partyPurposeMobilePhones)){
		        	partyPurposePhone = EntityUtil.getFirst(partyPurposeMobilePhones);
		        	telecomNumber = partyPurposePhone.getRelatedOne("TelecomNumber");
		            context.phoneMobileTelecomNumber =telecomNumber;
		            context.phoneMobileAreaCode =telecomNumber.areaCode;
		            context.phoneMobileContactNumber =telecomNumber.contactNumber;
		            context.partyPurposeMobilePhones =partyPurposeMobilePhones;
		        }
	        }
    	}
		//Get PARTY ATTRIBUTES
		partyAttributes = party.getRelated("PartyAttribute");
		context.customerAttributes = partyAttributes;

	    //IS_DOWNLOADED
	    partyAttrs = EntityUtil.filterByAnd(partyAttributes, UtilMisc.toMap("attrName", "IS_DOWNLOADED"));
	    if (UtilValidate.isNotEmpty(partyAttrs))
	    {
	    	partyAttr = EntityUtil.getFirst(partyAttrs);
	    	context.IS_DOWNLOADED = partyAttr.attrValue;
	    }
	    //PARTY_EMAIL_PREFERENCE
	    partyAttrs = EntityUtil.filterByAnd(partyAttributes, UtilMisc.toMap("attrName", "PARTY_EMAIL_PREFERENCE"));
	    if (UtilValidate.isNotEmpty(partyAttrs))
	    {
	    	partyAttr = EntityUtil.getFirst(partyAttrs);
	    	context.PARTY_EMAIL_PREFERENCE = partyAttr.attrValue;
	    }
	    //PARTY_EMAIL_PREFERENCE
	    partyAttrs = EntityUtil.filterByAnd(partyAttributes, UtilMisc.toMap("attrName", "PARTY_TEXT_PREFERENCE"));
	    if (UtilValidate.isNotEmpty(partyAttrs))
	    {
	    	partyAttr = EntityUtil.getFirst(partyAttrs);
	    	context.userTextPreference = partyAttr.attrValue;
	    }
		
		//PARTY FACEBOOK_USER
		partyAttrs = EntityUtil.filterByAnd(partyAttributes, UtilMisc.toMap("attrName", "FACEBOOK_USER"));
		if (UtilValidate.isNotEmpty(partyAttrs))
		{
			partyAttr = EntityUtil.getFirst(partyAttrs);
			context.userFbUser = partyAttr.attrValue;
		}
		
		
		partyRelationships = party.getRelated("ToPartyRelationship", );
		partyRelationships = EntityUtil.filterByCondition(partyRelationships, EntityCondition.makeCondition("roleTypeIdFrom", EntityOperator.IN, roleTypeIds));
		partyRelationship = EntityUtil.getFirst(partyRelationships);
	    if (UtilValidate.isNotEmpty(partyRelationship))
	    {
	    	context.partyOrganization = partyRelationship;
	    }
	    
	}
	
}

Map<String, Object> svcCtx = HashMap.newInstance();
userLogin = session.getAttribute("userLogin");
svcCtx.put("userLogin", userLogin);

svcCtx.put("lookupFlag", "Y");
svcCtx.put("showAll", "N");
svcCtx.put("partyTypeId", "ANY");
svcCtx.put("statusId", "ANY");
svcCtx.put("extInfo", "N");
svcCtx.put("partyTypeId", "PARTY_GROUP");

svcCtx.put("productStoreId", context.productStoreId);
if(UtilValidate.isNotEmpty(roleTypeIds))
{
	svcCtx.put("roleTypeIds", roleTypeIds);
	context.roleTypeIds = roleTypeIds;
}

Map<String, Object> svcRes;

List<GenericValue> partyList = LinkedList.newInstance();

svcRes = dispatcher.runSync("findParty", svcCtx);

context.organizationList = svcRes.get("completePartyList");

