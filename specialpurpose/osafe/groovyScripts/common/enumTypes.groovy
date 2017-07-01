import org.apache.ofbiz.base.util.UtilValidate;
import java.util.HashMap;
import java.util.LinkedList;
import java.lang.*;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.util.EntityQuery;

if (UtilValidate.isNotEmpty(context.enumTypeId)) 
{
    enumTypeList = EntityQuery.use(delegator).from("Enumeration").where("enumTypeId", context.enumTypeId).orderBy(["sequenceId"]).cache().queryList();
    if(UtilValidate.isNotEmpty(enumTypeList))
    {
        processEnumTypes = LinkedList.newInstance();
        for (GenericValue enumType :  enumTypeList) 
        {
            if(UtilValidate.isNotEmpty(enumType.sequenceId) && (enumType.sequenceId instanceof String) && (UtilValidate.isInteger(enumType.sequenceId)))
            {
                seqId = Integer.parseInt(enumType.sequenceId);
                if(seqId > 0)
                {
                    processEnumTypes.add(enumType);
                }
            }
        }
        context.enumTypes = processEnumTypes;
    }
    
}