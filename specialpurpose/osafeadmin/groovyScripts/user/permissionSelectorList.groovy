package user;

import java.util.LinkedList;
import org.apache.commons.lang.StringUtils;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.condition.EntityCondition;
import org.apache.ofbiz.entity.condition.EntityOperator;
import org.apache.ofbiz.entity.condition.EntityFunction;

if (UtilValidate.isNotEmpty(preRetrieved))
{
   context.preRetrieved=preRetrieved;
}
else
{
  preRetrieved = context.preRetrieved;
}

srchPermission = StringUtils.trimToEmpty(parameters.srchPermission);

exprs = LinkedList.newInstance();
mainCond=null;

if(UtilValidate.isNotEmpty(srchPermission))
{
	permissionId=srchPermission;
	exprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("permissionId"), EntityOperator.EQUALS, permissionId.toUpperCase()));
	context.permissionId=permissionId;
}

if (UtilValidate.isNotEmpty(exprs)) {
	prodCond=EntityCondition.makeCondition(exprs, EntityOperator.AND);
	mainCond=prodCond;
}

orderBy = ["permissionId"];
permissions = LinkedList.newInstance();

if(UtilValidate.isNotEmpty(preRetrieved) && preRetrieved != "N")
{
	permissions = delegator.findList("SecurityPermission", mainCond, null, orderBy, null, false);
}

if (UtilValidate.isNotEmpty(permissions))
{
	context.permissions =permissions;
}
 
pagingListSize=permissions.size();
context.pagingListSize=pagingListSize;
context.pagingList = permissions;