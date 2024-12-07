<html lang="en">
<#include "base.ftl">
<#macro title>
    exception details
</#macro>

<#macro content>
    <h1>exception details:</h1>
    <strong>Request uri: ${uri}</strong>
    <strong>Status code: ${statusCode}</strong>
    <#if message??><string>${message}</string></#if>

</#macro>
</html>