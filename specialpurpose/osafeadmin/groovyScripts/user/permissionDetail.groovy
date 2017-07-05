package user;

import org.apache.commons.lang.StringUtils;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.util.EntityQuery;

permissionId = StringUtils.trimToEmpty(parameters.permissionId);
context.permissionId = permissionId;
if (UtilValidate.isNotEmpty(permissionId))
{
	permissions = EntityQuery.use(delegator).from("SecurityPermission").where("permissionId", permissionId).queryOne();
	context.permissions = permissions;
}