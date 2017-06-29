import org.apache.ofbiz.base.util.UtilValidate;
import java.util.HashMap;
import java.util.LinkedList;
import java.lang.*;
import org.apache.ofbiz.entity.GenericValue;

personTitleList = EntityQuery.use(delegator).from("Enumeration").where("enumTypeId", "PERSONAL_TITLE").orderBy(UtilMisc.toList("sequenceId")).cache().queryList();
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