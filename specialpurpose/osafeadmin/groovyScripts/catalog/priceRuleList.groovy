package catalog;

import java.util.LinkedList;

import org.apache.commons.lang.StringUtils;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.GenericValue
import org.apache.ofbiz.entity.condition.EntityCondition;
import org.apache.ofbiz.entity.condition.EntityOperator;
import org.apache.ofbiz.entity.condition.EntityFunction;

srchPriceRuleName = StringUtils.trimToEmpty(parameters.srchPriceRuleName);
initializedCB = StringUtils.trimToEmpty(parameters.initializedCB);
preRetrieved = StringUtils.trimToEmpty(parameters.preRetrieved);

if (UtilValidate.isNotEmpty(preRetrieved))
{
   context.preRetrieved=preRetrieved;
}
else
{
  preRetrieved = context.preRetrieved;
}

if (UtilValidate.isNotEmpty(initializedCB))
{
   context.initializedCB=initializedCB;
}

paramsExpr = LinkedList.newInstance();
if (UtilValidate.isNotEmpty(srchPriceRuleName))
{
    paramsExpr.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("ruleName"),
            EntityOperator.LIKE, EntityFunction.UPPER("%"+srchPriceRuleName+"%")));
    context.srchPriceRuleName=srchPriceRuleName;
}
orderBy = ["fromDate"];
List<GenericValue> priceRuleList = LinkedList.newInstance();

if(UtilValidate.isNotEmpty(preRetrieved) && preRetrieved != "N")
{
    priceRuleList = delegator.findList("ProductPriceRule", EntityCondition.makeCondition(paramsExpr, EntityOperator.AND), null, orderBy, null, false);
}

context.pagingList = priceRuleList;
context.pagingListSize = priceRuleList.size();