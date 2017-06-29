package content;

import java.util.LinkedList;
import java.util.HashMap;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.condition.EntityCondition;
import org.apache.ofbiz.entity.condition.EntityOperator;
import org.apache.ofbiz.base.util.UtilProperties;
import org.apache.ofbiz.base.util.string.FlexibleStringExpander;

orderBy = ["contentId"];
List conds = LinkedList.newInstance();
osafeProperties = UtilProperties.getResourceBundleMap("OsafeProperties.xml", locale);
conds.add(EntityCondition.makeCondition([contentTypeId : "BF_STATIC_PAGE"]));
conds.add(EntityCondition.makeCondition([productStoreId : productStoreId]));
contentList = delegator.findList("XContentXref",EntityCondition.makeCondition(conds, EntityOperator.AND), null, orderBy, null, false);
context.contentList = contentList;

if (UtilValidate.isNotEmpty(context.SITEMAP_STATIC_URL))
{
    seoFriendlyPrefix = context.SITEMAP_STATIC_URL.substring(0, context.SITEMAP_STATIC_URL.lastIndexOf("/")+1);
    context.seoFriendlyPrefix = seoFriendlyPrefix;
}