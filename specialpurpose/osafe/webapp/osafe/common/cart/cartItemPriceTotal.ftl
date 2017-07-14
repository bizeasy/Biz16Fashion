<li class="${request.getAttribute("attributeClass")!}<#if lineIndex == 0> firstRow</#if>">
   <#assign defaultCurrencyUom = session.getAttribute("defaultCurrencyUomId")>
  <#if defaultCurrencyUom?has_content>
  	<#assign currencyUom = defaultCurrencyUom>
  </#if>
  <div>
    <label>${uiLabelMap.CartItemPriceTotalCaption}</label>
    <span><@ofbizCurrency amount=itemSubTotal isoCode=currencyUom rounding=globalContext.currencyRounding/></span> 
    <#--  <span>${currencySymbol}${itemSubTotal?if_exists?string("##0.00")} </span> --> 
  </div>
</li>

