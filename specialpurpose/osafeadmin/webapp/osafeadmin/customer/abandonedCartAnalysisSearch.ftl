<#assign monthBackStamp=Static["org.apache.ofbiz.osafe.util.OsafeAdminUtil"].getMonthBackTimeStamp(1)>
<#assign defaultTodate=Static["org.apache.ofbiz.base.util.UtilDateTime"].getMonthEnd(monthBackStamp,timeZone,locale)>
<#assign monthBackStamp=Static["org.apache.ofbiz.osafe.util.OsafeAdminUtil"].getMonthBackTimeStamp(3)>
<#assign defaultFromdate=Static["org.apache.ofbiz.base.util.UtilDateTime"].getMonthStart(monthBackStamp,timeZone,locale)>
<#--${screens.render("component://osafeadmin/widget/OsafeadminScreens.xml#dateTimeIssue")}-->
<!-- start searchBox -->
<div class="entryRow">
  <div class="entry daterange">
    <label>${uiLabelMap.FromDateCaption}</label>
    <div class="entryInput from">
      <input class="dateEntry" type="text" name="dateFrom" maxlength="40" value="${parameters.dateFrom!dateFrom!Static["org.apache.ofbiz.base.util.UtilDateTime"].toDateString(defaultFromdate,entryDateTimeFormat)!""}"/>
    </div>
    <label class="tolabel">${uiLabelMap.ToCaption}</label>
    <div class="entryInput to">
      <input class="dateEntry" type="text" name="dateTo" maxlength="40" value="${parameters.dateTo!dateTo!Static["org.apache.ofbiz.base.util.UtilDateTime"].toDateString(defaultTodate,entryDateTimeFormat)!""}"/>
    </div>
  </div>
</div>
<!-- end searchBox -->