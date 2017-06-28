import java.util.LinkedList;
import org.apache.ofbiz.base.util.StringUtil;
import org.apache.ofbiz.osafe.util.Util;
import org.apache.ofbiz.common.CommonWorkers;
import org.apache.ofbiz.base.util.*;

defaultCountry = Util.getProductStoreParm(request,"COUNTRY_DEFAULT");
countryDropDown = Util.getProductStoreParm(request,"COUNTRY_DROPDOWN");
defaultCountryGeoMap = [:];
List countryList = LinkedList.newInstance();

if (UtilValidate.isNotEmpty(defaultCountry))
 {
  defaultCountryGeoMap = Util.getCountryGeoInfo(delegator, defaultCountry.trim());
  if (UtilValidate.isNotEmpty(defaultCountryGeoMap))
   {
      context.defaultCountryGeoMap = defaultCountryGeoMap;
   }
 }

if (Util.isProductStoreParmTrue(request,"COUNTRY_MULTI")) 
 {
	if (UtilValidate.isNotEmpty(countryDropDown))
	{
	    if (countryDropDown.equalsIgnoreCase("All")) 
	      {
	          countryList = CommonWorkers.getCountryList(delegator);
	      }
	    else
	    {
		  countryGeoCodeList = StringUtil.split(countryDropDown,",")
		  for (String geoId: countryGeoCodeList) 
		   {
	          countryGeoId = geoId;
	          countryGeoMap = Util.getCountryGeoInfo(delegator, countryGeoId.trim());
	          if(UtilValidate.isNotEmpty(countryGeoMap))
	          {
	            countryList.add(countryGeoMap);
	          }
		   }
	     }
	}
 }

if (UtilValidate.isEmpty(countryList))
 {
    countryList = CommonWorkers.getCountryList(delegator);
 }
if (UtilValidate.isEmpty(defaultCountryGeoMap))
{
   defaultCountryGeoMap.put("geoId","USA");
   context.defaultCountryGeoMap = defaultCountryGeoMap;
}
context.countryList = countryList;