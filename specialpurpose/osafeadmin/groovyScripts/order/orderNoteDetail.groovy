package order;

import org.apache.commons.lang.StringUtils;
import org.apache.ofbiz.base.util.UtilProperties;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.util.EntityQuery;

userLogin = session.getAttribute("userLogin");
orderId = StringUtils.trimToEmpty(parameters.orderId);
context.orderId = orderId;

orderHeader = null;
orderNotes = null;
partyId = null;

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
	notes = orderHeader.getRelated("OrderHeaderNoteView",null, ["-noteDateTime"],false);
	context.orderNotes = notes;
	orderNotes = notes;
	
	if(UtilValidate.isNotEmpty(context.showOrderNotesPaging) && context.showOrderNotesPaging == "true")
	{
        pagingListSize=orderNotes.size();
        context.pagingListSize=pagingListSize;
        context.pagingList = orderNotes;
	}
}

if(UtilValidate.isNotEmpty(orderId) && security.hasEntityPermission('SPER_ORDER_MGMT', '_VIEW', session))
{
    messageMap=[:];
    messageMap.put("orderId", orderId);

    context.orderId=orderId;
    context.pageTitle = UtilProperties.getMessage("OSafeAdminUiLabels","OrderManagementOrderDetailTitle",messageMap, locale )
    context.generalInfoBoxHeading = UtilProperties.getMessage("OSafeAdminUiLabels","OrderDetailInfoHeading",messageMap, locale )
    if(UtilValidate.isNotEmpty(context.showOrderNoteHeading) && context.showOrderNoteHeading == "true" )
	{
        context.orderNoteInfoBoxHeading = UtilProperties.getMessage("OSafeAdminUiLabels","OrderNoteHeading",messageMap, locale )
	}
    
	context.notesCount = orderNotes.size();  
}





