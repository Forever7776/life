<#--img url -->
<#macro img type_=0 u_=""><#if type_=0>data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC<#else><#if u_?starts_with("http://")>${u_}<#else>${imgurl!'http://o7tc4e966.bkt.clouddn.com/'}${u_}<#switch type_>
    <#case 1>?imageView2/0/w/800/h/400<#break><#case 2>?imageView2/0/w/400/h/200<#break><#case 3>?imageView2/0/w/200/h/200<#break><#case 4>?imageView2/0/w/750/h/450<#break><#case 5>?imageView2/0/w/600/h/600<#break><#case 6>?/thumbnail/600x<#break><#case 7>?imageMogr2/thumbnail/!15p<#break><#case 8>?imageMogr2/thumbnail/56x42<#break>
    <#case 9>?imageMogr2/thumbnail/80x60<#break><#case 10>?imageView2/0/w/288/h/182<#break><#case 11>?imageMogr2/thumbnail/!50p<#break><#case 12>?imageMogr2/thumbnail/240x200<#break>
</#switch></#if></#if></#macro>
<#--res url -->
<#assign root = request.contextPath/>
<#macro res u_=""><#if context??>${context.ver('/resources/static/'+u_)}<#else>${root}/js/${u_}</#if></#macro>
<#macro baidutj>
<script>
    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?69253dd85a2fba314af5e1bd1bb55df1";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script></#macro>
<#macro baiducx>
<script type="text/javascript">
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F69253dd85a2fba314af5e1bd1bb55df1' type='text/javascript'%3E%3C/script%3E"));
</script></#macro>
<#macro baidumap>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=${context.getProp('baidu.ak')}"></script>
</#macro>