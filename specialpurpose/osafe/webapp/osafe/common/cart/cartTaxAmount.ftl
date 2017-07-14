<#if (!Static["org.apache.ofbiz.osafe.util.Util"].isProductStoreParmTrue(request,"CHECKOUT_SUPPRESS_TAX_IF_ZERO") || (orderTaxTotal?has_content && (orderTaxTotal &gt; 0)))>
  <#if !Static["org.apache.ofbiz.osafe.util.Util"].isProductStoreParmTrue(request,"CHECKOUT_SHOW_SALES_TAX_MULTI")>
    <li class="${request.getAttribute("attributeClass")!}">
     <#assign defaultCurrencyUom = session.getAttribute("defaultCurrencyUomId")>
  <#if defaultCurrencyUom?has_content>
  	<#assign currencyUom = defaultCurrencyUom>
  </#if>
      <div>
        <#assign taxInfoStringMap = Static["org.apache.ofbiz.base.util.UtilMisc"].toMap("taxPercent", totalTaxPercent)>
        <#assign salesTaxCaption = Static["org.apache.ofbiz.base.util.UtilProperties"].getMessage("OSafeUiLabels","SummarySalesTaxCaption",taxInfoStringMap, locale ) />
        <label><#if shipGroupSalesTaxSame>${salesTaxCaption!}<#else>${uiLabelMap.SummarySalesTaxShortCaption!}</#if></label>
        <span><#if orderTaxTotal?has_content><@ofbizCurrency amount=orderTaxTotal! isoCode=currencyUom  rounding=globalContext.currencyRounding/><#--<span>${currencySymbol}${orderTaxTotal?if_exists?string("#0.00")} --></span></#if></span>
      </div>
    </li>
  <#else>
    <#if appliedTaxList?exists && appliedTaxList?has_content && shipGroupSalesTaxSame>
      <#list appliedTaxList as appliedTax >   
        <li class="${request.getAttribute("attributeClass")!}">
          <div>
             <#assign taxInfoStringMap = Static["org.apache.ofbiz.base.util.UtilMisc"].toMap("taxPercent", appliedTax.sourcePercentage, "description", appliedTax.description)>
             <#assign salesTaxCaption = Static["org.apache.ofbiz.base.util.UtilProperties"].getMessage("OSafeUiLabels","SummarySalesTaxMultiCaption",taxInfoStringMap, locale ) />
             <label>${salesTaxCaption!}</label>
             <span><@ofbizCurrency amount=appliedTax.amount isoCode=currencyUom  rounding=globalContext.currencyRounding/></span>
          </div>
        </li>
      </#list>
    <#else>
      <li class="${request.getAttribute("attributeClass")!}">
        <div>
          <#assign taxInfoStringMap = Static["org.apache.ofbiz.base.util.UtilMisc"].toMap("taxPercent", totalTaxPercent)>
          <#assign salesTaxCaption = Static["org.apache.ofbiz.base.util.UtilProperties"].getMessage("OSafeUiLabels","SummarySalesTaxCaption",taxInfoStringMap, locale ) />
          <label><#if shipGroupSalesTaxSame>${salesTaxCaption!}<#else>${uiLabelMap.SummarySalesTaxShortCaption!}</#if></label>
          <span><#if orderTaxTotal?has_content><@ofbizCurrency amount=orderTaxTotal! isoCode=currencyUom  rounding=globalContext.currencyRounding/></#if></span>
        </div>
      </li>
    </#if>
  </#if>
</#if>

