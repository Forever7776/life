<#include "../../../../inc/public.ftl"/>
<#macro html title_="life">
<!DOCTYPE html>
<html lang="zh" class="app">
<head>
    <meta charset="utf-8"/>
    <title>${title_!''}</title>
    <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
<#include "head.ftl"/>
</head>
<body>
<section class="vbox">
    <#include "header.ftl"/>
    <section>
        <section class="hbox stretch">
            <#include "leftnav.ftl"/>
            <!--内容开始-->
            <#nested />
            <!--内容结束-->

        </section>
    </section>
    <#include "footer.ftl"/>
</section>


<#include "js.ftl"/>
</body>
</html>
</#macro>

<#macro script_>
    <#nested />
</#macro>