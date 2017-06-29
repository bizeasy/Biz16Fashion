package order;

import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.GenericValue;
import java.util.LinkedList;
import java.util.HashMap;

orderItemShipGroupAssoc = LinkedList.newInstance();
orderItemShipGroup = request.getAttribute("orderItemShipGroup");
if (UtilValidate.isNotEmpty(orderItemShipGroup))
{
	orderItemShipGroupAssoc = orderItemShipGroup.getRelated("OrderItemShipGroupAssoc");
	
}

context.orderItemShipGroup = orderItemShipGroup;
context.orderItemShipGroupAssoc = orderItemShipGroupAssoc;
