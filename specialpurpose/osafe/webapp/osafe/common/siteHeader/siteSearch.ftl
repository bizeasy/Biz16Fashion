<#-- <div class="${request.getAttribute("attributeClass")!}">
${screens.render("component://osafe/widget/EcommerceContentScreens.xml#SI_SEARCH")}
</div>-->
<script type="text/javascript">
 
 function clearIt(what)
 {
 if(what.value == what.defaultValue) what.value = '';
 }
 function setIt(what)
 {
 if(what.value == '') what.value = what.defaultValue;
 }
 </script>
 
 <form id="frmSearchForm" onsubmit="return submitSearchForm(document.frmSearchForm);" action="siteSearch" name="frmSearchForm" method="get">
 
         <fieldset class="formstyle" title="Search this site...">
             <div id="searchContainer" class="targetMobile">
                 <div id="searchButton">
                     <input type="submit" class="searchSubmit" value="" />
                 </div>
                 <div id="searchField">
                     <input type="text" value="${SEARCH_DEFAULT_TEXT!""}" name="searchText" id="searchText" onblur="setIt(this)" onfocus="clearIt(this)"/>
                 </div>
 
             </div>
         </fieldset>
 	<div class="searchAutoComplete" id="searchAutoComplete"></div>
     </form>