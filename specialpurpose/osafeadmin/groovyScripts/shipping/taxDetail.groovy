package shipping;

import java.util.List;
import java.util.Map;

import java.util.LinkedList;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.base.util.*;
import org.apache.ofbiz.entity.util.*;


productStoreId=globalContext.productStoreId;
taxAuthorityRateSeqId = parameters.taxAuthorityRateSeqId;

if (UtilValidate.isNotEmpty(taxAuthorityRateSeqId))
{
    taxAuthorityRateProduct = EntityQuery.use(delegator).from("TaxAuthorityRateProduct").where("taxAuthorityRateSeqId", taxAuthorityRateSeqId).queryOne();
    
    
    if(UtilValidate.isNotEmpty(taxAuthorityRateProduct)) 
    {
        context.taxAuthorityRateProduct = taxAuthorityRateProduct;
    }
}





