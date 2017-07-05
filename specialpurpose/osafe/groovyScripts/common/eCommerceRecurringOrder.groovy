import org.apache.ofbiz.base.util.*;
import org.apache.ofbiz.entity.*;
import org.apache.ofbiz.entity.util.*;
import org.apache.ofbiz.entity.condition.*;
import org.apache.ofbiz.entity.util.EntityQuery

shoppingLists = EntityQuery.use(delegator).from("ShoppingList").where("shoppingListTypeId","SLT_AUTO_REODR", "partyId",userLogin.partyId).orderBy(UtilMisc.toList("isActive DESC")).cache().queryList();
context.shoppingLists = shoppingLists;

