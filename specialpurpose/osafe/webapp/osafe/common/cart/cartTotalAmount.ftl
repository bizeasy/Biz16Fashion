<li class="${request.getAttribute("attributeClass")!}">
 <#assign defaultCurrencyUom = session.getAttribute("defaultCurrencyUomId")>
  <#if defaultCurrencyUom?has_content>
  	<#assign currencyUom = defaultCurrencyUom>
  </#if>
  <div>
    <label>${uiLabelMap.CartTotalCaption}</label>
    <span><@ofbizCurrency amount=orderGrandTotal! isoCode=currencyUom  rounding=globalContext.currencyRounding/></span>
   <#--   <span>${currencySymbol}${orderGrandTotal?if_exists?string("#0.00")} </span> -->
  </div>
</li>
