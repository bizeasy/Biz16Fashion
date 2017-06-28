import org.apache.ofbiz.base.util.UtilValidate;
import java.util.HashMap;
import java.util.LinkedList;
import java.lang.*;
import org.apache.ofbiz.entity.GenericValue;

if (UtilValidate.isNotEmpty(context.enumTypeId)) 
{
    enumTypeList = delegator.findByAndCache("Enumeration", [enumTypeId : context.enumTypeId], ["sequenceId"]);
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