<!-- .aside 左侧导航栏-->
<aside class="bg-black dk nav-xs aside hidden-print" id="nav">
    <section class="vbox">
        <section class="w-f-md scrollable">
            <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0"
                 data-size="10px" data-railOpacity="0.2">
                <!-- nav -->
                <nav class="nav-primary hidden-xs" style="margin-top: 10px;">
                    <ul class="nav" data-ride="collapse">
                        <li>
                            <a href="#" class="auto">
                                <span class="pull-right text-muted"><i class="fa fa-angle-left text"></i><i class="fa fa-angle-down text-active"></i></span>
                                <i class="icon-heart"></i>
                                <span>生活</span>
                            </a>
                            <ul class="nav dk text-sm">
                                <li><a href="layout-boxed.html" class="auto"><i class="icon-list"></i><span>记录</span></a></li>
                                <li><a href="layout-boxed.html" class="auto"><i class="icon-people"></i><span>联系人</span></a></li>
                                <li><a href="layout-boxed.html" class="auto"><i class="icon-notebook"></i><span>备忘录</span></a></li>
                                <li><a href="layout-boxed.html" class="auto"><i class="icon-clock"></i><span>计划</span></a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#" class="auto">
                                <span class="pull-right text-muted"><i class="fa fa-angle-left text"></i><i class="fa fa-angle-down text-active"></i></span>
                                <i class="icon-docs"></i>
                                <span>博文</span>
                            </a>
                            <ul class="nav dk text-sm">
                                <li><a href="layout-color.html" class="auto"><i class="icon-doc"></i><span>我的博文</span></a></li>
                                <li><a href="layout-boxed.html" class="auto"><i class="icon-link"></i><span>分享的博文</span></a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#" class="auto">
                                <span class="pull-right text-muted"><i class="fa fa-angle-left text"></i><i class="fa fa-angle-down text-active"></i></span>
                                <i class="icon-tag"></i>
                                <span>工具</span>
                            </a>
                            <ul class="nav dk text-sm">
                                <li><a href="layout-fluid.html" class="auto"><i class="icon-cloud-upload"></i><span>上传</span></a></li>
                                <li><a href="layout-fluid.html" class="auto"><i class="fa fa-shopping-bag" aria-hidden="true"></i><span>我的快递</span></a></li>
                                <li><a href="layout-fluid.html" class="auto"><i class="fa fa-qrcode" aria-hidden="true"></i><span>二维码</span></a></li>
                                <li><a href="layout-fluid.html" class="auto"><i class="icon-map"></i><span>地图</span></a></li>
                                <li><a href="layout-fluid.html" class="auto"><i class="icon-phone"></i><span>查询归属地</span></a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
                <!-- / nav -->
            </div>
        </section>
        <#--底部-->
        <footer class="footer hidden-xs no-padder text-center-nav-xs ">
            <div class="bg hidden-xs ">
                <div class="dropdown dropup wrapper-sm clearfix">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                      <span class="thumb-sm avatar pull-left m-l-xs">
                        <img src="${user.headimg!'http://ob2dt6f5q.bkt.clouddn.com/xingkong1.jpg?imageslim'}" class="dker" alt="头像">
                        <i class="on b-black"></i>
                      </span>
                        <span class="hidden-nav-xs clear">
                        <span class="block m-l">
                          <strong class="font-bold text-lt">${user.nickname!user.username}</strong>
                          <b class="caret"></b>
                        </span>
                        <span class="text-muted text-xs block m-l">最强王者</span>
                      </span>
                    </a>

                    <ul class="dropdown-menu animated fadeInRight aside text-left">
                        <li><span class="arrow bottom hidden-nav-xs"></span><a href="#">账户中心</a></li>
                        <li><a href="#"><span class="badge bg-danger pull-right">0</span>通知</a></li>
                        <li><a href="docs.html">设置</a></li>
                        <li class="divider"></li>
                        <li><a href="${root}/logout" id="logout">退出</a></li>
                    </ul>
                </div>
            </div>
        </footer>
    </section>
</aside>
<!-- /.aside -->

