<div class="actionIconMenu">
    <a class="toolIcon" href="javascript:void(o);"></a>
    <div class="actionIconBox" style="display:none">
    <div class="actionIcon">
    <p>${uiLabelMap.RelatedEmailTemplatesHelpInfo!}</p>
        <ul>
        	<#list templates as template>
		        <li>${Static["org.apache.ofbiz.osafe.util.Util"].getFormattedText(template)}</li>
	        </#list>
        </ul>
   </div>
   </div>
</div>    