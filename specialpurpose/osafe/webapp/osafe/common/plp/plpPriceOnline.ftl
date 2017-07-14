<#if plpPrice?exists &&  plpPrice?has_content>
	<#assign CURRENCY_UOM_DEFAULT = Static["org.apache.ofbiz.osafe.util.Util"].getProductStoreParm(request,"CURRENCY_UOM_DEFAULT")!""/>
	 <#assign defaultCurrencyUom = session.getAttribute("defaultCurrencyUomId")>
	  <#if defaultCurrencyUom?has_content>
	  	<#assign CURRENCY_UOM_DEFAULT = defaultCurrencyUom>
	  </#if>
	<li class="${request.getAttribute("attributeClass")!}">
		<div class="js_plpPriceOnline" id="js_plpPriceOnline_${uiSequenceScreen}_${plpProduct.productId}">
		  <#if plpPrice?exists && plpPrice?has_content>
		    <label>${uiLabelMap.PlpPriceLabel}</label>
		    <span><@ofbizCurrency amount=plpPrice isoCode=CURRENCY_UOM_DEFAULT!productStore.defaultCurrencyUomId!"" rounding=globalContext.currencyRounding/></span> 
		    <#-- <span>${currencySymbol}${plpPrice?if_exists?string("##0.00")} </span> -->
		  </#if>
		</div>
		
		<div class="js_plpPriceOnline" id="js_plpPriceOnline_Virtual_${uiSequenceScreen}_${plpProduct.productId}" style="display:none">
		  <#if plpPrice?exists && plpPrice?has_content>
		    <label>${uiLabelMap.PlpPriceLabel}</label>
		    <span><@ofbizCurrency amount=plpPrice isoCode=CURRENCY_UOM_DEFAULT!productStore.defaultCurrencyUomId!"" rounding=globalContext.currencyRounding/></span>
		    <#-- <span>${currencySymbol}${plpPrice?if_exists?string("##0.00")} </span> -->
		  </#if>
		</div>
		
		<#if plpProductVariantMapKeys?exists && plpProductVariantMapKeys?has_content>
		    <#list plpProductVariantMapKeys as key>
		        <#assign productPriceMap = plpProductVariantPriceMap.get('${key}')!""/>
		        <#if productPriceMap?has_content>
		            <div class="plpPriceOnline" id="js_plpPriceOnline_${key}" style="display:none">
				        <label>${uiLabelMap.PlpPriceLabel}</label>
				        <span><@ofbizCurrency amount=productPriceMap.basePrice isoCode=CURRENCY_UOM_DEFAULT!productStore.defaultCurrencyUomId rounding=globalContext.currencyRounding /></span> -->
				       <#--  <span>${currencySymbol}${(productPriceMap.basePrice)?if_exists?string("##0.00")} </span> -->
				    </div>
		        </#if>  
		    </#list>
		</#if>
	</li>
</#if>