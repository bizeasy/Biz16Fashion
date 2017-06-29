package user;

import java.util.LinkedList;
import java.util.HashMap;
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

userLoginId = parameters.userLoginId;

Map<String, Object> svcCtx = HashMap.newInstance();
userLogin = session.getAttribute("userLogin");
svcCtx.put("userLogin", userLogin);
exprs = LinkedList.newInstance();
mainCond=null;

List contentList = LinkedList.newInstance();
context.userLoginId = userLogin.userLoginId;

// groupId
if(UtilValidate.isNotEmpty(userLoginId))
{
	exprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("userLoginId"), EntityOperator.EQUALS, userLoginId.toUpperCase()));
	context.userLoginId=userLoginId;
}

if (UtilValidate.isNotEmpty(exprs)) 
{
	prodCond=EntityCondition.makeCondition(exprs, EntityOperator.AND);
	mainCond=prodCond;
}

contentList = delegator.findList("UserLoginSecurityGroup",mainCond, null, null, null, false);
context.resultList = contentList;