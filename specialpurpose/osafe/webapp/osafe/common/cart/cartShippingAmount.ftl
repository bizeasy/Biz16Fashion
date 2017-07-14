<#if shippingApplies?exists && shippingApplies>
  <li class="${request.getAttribute("attributeClass")!}">
  <#assign defaultCurrencyUom = session.getAttribute("defaultCurrencyUomId")>

+  <#if defaultCurrencyUom?has_content>

+  	<#assign currencyUom = defaultCurrencyUom>

+  </#if>

      <div>
      <label>${cartShippingLabel!uiLabelMap.CartShippingAndHandlingCaption}</label>
      <span><@ofbizCurrency amount=orderShippingTotal! isoCode=currencyUom  rounding=globalContext.currencyRounding/></span> 
     <#--  <span>${currencySymbol}${orderShippingTotal?if_exists?string("#0.00")} </span> -->
    </div>
  </li>
</#if>