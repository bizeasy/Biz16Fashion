<li class="${request.getAttribute("attributeClass")!}<#if lineIndex == 0> firstRow</#if>">
  <#assign defaultCurrencyUom = session.getAttribute("defaultCurrencyUomId")>
  <#if defaultCurrencyUom?has_content>
  	<#assign currencyUom = defaultCurrencyUom>
  </#if>
  <div>
    <#if (offerPriceVisible?has_content) && offerPriceVisible == "Y" >
      <label>${uiLabelMap.CartItemOfferPriceCaption}</label>
      <#if offerPrice?exists && offerPrice?has_content>
	     <span><@ofbizCurrency amount=offerPrice isoCode=currencyUom rounding=globalContext.currencyRounding/></span>
	    <#--  <span>${currencySymbol}${offerPrice?if_exists?string("##0.00")} </span>--> 
      </#if>
    </#if>
  </div>
</li>
