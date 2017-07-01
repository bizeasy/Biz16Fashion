package admin;

import java.util.LinkedList
import java.util.HashMap

import org.apache.commons.lang.StringUtils
import org.apache.ofbiz.base.util.FileUtil
import org.apache.ofbiz.base.util.UtilMisc
import org.apache.ofbiz.base.util.UtilProperties
import org.apache.ofbiz.base.util.UtilValidate
import org.apache.ofbiz.base.util.string.FlexibleStringExpander

import org.apache.ofbiz.osafe.services.OsafeManageXml

currentMode=context.mode;

ecommerceConfigPath = FlexibleStringExpander.expandString(UtilProperties.getPropertyValue("osafeAdmin.properties", "ecommerce-config-path"), context);

parameterFileList = LinkedList.newInstance();
bigfishParamList = LinkedList.newInstance();
parameterFileName = "";

// Get the all ecommerce config file
fileList = FileUtil.findFiles("xml", ecommerceConfigPath, null, null);
if(UtilValidate.isNotEmpty(fileList))
{
    for (parameterFile in fileList)
    {
        if (parameterFile.getName().startsWith("Parameters_") || parameterFile.getName().startsWith("parameters_"))
        {
            infoMap = HashMap.newInstance();
            infoMap.put("file", parameterFile);
            infoMap.put("fileName", parameterFile.getName());
            infoMap.put("fileNameUpperCase", parameterFile.getName().toUpperCase());
            parameterFileList.add(infoMap);
        }
    }
    parameterFileList = UtilMisc.sortMaps(parameterFileList, UtilMisc.toList("fileNameUpperCase"));
    parameterFileName = parameterFileList.get(0).get("fileName");
}

// search bigfish parameter according to passed file name
searchFileName = StringUtils.trimToEmpty(parameters.parameterFileName);
if (UtilValidate.isNotEmpty(searchFileName))
{
    parameterFileName = searchFileName;
    for (parameterFile in parameterFileList)
    {
        if (parameterFile.get("fileName").equalsIgnoreCase(searchFileName))
        {
			bigfishParamList =  OsafeManageXml.getListMapsFromXmlFile(parameterFile.get("file").getAbsolutePath());
			break;
        }
    }
	if("add".equalsIgnoreCase(currentMode))
	{
		emptyAddMap = HashMap.newInstance();
		bigfishParamList.add(emptyAddMap);
	}
}

context.parameterFileList = parameterFileList; 
context.parameterFileName = parameterFileName; 
context.bigfishParamList = bigfishParamList; 
