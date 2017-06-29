package user;

import java.util.LinkedList;
import java.util.HashMap;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.base.util.UtilProperties;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.condition.EntityCondition;
import org.apache.ofbiz.entity.condition.EntityOperator;
import org.apache.ofbiz.base.util.UtilDateTime;
import org.apache.ofbiz.entity.condition.EntityFunction;
import java.sql.Timestamp;

Timestamp now = UtilDateTime.nowTimestamp(); 
// Paging variables
viewIndex = Integer.valueOf(parameters.viewIndex  ?: 1);
viewSize = Integer.valueOf(parameters.viewSize ?: UtilProperties.getPropertyValue("osafeAdmin", "default-view-size"));
context.viewIndex = viewIndex;
context.viewSize = viewSize;

groupId = parameters.groupId;

Map<String, Object> svcCtx = HashMap.newInstance();
userLogin = session.getAttribute("userLogin");
svcCtx.put("userLogin", userLogin);
exprs = LinkedList.newInstance();
mainCond=null;

List contentList = LinkedList.newInstance();
context.groupId = userLogin.groupId;

// groupId
if(UtilValidate.isNotEmpty(groupId))
{
	exprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("groupId"), EntityOperator.EQUALS, groupId.toUpperCase()));
	context.groupId=groupId;
}

if(UtilValidate.isNotEmpty(exprs)) 
{
	prodCond=EntityCondition.makeCondition(exprs, EntityOperator.AND);
	mainCond=prodCond;
}

contentList = delegator.findList("SecurityGroupPermission",mainCond, null, null, null, false);
context.resultList = contentList;