package setup;

import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.base.util.StringUtil;
import org.apache.ofbiz.product.catalog.CatalogWorker;
import org.apache.ofbiz.product.store.ProductStoreWorker;
import org.apache.ofbiz.common.CommonWorkers;
import org.apache.ofbiz.osafe.util.Util;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.entity.util.EntityQuery;
import org.apache.ofbiz.party.contact.ContactHelper;
import org.apache.ofbiz.base.util.Debug;

productStore = ProductStoreWorker.getProductStore(request);
if (UtilValidate.isNotEmpty(productStore))
{
  String companyName = productStore.companyName;
  if (UtilValidate.isEmpty(companyName))
  {
     companyName=productStore.storeName;
  }
  globalContext.companyName = companyName;
  
  //Set Default Keywords to the prod catalog product categories
  if (UtilValidate.isEmpty(context.metaKeywords))
  {
        prodCatalog = CatalogWorker.getProdCatalog(request);
        keywords = [];
        keywords.add(productStore.storeName);
        keywords.add(prodCatalog.prodCatalogId);
        context.metaKeywords = StringUtil.join(keywords, ", ");
  }
  
}

globalContext.productStore = productStore;
globalContext.productStoreId = productStore.productStoreId;

preferredDateFormat = Util.getProductStoreParm(request,"FORMAT_DATE");
Debug.log("preferredDateFormat ================"+preferredDateFormat);
preferredDateTimeFormat = Util.getProductStoreParm(request,"FORMAT_DATE_TIME");
Debug.log("preferredDateTimeFormat ================"+preferredDateTimeFormat);

currencyRounding=2;
roundCurrency = Util.getProductStoreParm(request,"CURRENCY_UOM_ROUNDING");
if (UtilValidate.isNotEmpty(roundCurrency) && Util.isNumber(roundCurrency))
{
	currencyRounding = Integer.parseInt(roundCurrency);
}
globalContext.currencyRounding =currencyRounding;
globalContext.preferredDateFormat = Util.isValidDateFormat(preferredDateFormat)?preferredDateFormat:"MM/dd/yy";
globalContext.preferredDateTimeFormat = Util.isValidDateFormat(preferredDateTimeFormat)?preferredDateTimeFormat:"MM/dd/yy h:mma";


if (UtilValidate.isNotEmpty(productStore))
{
  pageTrackingList = productStore.getRelated("XPixelTracking",null,null,true);
  pageTrackingList = EntityUtil.filterByDate(pageTrackingList,true);
  context.pageTrackingList = pageTrackingList;
}

userLogin = session.getAttribute("userLogin");

if (UtilValidate.isNotEmpty(userLogin)) 
{
	globalContext.userEmailAddress=userLogin.userLoginId;

	person = userLogin.getRelatedOne("Person",true);
	if (UtilValidate.isNotEmpty(person))
	{
		globalContext.userFirstName=person.firstName;
		globalContext.userLastName=person.lastName;
		
	}
	party = userLogin.getRelatedOne("Party",true);
	if (UtilValidate.isNotEmpty(party))
	{
		contactMech = EntityUtil.getFirst(ContactHelper.getContactMech(party, "BILLING_LOCATION", "POSTAL_ADDRESS", false));
		if (UtilValidate.isNotEmpty(contactMech))
		{
			postalAddressData = contactMech.getRelatedOne("PostalAddress",true);
			if (UtilValidate.isNotEmpty(postalAddressData))
			{
				globalContext.userPostalAddressData=postalAddressData;
				globalContext.userAddress1 = postalAddressData.address1;
				globalContext.userAddress2 = postalAddressData.address2;
				globalContext.userCity=postalAddressData.city;
				globalContext.userPostalCode=postalAddressData.postalCode;
				if (UtilValidate.isNotEmpty(postalAddressData.stateProvinceGeoId))
				{
			        //geoValue = EntityQuery.use(delegator).from("Geo").where([geoId : postalAddressData.stateProvinceGeoId]).cache().queryOne();
			        geoValue = EntityQuery.use(delegator).from("Geo").where([geoId : postalAddressData.stateProvinceGeoId]).cache().queryOne();
			        
			        if (UtilValidate.isNotEmpty(geoValue)) 
			        {
			            globalContext.userSelectedStateName = geoValue.geoName;
			            globalContext.userStateProvinceGeoId = geoValue.geoId;
			        }
					
				}
			}
			
		}
	    telecomNumber = EntityQuery.use(delegator).from("PartyContactDetailByPurpose").where("contactMechPurposeTypeId", "PHONE_HOME").orderBy(UtilMisc.toList("fromDate")).cache().queryList();
	    telecomNumber = EntityUtil.filterByDate(telecomNumber,true);
		
	    if(UtilValidate.isNotEmpty(telecomNumber))
		{
		       telecomNumber = EntityUtil.getFirst(telecomNumber);
		       globalContext.userPhoneHomeNumber= telecomNumber.contactNumber;
		       globalContext.userPhoneHomeAreaCode=telecomNumber.areaCode;
		       if(UtilValidate.isNotEmpty(telecomNumber.contactNumber) && (telecomNumber.contactNumber.length() > 6))
		       {
		           globalContext.userPhoneHomeNumber3=telecomNumber.contactNumber.substring(0,3);
		           globalContext.userPhoneHomeNumber4=telecomNumber.contactNumber.substring(3,7);
		       }
		 }	
	  }
}
