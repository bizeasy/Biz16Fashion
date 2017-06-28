package common;

import org.apache.ofbiz.base.util.*;
import org.apache.ofbiz.entity.util.EntityQuery;
import java.util.LinkedList;

import org.apache.ofbiz.entity.GenericValue;


if (UtilValidate.isNotEmpty(context.contentId) && UtilValidate.isNotEmpty(context.productStoreId)) 
{
    //xContentXref = delegator.findByPrimaryKeyCache("XContentXref", [bfContentId : context.contentId, productStoreId : context.productStoreId]);
    xContentXref = EntityQuery.use(delegator).from("XContentXref").where([bfContentId : context.contentId, productStoreId : context.productStoreId]).cache().queryOne();
    if (UtilValidate.isNotEmpty(xContentXref))
    {
        content = xContentXref.getRelatedOne("Content",true);
        context.content = content;
        if ("CTNT_PUBLISHED".equals(content.statusId))
        {
            //set Meta title, Description and Keywords
            if (UtilValidate.isNotEmpty(content.contentName)) 
            {
                context.metaTitle = content.contentName;
                context.metaKeywords = content.contentName;
            }
            if (UtilValidate.isNotEmpty(content.description)) 
            {
                context.metaDescription = content.description;
            }
            //override HTML title, metatags, metakeywords
            contentAttrList = content.getRelated("ContentAttribute",null,null,true);
            for(GenericValue contentAttr : contentAttrList)
            {
                if(contentAttr.attrName == 'HTML_PAGE_TITLE') 
                {
                    if(UtilValidate.isNotEmpty(contentAttr.attrValue)) 
                    {
                        context.metaTitle = contentAttr.attrValue;
                    }
                }
                if(contentAttr.attrName == 'HTML_PAGE_META_DESC') 
                {
                    if(UtilValidate.isNotEmpty(contentAttr.attrValue)) 
                    {
                        context.metaDescription = contentAttr.attrValue;
                    }
                }
                if(contentAttr.attrName == 'HTML_PAGE_META_KEY') 
                {
                    if(UtilValidate.isNotEmpty(contentAttr.attrValue)) 
                    {
                        context.metaKeywords = contentAttr.attrValue;
                    }
                }
            }        	
        }
    }
}

