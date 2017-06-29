package admin;

import java.util.LinkedList;
import java.util.HashMap;
import org.apache.ofbiz.base.util.UtilMisc;

//payment gateway settings
adminToolsList = LinkedList.newInstance();
adminTool = HashMap.newInstance();
adminTool.put("toolType", uiLabelMap.AdminPayGatewayHeading);
adminTool.put("toolDesc", uiLabelMap.AdminPayGatewayInfo);
adminTool.put("toolDetail", "paymentGatewayConfigList");
adminToolsList.add(adminTool);

//titles
adminTool = HashMap.newInstance();
adminTool.put("toolType", uiLabelMap.AdminPaymentSettingHeading);
adminTool.put("toolDesc", uiLabelMap.AdminPaymentSettingInfo);
adminTool.put("toolDetail", "paymentSettingManagement");
adminToolsList.add(adminTool);

//credit card type
adminTool = HashMap.newInstance();
adminTool.put("toolType", uiLabelMap.ManageCreditCardTypeHeading);
adminTool.put("toolDesc", uiLabelMap.ManageCreditCardTypeInfo);
adminTool.put("toolDetail", "creditCardTypeList");
adminToolsList.add(adminTool);

context.resultList = adminToolsList;