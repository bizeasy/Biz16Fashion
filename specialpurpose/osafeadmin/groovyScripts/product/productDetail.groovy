package product;

import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.product.product.ProductContentWrapper;
import org.apache.ofbiz.product.product.ProductWorker;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.condition.EntityCondition;
import org.apache.ofbiz.entity.condition.EntityOperator;
import java.util.LinkedList;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.base.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.ofbiz.base.util.UtilProperties;

if (UtilValidate.isNotEmpty(parameters.productId)) 
{
    product = delegator.findOne("Product",["productId":parameters.productId], false);
    
    virtualProductContentList = LinkedList.newInstance();
    productContentList = LinkedList.newInstance();
    context.product = product;
    
    virtualProduct = null;
    variantProduct = null;
    finishedProduct = null;
    currentProduct = null
    
    isVirtual = "N";
    isVariant = "N";
    isFinished = "N";
    
    if(UtilValidate.isNotEmpty(product))
    { 
        currentProduct = product;
        if("Y".equals(product.getString("isVirtual")))
	    {
	        virtualProduct = product;
	        isVirtual = "Y";
	    } else if("Y".equals(product.getString("isVariant")))
	    {
	        variantProduct = product;
	        isVariant = "Y";
	        GenericValue parent = ProductWorker.getParentProduct(variantProduct.productId, delegator);
	        if(UtilValidate.isNotEmpty(parent))
	        {
	            virtualProduct = parent;
	        }
	    } else
	    {
	        finishedProduct = product;
	        isFinished = "Y";
	    }
    }
    context.virtualProduct = virtualProduct;
    context.variantProduct = variantProduct;
    context.finishedProduct = finishedProduct;
    context.currentProduct = currentProduct;
    
    context.isVirtual = isVirtual;
    context.isVariant = isVariant;
    context.isFinished = isFinished;
    // get the content wrapper
    
    
    if("Y".equals(product.getString("isVariant")))
	{
		GenericValue parent = ProductWorker.getParentProduct(product.productId, delegator);
		if (parent != null)
		{
		    productContentWrapper = new ProductContentWrapper(parent, request);
     		productContentList = product.getRelated("ProductContent");
        	productContentList = EntityUtil.filterByDate(productContentList,true);

     		virtualProductContentList =  parent.getRelated("ProductContent");
     		virtualProductContentList = EntityUtil.filterByDate(virtualProductContentList,true);
	    }
	}
    else
    {
        productContentWrapper = new ProductContentWrapper(product, request);
        productContentList = product.getRelated("ProductContent"); 
        productContentList = EntityUtil.filterByDate(productContentList,true);
    }
    // render content for varaint group use 0 index variant id 
    if (UtilValidate.isNotEmpty(context.passedVariantProductIds) || UtilValidate.isNotEmpty(parameters.variantProductIds))
    {
        String varaintProductId = "";
        if (UtilValidate.isNotEmpty(parameters.variantProductIds))
        {
            variantProductIdList = StringUtil.split(parameters.variantProductIds, "|");
            if (UtilValidate.isNotEmpty(variantProductIdList))
            {
                varaintProductId = variantProductIdList[0];
            }
        }
        else if(UtilValidate.isNotEmpty(context.passedVariantProductIds))
        {
            varaintProductId = context.passedVariantProductIds.first();
        }
		GenericValue parent = ProductWorker.getParentProduct(varaintProductId, delegator);
        productContentList = delegator.findByAnd("ProductContent", UtilMisc.toMap("productId" ,varaintProductId));
        productContentList = EntityUtil.filterByDate(productContentList,true);
 		virtualProductContentList = parent.getRelated("ProductContent");
 		virtualProductContentList = EntityUtil.filterByDate(virtualProductContentList,true);
    }

	if (UtilValidate.isNotEmpty(productContentList))
	{
            for (GenericValue productContent: productContentList) 
            {
    		   productContentTypeId = productContent.productContentTypeId;
    		   context.put(productContent.productContentTypeId,productContent);
            }
            context.productContentList = productContentList;
	}
	if (UtilValidate.isNotEmpty(virtualProductContentList))
	{
        for (GenericValue productContent: virtualProductContentList) 
        {
		   productContentTypeId = productContent.productContentTypeId;
		   if (productContentTypeId.equals("PRODUCT_NAME") || productContentTypeId.equals("PLP_LABEL") || productContentTypeId.equals("PDP_LABEL")) 
		   {
		       context.put(productContent.productContentTypeId,productContent);
		   }
        }
        context.virtualProductContentList = virtualProductContentList;
	}

    
    String productDetailHeading = "";
    if (UtilValidate.isNotEmpty(productContentWrapper))
    {
       context.productContentWrapper = productContentWrapper;
       productDetailHeading = StringEscapeUtils.unescapeHtml(productContentWrapper.get("PRODUCT_NAME").toString());
    }
    if (UtilValidate.isNotEmpty(product))
    {
        if (UtilValidate.isEmpty(productDetailHeading)) 
        {
            productDetailHeading = product.get("productName");
        }
        if (UtilValidate.isEmpty(productDetailHeading)) 
        {
            productDetailHeading = product.get("internalName");
        }
    	ecl = EntityCondition.makeCondition([
    	  EntityCondition.makeCondition("productId", EntityOperator.EQUALS, product.productId),
    	  EntityCondition.makeCondition("primaryParentCategoryId", EntityOperator.NOT_EQUAL, null),
         ],
    	EntityOperator.AND);

    	prodCatList = delegator.findList("ProductCategoryAndMember", ecl, null, null, null, false);
		context.prodCatList = EntityUtil.filterByDate(prodCatList,true);
    }
    
    context.productDetailHeading = productDetailHeading;
}