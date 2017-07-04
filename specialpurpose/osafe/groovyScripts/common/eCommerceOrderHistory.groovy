import org.apache.ofbiz.base.util.*;
import org.apache.ofbiz.entity.*;
import org.apache.ofbiz.entity.util.*;
import org.apache.ofbiz.entity.condition.*;
import org.apache.ofbiz.entity.util.EntityQuery;

orderRoleCollection = EntityQuery.use(delegator).from("OrderRole").where("partyId" , userLogin.partyId, "roleTypeId", "PLACING_CUSTOMER").cache().queryList();
orderHeaderList = EntityUtil.orderBy(EntityUtil.filterByAnd(EntityUtil.getFirst(orderRoleCollection).getRelated("OrderHeader", null, null, false),
        [EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "ORDER_REJECTED")]), ["orderDate DESC"]);
context.orderHeaderList = orderHeaderList;

