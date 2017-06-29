package common;

import org.apache.ofbiz.base.util.StringUtil;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.entity.util.EntityQuery;
import org.apache.ofbiz.base.util.UtilValidate;

postalAddressData = context.postalAddress;
if (UtilValidate.isNotEmpty(postalAddressData))
{
    if (UtilValidate.isNotEmpty(postalAddressData.toName))
    {
        String toName = postalAddressData.toName;
        toNameParts  = StringUtil.split(toName, " ");

        if (UtilValidate.isNotEmpty(toNameParts) && toNameParts.size() > 0)
        {
            context.firstName = toNameParts[0];
            context.lastName = StringUtil.join(toNameParts.subList(1,toNameParts.size()), " ");
        }
    }
}


if (UtilValidate.isNotEmpty(parameters.CUSTOMER_STATE)) 
{
    //geoValue = EntityQuery.use(delegator).from("Geo").where([geoId : parameters.CUSTOMER_STATE]).cache().queryOne();
    geoValue = EntityQuery.use(delegator).from("Geo").where([geoId : parameters.CUSTOMER_STATE]).cache().queryOne();
    if (UtilValidate.isNotEmpty(geoValue)) 
    {
        context.selectedStateName = geoValue.geoName;
    }
}

context.formRequestName = parameters.osafeFormRequestName;

contactMech = context.contactMech;
phoneNumberMap = [:];
if (UtilValidate.isNotEmpty(contactMech))
{
    contactMechIdFrom = contactMech.contactMechId;
    contactMechLinkList = EntityQuery.use(delegator).from("ContactMechLink").where("contactMechIdFrom", contactMechIdFrom).cache().queryList();

    for (GenericValue link: contactMechLinkList)
    {
        contactMechIdTo = link.contactMechIdTo
        //contactMech = EntityQuery.use(delegator).from("ContactMech").where([contactMechId : contactMechIdTo]).cache().queryOne();
        contactMech = EntityQuery.use(delegator).from("ContactMech").where([contactMechId : contactMechIdTo]).cache().queryOne();
        if(UtilValidate.isNotEmpty(contactMech)) 
        {
            phonePurposeList  = contactMech.getRelated("PartyContactMechPurpose",null,null,true);
            phonePurposeList  = EntityUtil.filterByDate(phonePurposeList, true);
            if(UtilValidate.isNotEmpty(phonePurposeList)) 
            {
                partyContactMechPurpose = EntityUtil.getFirst(phonePurposeList)
                if(UtilValidate.isNotEmpty(partyContactMechPurpose)) 
                {
                    telecomNumber = partyContactMechPurpose.getRelatedOne("TelecomNumber",true);
                    phoneNumberMap[partyContactMechPurpose.contactMechPurposeTypeId]=telecomNumber;
                }
            }
        }

    }
}
context.phoneNumberMap = phoneNumberMap;