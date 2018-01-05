<!----start-header---->
<div class="header" id="home">
    <div class="container">
       <#-- <div class="logo">
            <a href="index.html"><img src="${root}images/live/front/logo-2.png" alt=""></a>
        </div>-->
        <div class="navigation">
            <span class="menu"></span>
            <ul class="navig">
                <li><a href="/">首页</a><span> </span></li>
                <li><a href="/record">记录</a><span> </span></li>
                <li><a href="/community">社区</a><span> </span></li>
                <li><a href="/blog">博客</a><span> </span></li>
                <li><a href="/tool">工具</a><span> </span></li>
                <li><a href="/about">关于</a><span> </span></li>
                <li><a href="/login">登录</a><span> </span></li>
            </ul>
        </div>
        <!-- script-for-menu -->
        <script>
            $("span.menu").click(function(){
                $(" ul.navig").slideToggle("slow" , function(){
                });
            });
            var pathname = window.location.pathname;
            $(".navigation>ul.navig>li").each(function(){
                var thisUrl = $(this).children().attr("href") ;
                if(thisUrl == pathname){
                    $(this).children().addClass("active");
                }
            })
        </script>
        <!-- script-for-menu -->
    </div>
</div>
<!----end-header---->