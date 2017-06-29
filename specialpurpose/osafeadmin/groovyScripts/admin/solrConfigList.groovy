package admin;

import java.util.LinkedList;
import java.util.HashMap;
import org.apache.ofbiz.base.util.UtilMisc;

//manage Solr Facet Groups
adminToolsList = LinkedList.newInstance();
adminTool = HashMap.newInstance();
adminTool.put("toolType", uiLabelMap.ManageSOLRFacetGroupsHeading);
adminTool.put("toolDesc", uiLabelMap.ManageSOLRFacetGroupsInfo);
adminTool.put("toolDetail", "facetGroupList");
adminToolsList.add(adminTool);

//manage Solr Facet Values
adminTool = HashMap.newInstance();
adminTool.put("toolType", uiLabelMap.ManageSOLRFacetValueHeading);
adminTool.put("toolDesc", uiLabelMap.ManageSOLRFacetValueInfo);
adminTool.put("toolDetail", "facetValueList");
adminToolsList.add(adminTool);


context.resultList = UtilMisc.sortMaps(adminToolsList, UtilMisc.toList("toolType"));