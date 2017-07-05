package shipping;;

import java.util.List;
import java.util.Map;

import java.util.LinkedList;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.product.store.ProductStoreWorker;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.entity.util.EntityQuery;
import org.apache.ofbiz.entity.condition.*;
import org.apache.ofbiz.base.util.*;
import org.apache.ofbiz.entity.*;
import org.apache.ofbiz.entity.util.*;
import org.apache.ofbiz.entity.model.DynamicViewEntity;
import org.apache.ofbiz.entity.model.ModelKeyMap;
import org.apache.ofbiz.entity.util.EntityFindOptions;


productStoreId=globalContext.productStoreId;
partyId=parameters.carrierPartyId;

//get carrier info
if (UtilValidate.isNotEmpty(partyId))
{
    carrier = EntityQuery.use(delegator).from("PartyGroup").where("partyId", partyId).queryOne();
    
    context.carrier = carrier;
    
}
partyContent = EntityUtil.getFirst(delegator.findByAnd("PartyContent", [partyId : partyId, 	partyContentTypeId : "TRACKING_URL"], null, false));
if (UtilValidate.isNotEmpty(partyContent)) 
{
	content = partyContent.getRelatedOne("Content");
	if (UtilValidate.isNotEmpty(content))
	{
	   context.trackingUrlId = content.contentId;
	   dataResource = content.getRelatedOne("DataResource");
	   if (UtilValidate.isNotEmpty(dataResource))
	   {
		  context.trackingUrlDataResourceId = dataResource.dataResourceId;
	   }
	}
}




