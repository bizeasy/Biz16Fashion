<!-- start searchBox -->
<#assign nowTimestamp=Static["org.apache.ofbiz.base.util.UtilDateTime"].nowTimestamp()>
<#assign weekStart=Static["org.apache.ofbiz.base.util.UtilDateTime"].addDaysToTimestamp(nowTimestamp,-6)>
<#assign monthStart=Static["org.apache.ofbiz.base.util.UtilDateTime"].addDaysToTimestamp(nowTimestamp,-30)>
<#assign productionDate=Static["org.apache.ofbiz.base.util.UtilProperties"].getPropertyValue('osafeAdmin','production-date')>
<#--${screens.render("component://osafeadmin/widget/OsafeadminScreens.xml#dateTimeIssue")}-->
<#if productionDate?has_content>
   <#assign productDateStart=Static["org.apache.ofbiz.base.util.UtilDateTime"].toTimestamp(productionDate)>
<#else>
   <#assign productDateStart=Static["org.apache.ofbiz.base.util.UtilDateTime"].toTimestamp('06/02/2011 00:00:00')>
</#if>
<div class="entryRow">
  <div class="entry daterange">
    <label>${uiLabelMap.PeriodCaption}</label>
    <div class="entryInput from">
      <input class="dateEntry" type="text" name="dateFrom" maxlength="40" value="${parameters.dateFrom!dateFrom!Static["org.apache.ofbiz.base.util.UtilDateTime"].toDateString(weekStart,entryDateTimeFormat)!""}"/>
    </div>
    <label class="tolabel">${uiLabelMap.ToCaption}</label>
    <div class="entryInput to">
      <input class="dateEntry" type="text" name="dateTo" maxlength="40" value="${dateTo!Static["org.apache.ofbiz.base.util.UtilDateTime"].toDateString(nowTimestamp,entryDateTimeFormat)!""}"/>
    </div>
  </div>
  <div class="dateSelectButtons">
    <input type="button" class="standardBtn dateSelect" name="TodayBtn" value="${uiLabelMap.TodayBtn}" onClick="setDateRange('${Static["org.apache.ofbiz.base.util.UtilDateTime"].toDateString(nowTimestamp,entryDateTimeFormat)}','${Static["org.apache.ofbiz.base.util.UtilDateTime"].toDateString(nowTimestamp,entryDateTimeFormat)}',${searchFormName!""});"/>
    <input type="button" class="standardBtn dateSelect" name="ThisWeekBtn" value="${uiLabelMap.LastSevenDaysBtn}" onClick="setDateRange('${Static["org.apache.ofbiz.base.util.UtilDateTime"].toDateString(weekStart,entryDateTimeFormat)}','${Static["org.apache.ofbiz.base.util.UtilDateTime"].toDateString(nowTimestamp,entryDateTimeFormat)}',${searchFormName!""});"/>
    <input type="button" class="standardBtn dateSelect" name="MonthToDateBtn" value="${uiLabelMap.LastThirtyDaysBtn}" onClick="setDateRange('${Static["org.apache.ofbiz.base.util.UtilDateTime"].toDateString(monthStart,entryDateTimeFormat)}','${Static["org.apache.ofbiz.base.util.UtilDateTime"].toDateString(nowTimestamp,entryDateTimeFormat)}',${searchFormName!""});"/>
    <input type="button" class="standardBtn dateSelect" name="DateAllBtn" value="${uiLabelMap.DateAllBtn}" onClick="setDateRange('${Static["org.apache.ofbiz.base.util.UtilDateTime"].toDateString(productDateStart,entryDateTimeFormat)}','${Static["org.apache.ofbiz.base.util.UtilDateTime"].toDateString(nowTimestamp,entryDateTimeFormat)}',${searchFormName!""});"/>
</div>
</div>
<div class="entryRow">
    <div class="entry long">
        <label>${uiLabelMap.StatusCaption} </label>
        <#assign intiCb = "${initializedCB}"/>
        <div class="entryInput checkbox medium">
            <input type="checkbox" class="checkBoxEntry" name="viewall" id="viewall" onclick="javascript:setCheckboxes('${searchFormName!""}','view')" value="Y"<#if parameters.viewall?has_content>checked</#if> />${uiLabelMap.AllLabel}
            <input type="checkbox" class="checkBoxEntry" name="viewApproved" id="viewApproved" value="Y" <#if parameters.viewApproved?has_content || viewApproved?has_content || ((intiCb?exists) && (intiCb == "N"))>checked</#if> />${uiLabelMap.ApprovedLabel}
            <input type="checkbox" class="checkBoxEntry" name="viewPending" id="viewPending" value="Y" <#if parameters.viewPending?has_content || viewPending?has_content || ((intiCb?exists) && (intiCb == "N"))>checked</#if> />${uiLabelMap.PendingLabel}
            <input type="checkbox" class="checkBoxEntry" name="viewDeleted" id="viewDeleted" value="Y" <#if parameters.viewDeleted?has_content || viewDeleted?has_content>checked</#if> />${uiLabelMap.DeletedLabel}    
        </div>
    </div>
</div>

<!-- end searchBox -->