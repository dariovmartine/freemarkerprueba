<#function formatNumber value>
  <#local result=value?int?abs?string>
  <#if result?contains(",")>
    <#local result=result?replace(",", ".")>
  </#if>

  <#if value gte 0>
      <#return result + "," + getDecimal(value)>
  <#else>
      <#return "-" + result + "," + getDecimal(value)>
  </#if>
</#function>

<#function getDecimal value>
    <#local formatDecimal=value?string(".00")>
    <#local decimal = formatDecimal?substring(formatDecimal?index_of(".")+1,formatDecimal?length) >
  <#return decimal>
</#function>
${formatNumber(unformated)}
