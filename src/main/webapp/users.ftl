<html lang="en">
<#include "base.ftl">
<#macro title>Users</#macro>
<#macro content>
    <#if users?has_content>
        Пользователи:
        <br>
        Имя Фамилия
        <br>
        <#list users as u>
            ${u.name} ${u.lastname}
            <br>
        </#list>
    </#if>
</#macro>
</html>
