import org.apache.ofbiz.base.util.*;
import org.apache.ofbiz.entity.*;
import org.apache.ofbiz.entity.util.*;
import org.apache.ofbiz.entity.condition.*;
import java.sql.Timestamp;
import org.apache.ofbiz.base.util.Debug;
import java.util.*;
import org.apache.ofbiz.product.store.*;
import java.util.LinkedList;
import java.util.HashMap;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.commons.lang.StringUtils;
import org.apache.ofbiz.entity.util.EntityQuery;

productStoreId = ProductStoreWorker.getProductStoreId(request);
String orderId = StringUtils.trimToEmpty(parameters.orderId);

customerOrderList = EntityQuery.use(delegator).from("OrderHeaderAndRoles").where("roleTypeId","PLACING_CUSTOMER", "partyId",userLogin.partyId).orderBy(UtilMisc.toList("-orderDate")).cache().queryList();
context.customerOrderList = customerOrderList;
customerOrderIdList = EntityUtil.getFieldListFromEntityList(customerOrderList, "orderId", true);	

prodCond = LinkedList.newInstance();
paramsExpr = LinkedList.newInstance();

if (UtilValidate.isNotEmpty(orderId))
{
	paramsExpr.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("orderId"), EntityOperator.EQUALS, orderId.toUpperCase()));
	context.orderId = orderId;
}
else
{
	//make all orderIds uppercase
	customerOrderIdListUpper = LinkedList.newInstance();
	if(UtilValidate.isNotEmpty(customerOrderIdList))
	{
		for(Object customerOrderId : customerOrderIdList)
		{
			customerOrderIdListUpper.add(customerOrderId.toUpperCase());
		}
	}
	paramsExpr.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("orderId"), EntityOperator.IN, customerOrderIdListUpper));
}
List orderedItemsList = LinkedList.newInstance();
if (UtilValidate.isNotEmpty(paramsExpr))
{
	//mainCond = EntityCondition.makeCondition(paramsExpr, EntityOperator.AND);
	//orderedItemsList = delegator.findList("OrderHeaderAndItems", mainCond, null, UtilMisc.toList("-orderDate"), null, true);
	orderedItemsList=EntityQuery.use(delegator).from("OrderHeaderAndItems").where(paramsExpr).orderBy("-orderDate").queryList();
}
context.orderedItemsList = orderedItemsList;

