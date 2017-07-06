
package  dashboard;

import org.apache.ofbiz.base.util.UtilProperties;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.base.util.ObjectType;

import java.util.Locale;
import java.util.TimeZone;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import java.util.LinkedList;
import java.util.HashMap;
import org.apache.ofbiz.base.util.*;
import org.apache.ofbiz.entity.*;
import org.apache.ofbiz.entity.util.*;
import org.apache.ofbiz.entity.condition.*;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.product.product.ProductContentWrapper;
import org.apache.ofbiz.product.product.ProductWorker;

import com.ibm.icu.util.Calendar;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression.Context;

import org.apache.ofbiz.osafe.util.OsafeAdminUtil;

nowTs = UtilDateTime.nowTimestamp();

String periodFrom = "";
String periodTo = "";
Timestamp periodFromTs = null;
Timestamp periodToTs = null;
Boolean isValidDate = true;


if(UtilValidate.isEmpty(periodTo))
{
        periodToTs = nowTs;
        periodTo = UtilDateTime.timeStampToString(periodToTs, entryDateTimeFormat, timeZone, locale);
}
if(UtilValidate.isEmpty(periodFrom))
{
        periodFromTs =nowTs;
        periodFrom = UtilDateTime.timeStampToString(periodFromTs, entryDateTimeFormat, timeZone, locale);
    
}
Debug.log("periodFrom ================"+periodFrom);
//customers
context.periodFrom = periodFrom;
context.periodTo = periodTo;
//customers-->abandedAnalysis and reviews-->reviewSummary
context.dateFrom = periodFrom;
context.dateTo = periodTo;
//add Promotion 
context.fromDate=periodFrom;
//reviews-->viewRevies
context.from=periodFrom;
context.to = periodTo;
