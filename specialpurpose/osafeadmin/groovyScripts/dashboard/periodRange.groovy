
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

context.periodFrom ="";
context.periodTo ="";
String periodFrom = request.getParameter("periodFrom");
String periodTo = request.getParameter("periodTo");
Timestamp periodFromTs = null;
Timestamp periodToTs = null;
Boolean isValidDate = true;

Debug.log("periodFromTs test ================");
if(UtilValidate.isEmpty(periodFrom))
{
	Debug.log("periodFrom ================"+periodFrom);
    String periodFromSess = session.getAttribute("periodFrom");
    Debug.log("periodFromSess ================"+periodFromSess);
    periodFrom = periodFromSess;
    if(UtilValidate.isEmpty(periodFrom))
    {
    	Debug.log("periodFrom ================"+periodFrom);
        periodFromTs = UtilDateTime.getDayStart(nowTs);
        Debug.log("periodFromTs ================"+periodFromTs);
        Debug.log("entryDateTimeFormat ================"+entryDateTimeFormat);
        Debug.log("timeZone ================"+timeZone);
        Debug.log("locale ================"+locale);
        periodFrom = UtilDateTime.timeStampToString(periodFromTs, "MM/dd/yy h:mm", timeZone, locale);
        Debug.log("periodFrom ================"+periodFrom);
    }
}

Debug.log("periodFromTs ================"+periodFromTs);

if(UtilValidate.isEmpty(periodTo))
{
    String periodToSess = session.getAttribute("periodTo");
    periodTo = periodToSess;
    if(UtilValidate.isEmpty(periodTo))
    {
        periodToTs = nowTs;
        periodTo = UtilDateTime.timeStampToString(periodToTs, entryDateTimeFormat, timeZone, locale);
    }
}

if(UtilValidate.isNotEmpty(periodFrom))
{
    if(OsafeAdminUtil.isDateTime(periodFrom, entryDateTimeFormat))
    {
        periodFromTs =ObjectType.simpleTypeConvert(periodFrom, "Timestamp", entryDateTimeFormat, locale);
        context.periodFrom = periodFrom;
        context.periodFromTs = periodFromTs;
        session.setAttribute("periodFrom", periodFrom);
    }
    else
    {
        isValidDate = false;
    }
}

if(UtilValidate.isNotEmpty(periodTo)) 
{
    if(OsafeAdminUtil.isDateTime(periodTo, entryDateTimeFormat))
    {
        periodToTs =ObjectType.simpleTypeConvert(periodTo, "Timestamp", entryDateTimeFormat, locale);
        context.periodTo = periodTo;
        context.periodToTs = periodToTs;
        session.setAttribute("periodTo", periodTo);
    }
    else
    {
        isValidDate = false;
    }
    
}
context.isValidDate = isValidDate;
context.periodFrom = periodFrom;
context.periodTo = periodTo;
