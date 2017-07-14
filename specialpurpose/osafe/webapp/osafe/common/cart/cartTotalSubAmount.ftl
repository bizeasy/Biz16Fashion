<li class="${request.getAttribute("attributeClass")!}">
	   <#assign defaultCurrencyUom = session.getAttribute("defaultCurrencyUomId")>
	  <#if defaultCurrencyUom?has_content>
	  	<#assign currencyUom = defaultCurrencyUom>
	  </#if>
  <div>
    <label>${uiLabelMap.SubTotalCaption}</label>
    <#if cartSubTotal?exists && cartSubTotal?has_content>
   	<span><@ofbizCurrency amount=cartSubTotal! isoCode=currencyUom  rounding=globalContext.currencyRounding/></span> 
    <#--  <span>${currencySymbol}${cartSubTotal?if_exists?string("##0.00")} </span> -->
    </#if>
  </div>
</li>