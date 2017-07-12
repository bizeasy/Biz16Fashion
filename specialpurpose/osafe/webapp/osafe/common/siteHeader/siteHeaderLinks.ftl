<table>
	<tr>
		<td>
			<div class="content links siteHeaderLinks">
				<div id="siteHeaderLinks">
					<ul>
						Product Store: 
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


</script>

