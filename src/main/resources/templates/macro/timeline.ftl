<#macro timeline_dt datetime>
<#assign ct = (.now?long-datetime?long)/1000 />
<#if ct gte 31104000>${(ct/31104000)?int}年前
<#t><#elseif ct gte 2592000>${(ct/2592000)?int}个月前
<#t><#elseif ct gte 86400*2>${(ct/86400)?int}天前
<#t><#elseif ct gte 86400>昨天
<#t><#elseif ct gte 3600>${(ct/3600)?int}小时前
<#t><#elseif ct gte 60>${(ct/60)?int}分钟前
<#t><#elseif ct gt 0>${ct?int}秒前
<#t><#else>刚刚
</#if>
</#macro>