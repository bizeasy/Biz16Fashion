import org.apache.ofbiz.base.util.UtilValidate;
import java.util.HashMap;
import java.util.LinkedList;
import java.lang.*;
import org.apache.ofbiz.entity.GenericValue;

personTitleList = delegator.findByAndCache("Enumeration", [enumTypeId : "PERSONAL_TITLE"], ["sequenceId"]);
if(UtilValidate.isNotEmpty(personTitleList))
{
    personTitles = LinkedList.newInstance();
    for (GenericValue personTitle :  personTitleList) 
    {
        if(UtilValidate.isNotEmpty(personTitle.sequenceId) && (personTitle.sequenceId instanceof String) && (UtilValidate.isInteger(personTitle.sequenceId)))
        {
            seqId = Integer.parseInt(personTitle.sequenceId);
            if(seqId > 0)
            {
                personTitles.add(personTitle);
            }
        }
    }
}
context.personTitleList = personTitles;