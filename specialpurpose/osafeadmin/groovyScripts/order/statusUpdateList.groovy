package order;

import java.util.LinkedList;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;
import org.apache.ofbiz.base.util.UtilProperties;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.util.EntityQuery;

// Paging variables
viewIndex = Integer.valueOf(parameters.viewIndex  ?: 1);
viewSize = Integer.valueOf(parameters.viewSize ?: UtilProperties.getPropertyValue("osafeAdmin", "default-view-size"));
context.viewIndex = viewIndex;
context.viewSize = viewSize;
Map<String, Object> svcCtx = HashMap.newInstance();
userLogin = session.getAttribute("userLogin");
svcCtx.put("userLogin", userLogin);

orderId = StringUtils.trimToEmpty(parameters.orderId);
contentList = LinkedList.newInstance();

if (UtilValidate.isNotEmpty(orderId)) 
{
	orderHeader = EntityQuery.use(delegator).from("OrderHeader").where("orderId", orderId).queryOne();
	context.orderHeader = orderHeader;
	
	orderProductStore = orderHeader.getRelatedOne("ProductStore");
	if (UtilValidate.isNotEmpty(orderProductStore.storeName))
	{
		productStoreName = orderProductStore.storeName;
	}
	else
	{
		productStoreName = orderHeader.productStoreId;
	}
	context.productStoreName = productStoreName;
}

if (UtilValidate.isNotEmpty(orderHeader))
{
	contentList = orderHeader.getRelated("OrderStatus",null, ["orderStatusId"],false);
	
	notes = orderHeader.getRelated("OrderHeaderNoteView");
	context.notesCount = notes.size();
}
context.resultList = contentList;