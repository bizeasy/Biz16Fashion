package common;

import org.apache.ofbiz.base.util.*;
import java.util.LinkedList;
import org.apache.ofbiz.content.content.ContentWorker;
import org.apache.ofbiz.product.store.ProductStoreWorker;

import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.condition.EntityCondition;
import org.apache.ofbiz.entity.condition.EntityOperator;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.entity.util.EntityQuery;

if (UtilValidate.isNotEmpty(context.contentId) && UtilValidate.isNotEmpty(context.productStoreId)) 
{
    //xContentXref = EntityQuery.use(delegator).from("XContentXref").where([bfContentId : context.contentId, productStoreId : context.productStoreId]).cache().queryOne();;
    xContentXref = EntityQuery.use(delegator).from("XContentXref").where([bfContentId : context.contentId, productStoreId : context.productStoreId]).cache().queryOne();
    if (UtilValidate.isNotEmpty(xContentXref))
    {
        content = xContentXref.getRelatedOne("Content",true);
        context.content = content;
    }
    else
    {
    	context.content = "";
    }
}
