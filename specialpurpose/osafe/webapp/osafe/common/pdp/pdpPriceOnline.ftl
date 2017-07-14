<#assign CURRENCY_UOM_DEFAULT = Static["org.apache.ofbiz.osafe.util.Util"].getProductStoreParm(request,"CURRENCY_UOM_DEFAULT")!""/>
<li class="${request.getAttribute("attributeClass")!}">
	 <#assign defaultCurrencyUom = session.getAttribute("defaultCurrencyUomId")>
  <#if defaultCurrencyUom?has_content>
  	<#assign CURRENCY_UOM_DEFAULT = defaultCurrencyUom>
  </#if>
	<#if pdpPriceMap?exists && pdpPriceMap?has_content && pdpPriceMap.price?has_content>
	  <div class="pdpPriceOnline" id="js_pdpPriceOnline">
	    <label>${uiLabelMap.OnlinePriceCaption}</label>
		 <span><@ofbizCurrency amount=pdpPriceMap.price isoCode=CURRENCY_UOM_DEFAULT!pdpPriceMap.currencyUsed rounding=globalContext.currencyRounding /></span> 
	    <#--  <span>${currencySymbol}${(pdpPriceMap.price)?if_exists?string("##0.00")} </span> -->
	  </div>
	  <div class="pdpPriceOnline" id="js_pdpPriceOnline_Virtual" style="display:none">
	    <label>${uiLabelMap.OnlinePriceCaption}</label>
	    <span><@ofbizCurrency amount=pdpPriceMap.price isoCode=CURRENCY_UOM_DEFAULT!pdpPriceMap.currencyUsed rounding=globalContext.currencyRounding /></span> 
	    <#-- <span>${currencySymbol}${(pdpPriceMap.price)?if_exists?string("##0.00")} </span> -->
	  </div>
	</#if>
	
	<#if productVariantMapKeys?exists && productVariantMapKeys?has_content>
	  <#list productVariantMapKeys as key>
	    <#assign productPrice = productVariantPriceMap.get('${key}')!""/>
	    <#if productPrice?has_content>
	      <div class="pdpPriceOnline" id="js_pdpPriceOnline_${key}" style="display:none">
	        <label>${uiLabelMap.OnlinePriceCaption}</label>
	        <span><@ofbizCurrency amount=productPrice.basePrice isoCode=CURRENCY_UOM_DEFAULT!productPrice.currencyUsed rounding=globalContext.currencyRounding /></span> 
	        <#-- <span>${currencySymbol}${(productPrice.basePrice)?if_exists?string("##0.00")} </span> -->
	      </div>
	    </#if>
	  </#list>
	</#if>
</li>