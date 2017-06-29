package common;

import org.apache.ofbiz.base.util.StringUtil;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.entity.util.EntityQuery;
import org.apache.ofbiz.party.contact.ContactHelper;
import org.apache.ofbiz.entity.condition.EntityCondition;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.base.util.Debug;

if (UtilValidate.isNotEmpty(userLogin)) 
{
	context.emailLogin=userLogin.userLoginId;
	person = userLogin.getRelatedOne("Person",true);
	context.firstName=person.firstName;
	context.lastName=person.lastName;
	party = userLogin.getRelatedOne("Party",true);
	contactMech = EntityUtil.getFirst(ContactHelper.getContactMech(party, "BILLING_LOCATION", "POSTAL_ADDRESS", false));
	context.contactMech = contactMech;
	postalAddressData = contactMech.getRelatedOne("PostalAddress",true);
	context.address1 = postalAddressData.address1;
	context.address2 = postalAddressData.address2;
	context.city=postalAddressData.city;
	context.postalCode=postalAddressData.postalCode;
	context.postalAddressData=postalAddressData;

	 if (UtilValidate.isNotEmpty(parameters.stateCode)) 
	 {
	        //geoValue = EntityQuery.use(delegator).from("Geo").where([geoId : parameters.stateCode]).cache().queryOne();
	        geoValue = EntityQuery.use(delegator).from("Geo").where([geoId : parameters.stateCode]).cache().queryOne();
	        if (UtilValidate.isNotEmpty(geoValue)) 
	        {
	            context.selectedStateName = geoValue.geoName;
	            context.stateProvinceGeoId = geoValue.geoId;
	        }
	 } 
	 else if (UtilValidate.isNotEmpty(postalAddressData) && UtilValidate.isNotEmpty(postalAddressData.stateProvinceGeoId)) 
	 {
	        //geoValue = EntityQuery.use(delegator).from("Geo").where([geoId : postalAddressData.stateProvinceGeoId]).cache().queryOne();
	        geoValue = EntityQuery.use(delegator).from("Geo").where([geoId : postalAddressData.stateProvinceGeoId]).cache().queryOne();
	        if (UtilValidate.isNotEmpty(geoValue)) 
	        {
	            context.selectedStateName = geoValue.geoName;
	            context.stateProvinceGeoId = geoValue.geoId;
	        }
	 }
	
    telecomNumber = EntityQuery.use(delegator).from("PartyContactDetailByPurpose").where("partyId", party.partyId,"contactMechPurposeTypeId", "PHONE_HOME").orderBy(UtilMisc.toList("fromDate")).cache().queryList();
    telecomNumber = EntityUtil.filterByDate(telecomNumber,true);

    if(UtilValidate.isNotEmpty(telecomNumber))
	{
	       telecomNumber = EntityUtil.getFirst(telecomNumber);
	       context.contactNumberHome= telecomNumber.contactNumber;
	       context.areaCodeHome=telecomNumber.areaCode;
	       if(UtilValidate.isNotEmpty(telecomNumber.contactNumber) && (telecomNumber.contactNumber.length() > 6))
	       {
	           context.contactNumber3Home=telecomNumber.contactNumber.substring(0,3);
	           context.contactNumber4Home=telecomNumber.contactNumber.substring(3,7);
	       }
	 }
} 
else
{
    if (UtilValidate.isNotEmpty(parameters.stateCode)) 
    {
	    //geoValue = EntityQuery.use(delegator).from("Geo").where([geoId : parameters.stateCode]).cache().queryOne();
	    geoValue = EntityQuery.use(delegator).from("Geo").where([geoId : parameters.stateCode]).cache().queryOne();
	    if (UtilValidate.isNotEmpty(geoValue)) 
	    {
	        context.selectedStateName = geoValue.geoName;
	        context.stateProvinceGeoId = geoValue.geoId;
	    }
    }
}