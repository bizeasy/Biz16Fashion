package admin;

import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.base.util.string.FlexibleStringExpander;
import org.apache.ofbiz.base.util.UtilProperties;
import org.apache.ofbiz.base.util.UtilMisc;
import com.osafe.services.OsafeManageXml;
import org.apache.commons.lang.StringUtils;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;


String searchString = StringUtils.trimToEmpty(parameters.searchString);
String searchByName = StringUtils.trimToEmpty(parameters.srchByName);
String searchByDescription = StringUtils.trimToEmpty(parameters.srchByDescription);
String searchByCategory=StringUtils.trimToEmpty(parameters.srchByCategory);
String searchByValue=StringUtils.trimToEmpty(parameters.srchByValue);
String searchByAll=StringUtils.trimToEmpty(parameters.srchAll);
initializedCB = StringUtils.trimToEmpty(parameters.initializedCB);
preRetrieved = StringUtils.trimToEmpty(parameters.preRetrieved);

if (UtilValidate.isNotEmpty(preRetrieved))
{
   context.preRetrieved=preRetrieved;
}
else
{
  preRetrieved = context.preRetrieved;
}

if (UtilValidate.isNotEmpty(initializedCB))
{
   context.initializedCB=initializedCB;
}

uiLabelsSearchList = LinkedList.newInstance();
if(UtilValidate.isNotEmpty(preRetrieved) && preRetrieved != "N") {
    if(searchByName.equals("") && searchByCategory.equals("") && searchByDescription.equals("") && searchByValue.equals("") && searchString.equals("")) {
         uiLabelsSearchList =  OsafeManageXml.getListMapsFromXmlFile(XmlFilePath);
    } else {
        searchRestrictionMap = HashMap.newInstance();
        searchRestrictionMap.put("key", searchByName);
        searchRestrictionMap.put("category", searchByCategory);
        searchRestrictionMap.put("description", searchByDescription);
        searchRestrictionMap.put("value", searchByValue);
        uiLabelsSearchList =  OsafeManageXml.getSearchListFromXmlFile(XmlFilePath, searchRestrictionMap, searchString);
    }
    uiLabelsSearchList = UtilMisc.sortMaps(uiLabelsSearchList, UtilMisc.toList("key"));
}

pagingListSize=uiLabelsSearchList.size();
context.pagingListSize=pagingListSize;
context.pagingList = uiLabelsSearchList;