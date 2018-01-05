<!-- .header 头部-->
<header class="bg-white-only header header-md navbar navbar-fixed-top-xs">
    <div class="navbar-header aside bg-info nav-xs" style="height: 60px;background-color: #4cb6cb;">
        <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html">
            <i class="icon-list"></i>
        </a>
        <a href="${root}/m" class="navbar-brand text-lt">
            <i class="icon-earphones"></i>
            <img src="http://ob2dt6f5q.bkt.clouddn.com/x1.jpg" alt="." class="hide">
            <span class="hidden-nav-xs m-l-sm">live</span>
        </a>
        <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".user">
            <i class="icon-user"></i>
        </a>
    </div>
    <ul class="nav navbar-nav hidden-xs">
        <li>
            <a href="#nav,.navbar-header" data-toggle="class:nav-xs,nav-xs" class="text-muted">
                <i class="fa fa-indent text"></i>
                <i class="fa fa-dedent text-active"></i>
            </a>
        </li>
    </ul>
    <form class="navbar-form navbar-left input-s-lg m-t m-l-n-xs hidden-xs" role="search">
        <div class="form-group">
            <div class="input-group">
            <span class="input-group-btn">
              <button type="submit" class="btn btn-sm bg-white btn-icon rounded"><i class="fa fa-search"></i></button>
            </span>
                <input type="text" class="form-control input-sm no-border rounded"
                       placeholder="Search songs, albums...">
            </div>
        </div>
    </form>
    <div class="navbar-right visible-xs no-padder text-center-nav-xs  ">
        <ul class="nav navbar-nav m-n hidden-xs nav-user user">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle bg clear" data-toggle="dropdown">
                    <span class="thumb-sm avatar pull-right m-t-n-sm m-b-n-sm m-l-sm">
                        <img src="${user.headimg!'http://ob2dt6f5q.bkt.clouddn.com/xingkong1.jpg?imageslim'}" class="dker" alt="头像">
                    </span>
                    ${user.nickname!user.username}
                </a>
                <ul class="dropdown-menu animated fadeInRight">
                    <li><span class="arrow bottom hidden-nav-xs"></span><a href="#">账户中心</a></li>
                    <li><a href="#"><span class="badge bg-danger pull-right">0</span>通知</a></li>
                    <li><a href="docs.html">设置</a></li>
                    <li class="divider"></li>
                    <li><a href="${root}/logout" id="logout">退出</a></li>
                </ul>
            </li>
        </ul>
    </div>
</header>
<!-- /.header -->