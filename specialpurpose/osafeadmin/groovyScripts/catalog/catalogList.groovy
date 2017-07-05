package catalog;

import org.apache.ofbiz.product.category.CategoryContentWrapper;
import java.util.HashMap;
import java.util.LinkedList;
import org.apache.ofbiz.entity.util.EntityQuery
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.ofbiz.entity.condition.EntityCondition;
import org.apache.ofbiz.entity.condition.EntityConditionList;
import org.apache.ofbiz.entity.condition.EntityExpr;
import org.apache.ofbiz.entity.condition.EntityOperator;
import java.sql.Timestamp;
import org.apache.ofbiz.base.util.ObjectType;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.base.util.UtilProperties;
import org.apache.ofbiz.base.util.UtilDateTime;
import org.apache.ofbiz.base.util.UtilMisc;
import com.ibm.icu.util.Calendar;
import org.apache.ofbiz.osafe.util.OsafeAdminUtil;

initializedCB = StringUtils.trimToEmpty(parameters.initializedCB);
preRetrieved = StringUtils.trimToEmpty(parameters.preRetrieved);
catalogActiveDate = StringUtils.trimToEmpty(parameters.catalogActiveDate);
showAll = StringUtils.trimToEmpty(parameters.showAll);
List errMsgList = LinkedList.newInstance();
List infoMsgList = LinkedList.newInstance();
Boolean isValidDate = true;
categoryRollupList =  LinkedList.newInstance();
Timestamp catalogActiveDateTs = null;

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

