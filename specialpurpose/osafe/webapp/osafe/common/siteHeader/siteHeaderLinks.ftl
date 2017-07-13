<table>
	<tr>
		<td>
			<div class="content links siteHeaderLinks">
				<div id="siteHeaderLinks">
					<ul>
						${uiLabelMap.ProductStore} 
						<select name ="globalProductStoreId" id="globalProductStoreId" onchange="javascript:setGlobalProductStore();">
							<#list productStoresList as productStore >
							  <#if globalContext.productStoreId?has_content>
							    <option value='${productStore.productStoreId}'<#if productStore.productStoreId == globalContext.productStoreId> selected</#if>>${productStore.productStoreId!""}</option>
							  <#else>
							  	<option value='${productStore.productStoreId}'>${productStore.productStoreId!""}</option>
							  </#if>  
							</#list>
						</select>
					</ul>  
				</div>
			</div>
		</td>
		<td>
		<#assign availableLocales = Static["org.apache.ofbiz.base.util.UtilMisc"].availableLocales()/>
			<div class="content links siteHeaderLinks">
				<div id="siteHeaderLinks">
					
						${uiLabelMap.Language}
						<select name ="globalLocaleId" id="globalLocaleId" onchange="setSessionLocale();">
							<#list availableLocales as availableLocale>
							<#if availableLocale.toString()=="en" || availableLocale.toString()=="en_US" || availableLocale.toString()=="tr_TR">
						      <#assign langAttr = availableLocale.toString()?replace("_", "-")>
						      <#if session.getAttribute("locale")?has_content>
							    <option value='${availableLocale.toString()}'<#if availableLocale == session.getAttribute("locale")> selected</#if>>${availableLocale.getDisplayName(availableLocale)}&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp; [${langAttr}]</option>
							  <#else>
							  	<option value='${availableLocale.toString()}'>${availableLocale.getDisplayName(availableLocale)}&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp; [${langAttr}]</option>
							  </#if> 
						      </#if>
						    </#list>
						</select>
				</div>
			</div>
		</td>
		<td>
			<div class="${request.getAttribute("attributeClass")!}">
			${screens.render("component://osafe/widget/EcommerceContentScreens.xml#SI_LINKS")}
			</div>
		</td>
	</tr>
</table>


<script language="JavaScript" type="text/javascript">

	function setGlobalProductStore() {
		var url      = window.location.href ;
		var gpiIndex = url.indexOf("?globalProductStoreId=");
		if(gpiIndex > 0){
			url = url.substring(0,gpiIndex);
		}
		url = url + "?globalProductStoreId="+ document.getElementById("globalProductStoreId").value;
		window.location.replace(url);
	}
	function setSessionLocale() {
		var id = jQuery("#globalLocaleId").val();
		var url= '<@ofbizUrl>/setSessionLocale?newLocale='+id+'</@ofbizUrl>';
         jQuery.ajax({
            url: url,
            type: 'POST',
            async: false,
            error: function(msg) {
                alert("Error While Setting Locale");
            },
            success: function(msg) {
                window.location.reload();
            }
        });
		
	}


</script>

