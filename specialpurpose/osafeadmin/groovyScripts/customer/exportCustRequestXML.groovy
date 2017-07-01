package customer;

import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.base.util.UtilDateTime;
import org.apache.ofbiz.osafe.util.OsafeAdminUtil;
import org.apache.ofbiz.base.util.*;
import org.apache.ofbiz.entity.GenericValue;
import java.util.HashMap;
import java.util.LinkedList;
import org.apache.ofbiz.product.store.ProductStoreWorker;

custRequestList=session.getAttribute("custRequestList");

List custRequestIdList = new ArrayList();
if (UtilValidate.isNotEmpty(custRequestList)) 
{
    for(Map custRequestInfo : custRequestList)
    {
        custRequest = custRequestInfo.CustRequest;
        custRequestIdList.add(custRequest.custRequestId);
    }
}
context.exportIdList = custRequestIdList
