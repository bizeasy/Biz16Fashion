package personalInfo;

import org.apache.ofbiz.base.util.string.FlexibleStringExpander;
import org.apache.ofbiz.base.util.UtilProperties;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.base.util.UtilHttp;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.osafe.services.OsafeManageXml;
import java.util.HashMap;

customPartyAttributeList = null;
Map<String, Object> svcCtx = HashMap.newInstance();
svcCtx.put("useCache", "true");
partyCustomAttributeListRes = dispatcher.runSync("getPartyCustomAttributeList", svcCtx);
//CustomPartyAttributeServices.getPartyCustomAttributeList();

context.customPartyAttributeList = partyCustomAttributeListRes.get("customPartyAttributeList");

