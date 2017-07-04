package order;

import org.apache.commons.lang.StringUtils;
import org.apache.ofbiz.base.util.UtilProperties;
import org.apache.ofbiz.order.order.OrderReadHelper;
import org.apache.ofbiz.party.contact.ContactHelper;
import org.apache.ofbiz.entity.condition.EntityCondition;
import org.apache.ofbiz.entity.condition.EntityOperator;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.entity.util.EntityQuery;
import org.apache.ofbiz.party.contact.ContactMechWorker;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.base.util.string.FlexibleStringExpander;
import org.apache.ofbiz.entity.GenericValue;
import java.util.LinkedList;
import java.util.HashMap;
import java.math.BigDecimal;
import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.base.util.UtilNumber;

userLogin = session.getAttribute("userLogin");
orderId = StringUtils.trimToEmpty(parameters.orderId);
context.orderId = orderId;

orderHeader = null;
orderItems = null;
orderNotes = null;
partyId = null;
trackingURL ="";
orderReadHelper = null;
shippingApplies = true;

if (UtilValidate.isNotEmpty(orderId)) 
{
	orderHeader = EntityQuery.use(delegator).from("OrderHeader").where("orderId", orderId).queryOne();
	if (UtilValidate.isNotEmpty(orderHeader)) 
	{
		Debug.log("====================orderProductStore================================");
		orderProductStore = orderHeader.getRelatedOne("ProductStore");
		if (UtilValidate.isNotEmpty(orderProductStore.storeName))
		{
			productStoreName = orderProductStore.storeName;
		}
		else
		{
			productStoreName = orderHeader.productStoreId;
		}
		context.productStoreName = productStoreName;
		
	    // note these are overridden in the OrderViewWebSecure.groovy script if run
	    context.hasPermission = true;
	    context.canViewInternalDetails = true;
	
	    orderReadHelper = new OrderReadHelper(orderHeader);
	    orderItems = orderReadHelper.getOrderItems();
		
		orderAdjustments = orderReadHelper.getAdjustments();
		
		
		//shipping applies check
		shippingApplies = orderReadHelper.shippingApplies();
		context.shippingApplies = shippingApplies;
	
	    // get the order type
	    orderType = orderHeader.orderTypeId;
	    context.orderType = orderType;
	
	    // get the display party
	    displayParty = null;
	    if ("PURCHASE_ORDER".equals(orderType)) 
	    {
	        displayParty = orderReadHelper.getSupplierAgent();
	    } 
	    else 
	    {
	        displayParty = orderReadHelper.getPlacingParty();
	    }
	    if (UtilValidate.isNotEmpty(displayParty)) 
	    {
	        partyId = displayParty.partyId;
	        context.displayParty = displayParty;
	        context.partyId = partyId;
	
			Debug.log("displayParty========="+displayParty);
	        //Get PRIMARY EMAIL, TELEPHONE LOCATIONS
	        partyContactMechPurpose = displayParty.getRelated("PartyContactMechPurpose");
			Debug.log("partyContactMechPurpose========="+partyContactMechPurpose);
	        partyContactMechPurpose = EntityUtil.filterByDate(partyContactMechPurpose,true);
	
	        partyPurposeEmails = EntityUtil.filterByAnd(partyContactMechPurpose, UtilMisc.toMap("contactMechPurposeTypeId", "PRIMARY_EMAIL"));
			Debug.log("partyPurposeEmails====11====="+partyPurposeEmails);
	        partyPurposeEmails = EntityUtil.getFirst(partyPurposeEmails).getRelated("PartyContactMech", null, null, false);
			Debug.log("partyPurposeEmails=====22===="+partyPurposeEmails);
	        partyPurposeEmails = EntityUtil.filterByDate(partyPurposeEmails,true);
			Debug.log("partyPurposeEmails=====33===="+partyPurposeEmails);
	        partyPurposeEmails = EntityUtil.orderBy(partyPurposeEmails, UtilMisc.toList("fromDate DESC"));
			Debug.log("partyPurposeEmails=======44=="+partyPurposeEmails);
	        if (UtilValidate.isNotEmpty(partyPurposeEmails)) 
	        {
	            partyPurposeEmail = EntityUtil.getFirst(partyPurposeEmails);
				Debug.log("partyPurposeEmails=====55==="+partyPurposeEmails);
	            contactMech = partyPurposeEmail.getRelatedOne("ContactMech");
	            Debug.log("contactMech========"+contactMech);
				context.userEmailContactMech = contactMech;
	            context.userEmailAddress = contactMech.infoString;
	            context.userEmailAllowSolicitation= partyPurposeEmail.allowSolicitation;
	            userEmailContactMechList= EntityUtil.getFirst(partyPurposeEmails).getRelated("ContactMech",null, null, false);
	            context.userEmailContactMechList = userEmailContactMechList;
	            
	        }
	
	        partyPurposeHomePhones = EntityUtil.filterByAnd(partyContactMechPurpose, UtilMisc.toMap("contactMechPurposeTypeId", "PHONE_HOME"));
	        partyPurposeHomePhones = EntityUtil.getFirst(partyPurposeHomePhones).getRelated("PartyContactMech", null, null, false);
	        partyPurposeHomePhones = EntityUtil.filterByDate(partyPurposeHomePhones,true);
	        partyPurposeHomePhones = EntityUtil.orderBy(partyPurposeHomePhones, UtilMisc.toList("fromDate DESC"));
	        if (UtilValidate.isNotEmpty(partyPurposeHomePhones)) 
	        {
	            partyPurposePhone = EntityUtil.getFirst(partyPurposeHomePhones);
				Debug.log("partyPurposePhone======"+partyPurposePhone);
	            telecomNumber = partyPurposePhone.getRelatedOne("TelecomNumber");
				Debug.log("telecomNumber==rrr===="+telecomNumber);
	            context.phoneHomeTelecomNumber =telecomNumber;
	            context.phoneHomeAreaCode =telecomNumber.areaCode;
	            context.phoneHomeContactNumber =telecomNumber.contactNumber;
	            context.partyPurposeHomePhones =partyPurposeHomePhones;
	        }
	        
	        partyPurposeWorkPhones = EntityUtil.filterByAnd(partyContactMechPurpose, UtilMisc.toMap("contactMechPurposeTypeId", "PHONE_WORK"));
			if(UtilValidate.isNotEmpty(partyPurposeWorkPhones)){
		        partyPurposeWorkPhones = EntityUtil.getFirst(partyPurposeWorkPhones).getRelated("PartyContactMech", null, null, false);
		        partyPurposeWorkPhones = EntityUtil.filterByDate(partyPurposeWorkPhones,true);
		        partyPurposeWorkPhones = EntityUtil.orderBy(partyPurposeWorkPhones, UtilMisc.toList("fromDate DESC"));
			}
			if (UtilValidate.isNotEmpty(partyPurposeWorkPhones)) 
	        {
	            partyPurposePhone = EntityUtil.getFirst(partyPurposeWorkPhones);
				Debug.log("partyPurposePhone33======"+partyPurposePhone);
	            telecomNumber = partyPurposePhone.getRelatedOne("TelecomNumber");
				Debug.log("telecomNumber======"+telecomNumber);
		        context.partyPurposeWorkPhone =partyPurposePhone;
	            context.phoneWorkTelecomNumber =telecomNumber;
	            context.phoneWorkAreaCode =telecomNumber.areaCode;
	            context.phoneWorkContactNumber =telecomNumber.contactNumber;
	            context.partyPurposeWorkPhones =partyPurposeWorkPhones;
	        }
	
	        partyPurposeMobilePhones = EntityUtil.filterByAnd(partyContactMechPurpose, UtilMisc.toMap("contactMechPurposeTypeId", "PHONE_MOBILE"));
			if (UtilValidate.isNotEmpty(partyPurposeWorkPhones)){
		        partyPurposeMobilePhones = EntityUtil.getFirst(partyPurposeMobilePhones).getRelated("PartyContactMech", null, null, false);
		        partyPurposeMobilePhones = EntityUtil.filterByDate(partyPurposeMobilePhones,true);
		        partyPurposeMobilePhones = EntityUtil.orderBy(partyPurposeMobilePhones, UtilMisc.toList("fromDate DESC"));
			}
	        Debug.log("==============partyPurposeMobilePhones-==========================");
			if (UtilValidate.isNotEmpty(partyPurposeMobilePhones)) 
	        {
	          
				  partyPurposePhone = EntityUtil.getFirst(partyPurposeMobilePhones);
				  Debug.log("==============partyPurposePhone-==========================");
	            telecomNumber = partyPurposePhone.getRelatedOne("TelecomNumber");
	            context.phoneMobileTelecomNumber =telecomNumber;
	            context.phoneMobileAreaCode =telecomNumber.areaCode;
	            context.phoneMobileContactNumber =telecomNumber.contactNumber;
	            context.partyPurposeMobilePhones =partyPurposeMobilePhones;
	        }
	        
	    }
	
	    canceledPromoOrderItem = [:];
	    orderItemList = orderReadHelper.getOrderItems();
	    orderItemList.each { orderItem ->
	        if("Y".equals(orderItem.get("isPromo")) && "ITEM_CANCELLED".equals(orderItem.get("statusId"))) 
	        {
	            canceledPromoOrderItem = orderItem;
	        }
	        orderItemList.remove(canceledPromoOrderItem);
	    }
	    context.orderItemList = orderItemList;
	
	    shippingAddress = orderReadHelper.getShippingAddress();
	    context.shippingAddress = shippingAddress;
	
	    billingAddress = orderReadHelper.getBillingAddress();
	    context.billingAddress = billingAddress;
	
	    distributorId = orderReadHelper.getDistributorId();
	    context.distributorId = distributorId;
	
	    affiliateId = orderReadHelper.getAffiliateId();
	    context.affiliateId = affiliateId;
	
	    billingAccount = orderHeader.getRelatedOne("BillingAccount");
	    context.billingAccount = billingAccount;
	    context.billingAccountMaxAmount = orderReadHelper.getBillingAccountMaxAmount();
	
	    ecl = EntityCondition.makeCondition([
	                                    EntityCondition.makeCondition("orderId", EntityOperator.EQUALS, orderId),
	                                    EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "PAYMENT_CANCELLED")],
	                                EntityOperator.AND);
	    orderPaymentPreferences = delegator.findList("OrderPaymentPreference", ecl, null, null, null, false);
		orderPaymentPreferences = EntityUtil.orderBy(orderPaymentPreferences, UtilMisc.toList("createdDate ASC"));
	    context.orderPaymentPreferences = orderPaymentPreferences;
	
	    // ship groups
		
	    shipGroups = orderHeader.getRelated("OrderItemShipGroup",null, ["-shipGroupSeqId"],false);
	    context.shipGroups = shipGroups;
	    shipGroupsSize = shipGroups.size();
	    context.shipGroupsSize = shipGroupsSize;
	    shipGroup = EntityUtil.getFirst(shipGroups);
		carrierPartyId = "";
		trackingNumber = "";
		if(UtilValidate.isNotEmpty(shipGroup))
	    {
			carrierPartyId = shipGroup.carrierPartyId;
			trackingNumber = shipGroup.trackingNumber;
	    }
	    customerPoNumber = null;
	    orderItemList.each { orderItem ->
	        customerPoNumber = orderItem.correspondingPoId;
	    }
	    context.customerPoNumber = customerPoNumber;
	
	    statusChange = delegator.findByAnd("StatusValidChange", [statusId : orderHeader.statusId], null, false);
	    context.statusChange = statusChange;
		Debug.log("====================================================");
	    currentStatus = orderHeader.getRelatedOne("StatusItem");
	    context.currentStatus = currentStatus;
	
	    orderHeaderStatuses = orderReadHelper.getOrderHeaderStatuses();
	    context.orderHeaderStatuses = orderHeaderStatuses;
	
	    adjustmentTypes = delegator.findList("OrderAdjustmentType", null, null, ["description"], null, false);
	    context.orderAdjustmentTypes = adjustmentTypes;
	
	    notes = orderHeader.getRelated("OrderHeaderNoteView",null, ["-noteDateTime"],false);
	    context.orderNotes = notes;
	    orderNotes = notes;
	    
	    if(UtilValidate.isNotEmpty(context.showOrderNotesPaging) && context.showOrderNotesPaging == "true")
	    {
	        pagingListSize=orderNotes.size();
	        context.pagingListSize=pagingListSize;
	        context.pagingList = orderNotes;
	    }
	    
	    showNoteHeadingOnPDF = false;
	    if (UtilValidate.isNotEmpty(notes) && EntityUtil.filterByCondition(notes, EntityCondition.makeCondition("internalNote", EntityOperator.EQUALS, "N")).size() > 0) 
	    {
	        showNoteHeadingOnPDF = true;
	    }
	    context.showNoteHeadingOnPDF = showNoteHeadingOnPDF;
	
	    cmvm = ContactMechWorker.getOrderContactMechValueMaps(delegator, orderId);
	    context.orderContactMechValueMaps = cmvm;
	
	    orderItemChangeReasons = delegator.findByAnd("Enumeration", [enumTypeId : "ODR_ITM_CH_REASON"], ["sequenceId"], false);
	    context.orderItemChangeReasons = orderItemChangeReasons;
		
		// Fetching the carrier tracking URL
		if(UtilValidate.isNotEmpty(shipGroupsSize) && shipGroupsSize == 1)
		{
		    trackingURLPartyContents = delegator.findByAnd("PartyContent", UtilMisc.toMap("partyId",carrierPartyId,"partyContentTypeId", "TRACKING_URL"), null, false);
		    if(UtilValidate.isNotEmpty(trackingURLPartyContents))
		    {
		        trackingURLPartyContent = EntityUtil.getFirst(trackingURLPartyContents);
		        if(UtilValidate.isNotEmpty(trackingURLPartyContent))
		        {
					Debug.log("====================content================================");
		            content = trackingURLPartyContent.getRelatedOne("Content");
		            if(UtilValidate.isNotEmpty(content))
		            {
						Debug.log("====================DataResource================================");
		                dataResource = content.getRelatedOne("DataResource");
		                if(UtilValidate.isNotEmpty(dataResource))
		                {
							Debug.log("====================electronicText================================");
		                    electronicText = dataResource.getRelatedOne("ElectronicText");
		                    trackingURL = electronicText.textData;
		                    if(UtilValidate.isNotEmpty(trackingURL))
		                    {
		                        trackingURL = FlexibleStringExpander.expandString(trackingURL,  UtilMisc.toMap("TRACKING_NUMBER":trackingNumber))
		                    }
		                }
		            }
		        }
		    }
		}
		
		if(security.hasEntityPermission('SPER_ORDER_MGMT', '_VIEW', session))
		{
			messageMap=[:];
			messageMap.put("orderId", orderId);
		
			context.orderId=orderId;
			context.pageTitle = UtilProperties.getMessage("OSafeAdminUiLabels","OrderManagementOrderDetailTitle",messageMap, locale )
			context.generalInfoBoxHeading = UtilProperties.getMessage("OSafeAdminUiLabels","OrderDetailInfoHeading",messageMap, locale )
			if(UtilValidate.isNotEmpty(context.showOrderNoteHeading) && context.showOrderNoteHeading == "true" )
			{
				context.orderNoteInfoBoxHeading = UtilProperties.getMessage("OSafeAdminUiLabels","OrderNoteHeading",messageMap, locale )
			}
			if(UtilValidate.isNotEmpty(context.showOrderAttributeHeading) && context.showOrderAttributeHeading == "true" )
			{
				context.orderAttributeInfoBoxHeading = UtilProperties.getMessage("OSafeAdminUiLabels","OrderAttributeHeading",messageMap, locale )
			}
			
			context.notesCount = orderNotes.size();
			
			if(UtilValidate.isNotEmpty(context.showOrderItemsPaging) && context.showOrderItemsPaging == "true")
			{
				pagingListSize=orderItems.size();
				context.pagingListSize=pagingListSize;
				context.pagingList = orderItems;
			}
		
			storeId = "";
			Debug.log("sdddddddddddddddddddddddddddd");
			orderDeliveryOptionAttr = orderHeader.getRelated("OrderAttribute", [attrName : "DELIVERY_OPTION"],null,false);
			Debug.log("NNNNNNNNNNNN");
			orderDeliveryOptionAttr = EntityUtil.getFirst(orderDeliveryOptionAttr);
			
			if (UtilValidate.isNotEmpty(orderDeliveryOptionAttr) && orderDeliveryOptionAttr.attrValue == "STORE_PICKUP")
			{
				context.isStorePickup = "Y";
				Debug.log("MMMM")
				orderStoreLocationAttr = orderHeader.getRelated("OrderAttribute", [attrName : "STORE_LOCATION"],null,false);
				orderStoreLocationAttr = EntityUtil.getFirst(orderStoreLocationAttr);
				if (UtilValidate.isNotEmpty(orderStoreLocationAttr))
				{
					storeId = orderStoreLocationAttr.attrValue;
				}
			}
		Debug.log("storeId=================="+storeId);
			if (UtilValidate.isNotEmpty(storeId))
			{
				context.storeId = storeId;
				store = delegator.findOne("Party", [partyId : storeId], false);
				context.store = store;
				if (UtilValidate.isNotEmpty(store))
				{
					Debug.log("====================storeInfo================================");
					storeInfo = store.getRelatedOne("PartyGroup");
					if (UtilValidate.isNotEmpty(storeInfo))
					{
						context.storeInfo = storeInfo;
					}
				}
				partyContactMechValueMaps = ContactMechWorker.getPartyContactMechValueMaps(delegator, storeId, false);
				if (UtilValidate.isNotEmpty(partyContactMechValueMaps))
				{
					partyContactMechValueMaps.each { partyContactMechValueMap ->
						contactMechPurposes = partyContactMechValueMap.partyContactMechPurposes;
						contactMechPurposes.each { contactMechPurpose ->
							if (contactMechPurpose.contactMechPurposeTypeId.equals("GENERAL_LOCATION"))
							{
								context.storeContactMechValueMap = partyContactMechValueMap;
							}
						}
					}
				}
			}
		}
		
		//display success message for checkout
		String showThankYouStatus = request.getAttribute("showThankYouStatus");
		if (UtilValidate.isEmpty(showThankYouStatus))
		{
			context.showThankYouStatus ="N"
		}
		if("Y".equals (showThankYouStatus))
		{
			messageMap=[:];
			messageMap.put("orderId", orderId);
			checkoutSuccessMessageList = UtilMisc.toList(UtilProperties.getMessage("OSafeAdminUiLabels","OrderCheckoutSuccess",messageMap, locale ));
			context.checkoutSuccessMessageList = checkoutSuccessMessageList;
		}
	}
}

context.orderHeader = orderHeader;
context.orderReadHelper = orderReadHelper;
context.orderItems = orderItems;
context.trackingURL = trackingURL;
