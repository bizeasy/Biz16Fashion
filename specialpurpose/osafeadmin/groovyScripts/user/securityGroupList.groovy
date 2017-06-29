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

srchSecurityGroup = StringUtils.trimToEmpty(parameters.srchSecurityGroup);

exprs = LinkedList.newInstance();
mainCond=null;

if(UtilValidate.isNotEmpty(srchSecurityGroup))
{
	groupId=srchSecurityGroup;
	exprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("groupId"), EntityOperator.EQUALS, groupId.toUpperCase()));
	context.groupId=groupId;
}

if(UtilValidate.isNotEmpty(exprs)) 
{
	prodCond=EntityCondition.makeCondition(exprs, EntityOperator.AND);
	mainCond=prodCond;
}

orderBy = ["groupId"];

securityGroups = LinkedList.newInstance();
if(UtilValidate.isNotEmpty(preRetrieved) && preRetrieved != "N")
{
	securityGroups = delegator.findList("SecurityGroup", mainCond, null, orderBy, null, false);
}

if(UtilValidate.isNotEmpty(securityGroups))
{
	context.securityGroups =securityGroups;
}

pagingListSize=securityGroups.size();
context.pagingListSize=pagingListSize;
context.pagingList = securityGroups;