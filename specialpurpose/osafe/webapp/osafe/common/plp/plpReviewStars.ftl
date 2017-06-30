<#assign reviewMethod = Static["org.apache.ofbiz.osafe.util.Util"].getProductStoreParm(request,"REVIEW_METHOD")!""/>
<#if reviewMethod?has_content >
	<#if (reviewMethod.toUpperCase() == "BIGFISH") || (reviewMethod.toUpperCase() == "REEVOO")>
          <li class="${request.getAttribute("attributeClass")!}">
	    	<#if reviewMethod.toUpperCase() == "REEVOO">
	    		<#if Static["org.apache.ofbiz.osafe.util.Util"].isProductStoreParmTrue(request,"REEVOO_SHOW_ON_PLP")>
			        <#assign reevooBadgeurl = Static["org.apache.ofbiz.osafe.util.Util"].getProductStoreParm(request,"REEVOO_BADGE_URL")!""/>
			        <#assign reevooTrkref = Static["org.apache.ofbiz.osafe.util.Util"].getProductStoreParm(request,"REEVOO_TRKREF")!""/>
			        <#assign reevooSku = "">
			        <#assign skuProduct = delegator.findOne("GoodIdentification", Static["org.apache.ofbiz.base.util.UtilMisc"].toMap("productId", plpProductId!"", "goodIdentificationTypeId", "SKU"), true)?if_exists />
			        <#if skuProduct?has_content>
			            <#assign reevooSku = skuProduct.idValue!"">
			        <#else>
			            <#assign reevooSku = plpProductId!"">
			        </#if>
			        <#assign reevooBadgeurl = reevooBadgeurl.concat("/").concat(reevooTrkref!"").concat("/").concat(reevooSku!"")>
			        <div class="reevoo-area">
			            <a class="reevoomark" href="${reevooBadgeurl}">${uiLabelMap.ReevooProductReviewLabel}</a>
			        </div>
		        </#if>
	        </#if>
	    	<#if reviewMethod.toUpperCase() == "BIGFISH">
		        <#if plpAverageStarRating?has_content>
		            <#assign ratePercentage= ((plpAverageStarRating / 5) * 100)>
		            <div class="rating_bar"><div style="width:${ratePercentage}%"></div></div>
		        </#if>
	        </#if>
		 </li>
	</#if>
</#if>