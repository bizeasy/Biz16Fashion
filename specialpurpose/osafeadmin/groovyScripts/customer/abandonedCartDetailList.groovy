package customer;

import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.commons.lang.StringUtils;
import org.apache.ofbiz.entity.util.EntityQuery;

import java.util.LinkedList;
import java.util.HashMap;
import org.apache.ofbiz.base.util.*;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.entity.condition.EntityCondition;
import org.apache.ofbiz.entity.condition.EntityOperator;

userLogin = session.getAttribute("userLogin");
shoppingListId = StringUtils.trimToEmpty(parameters.cartId);

context.partyId=partyId;

messageMap=[:];
messageMap.put("partyId", partyId);
messageMap.put("shoppingListId", shoppingListId);

context.partyId=partyId;
context.pageTitle = UtilProperties.getMessage("OSafeAdminUiLabels","AbandonedCartDetailTitle",messageMap, locale )
context.generalInfoBoxHeading = UtilProperties.getMessage("OSafeAdminUiLabels","CustomerDetailInfoHeading",messageMap, locale )
context.customerNoteInfoBoxHeading = UtilProperties.getMessage("OSafeAdminUiLabels","CustomerNoteHeading",messageMap, locale )

party = EntityQuery.use(delegator).from("Party").where("partyId", partyId).queryOne();

messageMap=[:];
messageMap.put("partyId", partyId);

context.detailInfoBoxHeading = UtilProperties.getMessage("OSafeAdminUiLabels","CustomerDetailInfoHeading",messageMap, locale )

List contentList = LinkedList.newInstance();
if (UtilValidate.isNotEmpty(shoppingListId))
{
   orderBy = ["shoppingListItemSeqId ASC"];
   mainCond = EntityCondition.makeCondition("shoppingListId", EntityOperator.EQUALS, shoppingListId);
   contentList = delegator.findList("ShoppingListItem",mainCond, null, orderBy, null, false);
   context.resultList = contentList;
}



context.userLoginId = userLogin.userLoginId;



