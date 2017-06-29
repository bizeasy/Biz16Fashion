package jobs;

import java.util.LinkedList;
import java.util.HashMap;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.base.util.*
import org.apache.ofbiz.base.util.string.*;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

Map<String, Object> svcCtx = HashMap.newInstance();
userLogin = session.getAttribute("userLogin");
svcCtx.put("userLogin", userLogin);

runtimeDataId = StringUtils.trimToEmpty(parameters.runtimeDataId);
context.runtimeDataId = runtimeDataId;
if (UtilValidate.isNotEmpty(runtimeDataId))
{
	runtimeData = EntityQuery.use(delegator).from("RuntimeData").where(UtilMisc.toMap("runtimeDataId", runtimeDataId)).queryOne();
	
	context.runtimeData = runtimeData;
}


jobId = StringUtils.trimToEmpty(parameters.jobId);
context.jobId = jobId;
if (UtilValidate.isNotEmpty(jobId))
{
	schedJob = EntityQuery.use(delegator).from("JobSandbox").where(UtilMisc.toMap("jobId",jobId)).queryOne()
	
	context.schedJob = schedJob;
	detailInfoBoxHeading = FlexibleStringExpander.expandString(UtilProperties.getMessage("OSafeAdminUiLabels","RuntimeDataParametersHeading",locale), context);
	
	context.detailInfoBoxHeading  = detailInfoBoxHeading;
}