productStore = context.productStore;
if (UtilValidate.isNotEmpty(productStore))
{
    String productStoreId = productStore.productStoreId;
    String currentCatalogId = context.currentCatalogId;
    String rootProductCategoryId = context.rootProductCategoryId;
    if (UtilValidate.isNotEmpty(rootProductCategoryId))
    {
        context.catalogTopCategoryId = rootProductCategoryId;
        orderBy = ["sequenceNum"];
        paramsExpr.add(EntityCondition.makeCondition("parentProductCategoryId", EntityOperator.EQUALS, rootProductCategoryId));
        paramCond = null;
        mainCond = null;
        dateCond = null;
        if (UtilValidate.isNotEmpty(paramsExpr)) 
        {
            paramCond=EntityCondition.makeCondition(paramsExpr, EntityOperator.AND);
            mainCond=paramCond;
        }
        dateExpr = LinkedList.newInstance();
        activeThruDateExpr = LinkedList.newInstance();
        if(UtilValidate.isEmpty(showAll))
        {
            if(UtilValidate.isNotEmpty(catalogActiveDate)) 
            {
                if(OsafeAdminUtil.isDateTime(catalogActiveDate, entryDateTimeFormat))
                {
                	
                    catalogActiveDateTs =UtilDateTime.nowTimestamp(); 
                    try 
                    {
                        catalogActiveDateTs =ObjectType.simpleTypeConvert(catalogActiveDate, "Timestamp", entryDateTimeFormat, locale);
                    }
                    catch (Exception e) 
                    {
                        catalogActiveDateTs =UtilDateTime.nowTimestamp();
                        context.catalogActiveDate=UtilDateTime.nowDateString(entryDateTimeFormat);
                        errMsgList.add(UtilProperties.getMessage("OSafeAdminUiLabels","AsOfDateError",locale));
                    }
                }
                else
                {
                    catalogActiveDateTs =UtilDateTime.nowTimestamp();
                    infoMsgList.add(UtilProperties.getMessage("OSafeAdminUiLabels","InvalidDateInfo",locale));
                    request.setAttribute("_INFO_MESSAGE_LIST_", infoMsgList);
                    isValidDate = false;
                }
            } else 
            {
                catalogActiveDateTs =UtilDateTime.nowTimestamp();
                context.catalogActiveDate=UtilDateTime.nowDateString(entryDateTimeFormat);
            }
            dateExpr.add(EntityCondition.makeCondition("fromDate", EntityOperator.LESS_THAN_EQUAL_TO, catalogActiveDateTs));
            activeThruDateExpr.add(EntityCondition.makeCondition("thruDate", EntityOperator.GREATER_THAN_EQUAL_TO, catalogActiveDateTs));
            activeThruDateExpr.add(EntityCondition.makeCondition("thruDate", EntityOperator.EQUALS, null));
            dateExpr.add(EntityCondition.makeCondition(activeThruDateExpr, EntityOperator.OR));
            if(UtilValidate.isNotEmpty(dateExpr))
            {
                dateCond = EntityCondition.makeCondition(dateExpr, EntityOperator.AND);
            }
            
            if (UtilValidate.isNotEmpty(dateCond)) 
            {
                mainCond = EntityCondition.makeCondition([paramCond, dateCond], EntityOperator.AND);
            }
        }
        else 
        {
            context.catalogActiveDate="";
        }
        if(isValidDate)
        {
            categoryRollupList = delegator.findList("ProductCategoryRollupAndChild", mainCond, null, orderBy, null, false);
        }
        
        context.resultList = categoryRollupList;
        request.setAttribute("topLevelList", categoryRollupList);
        if (UtilValidate.isNotEmpty(categoryRollupList)) 
        {
            catContentWrappers = HashMap.newInstance();
            subCatRollUpMap = HashMap.newInstance();
            currentCategories = LinkedList.newInstance();
            
            for (GenericValue categoryRollUp : categoryRollupList)
            {
                String mapKey = categoryRollUp.productCategoryId;
                if (!catContentWrappers.containsKey(mapKey)) 
                {
                    gvProductCategory = EntityQuery.use(delegator).from("ProductCategory").where("productCategoryId", mapKey).queryOne();
                    
                    CategoryContentWrapper productCategoryContentWrapper = new CategoryContentWrapper(gvProductCategory, request);
                    catContentWrappers.put(mapKey,productCategoryContentWrapper);
                    currentCategories.add(gvProductCategory);
                    
                    
                }
                paramsExpr = LinkedList.newInstance();
                paramsExpr.add(EntityCondition.makeCondition("parentProductCategoryId", EntityOperator.EQUALS, categoryRollUp.productCategoryId));
                paramCond = null;
                mainCond = null;
                if (UtilValidate.isNotEmpty(paramsExpr)) 
                {
                    paramCond = EntityCondition.makeCondition(paramsExpr, EntityOperator.AND);
                    mainCond = paramCond;
                }
                if (dateCond) 
                {
                    mainCond = EntityCondition.makeCondition([paramCond, dateCond], EntityOperator.AND);
                }
                List subCatRollupList = delegator.findList("ProductCategoryRollupAndChild", mainCond, null, orderBy, null, false);
                if(UtilValidate.isNotEmpty(subCatRollupList)) 
                {
                    subCatRollUpMap.put(mapKey, subCatRollupList);
                    context.subCatRollUpMap = subCatRollUpMap;
                    for (GenericValue subCategoryRollUp : subCatRollupList)
                    {
                        productCategoryId=subCategoryRollUp.productCategoryId;
                        if (!catContentWrappers.containsKey(productCategoryId)) 
                        {
                            gvProductCategory = EntityQuery.use(delegator).from("ProductCategory").where("productCategoryId", productCategoryId).queryOne();
                            
                            CategoryContentWrapper productCategoryContentWrapper = new CategoryContentWrapper(gvProductCategory, request);
                            catContentWrappers.put(productCategoryId,productCategoryContentWrapper);
                            currentCategories.add(gvProductCategory);
                            
                        }
                        
                    }
                    
                }
            }
            context.catContentWrappers = catContentWrappers;
            //Check if the catalog Active date is equal to the current date if so update the current categories in the session with this category list
            if (UtilValidate.isEmpty(catalogActiveDateTs) && UtilValidate.isEmpty(showAll))
            {
                catActiveStartDate =UtilDateTime.getDayStart(catalogActiveDateTs); 
                currentDayStartDate =UtilDateTime.getDayStart(UtilDateTime.nowTimestamp());
                Calendar cc= UtilDateTime.toCalendar(catActiveStartDate);
                Calendar cc2= UtilDateTime.toCalendar(currentDayStartDate);
                if (cc.isEquivalentTo(cc2))
                {
                      globalContext.currentCategories = currentCategories;
                    session.setAttribute("selectedCategories",currentCategories);
                }
                
            }
            
        }
    }
    
     if (errMsgList) 
     {
        request.setAttribute("_ERROR_MESSAGE_LIST_", errMsgList);
     }
    
}
