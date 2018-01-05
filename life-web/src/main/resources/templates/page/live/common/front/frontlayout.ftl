<#include "../../../../inc/public.ftl"/>
<#macro html title_="life">
<!DOCTYPE html>
<html lang="zh" class="app">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${title_!''}</title>
    <meta name="description" content="life"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <meta name="keywords" content="回味生活"/>
    <#include "head.ftl"/>
</head>
<body>
    <#include "header.ftl"/>
    <div class="main">
        <#nested />
    </div>
    <#include "footer.ftl"/>
</body>
</html>
</#macro>

<#macro script_>
    <#nested />
</#macro>