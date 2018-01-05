<#include "../../inc/inc.ftl"/>
<@html title_='回味生活-登录' css_=['live/login/login.css'] fontawesome=true>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <script>if (window.top !== window.self) {
        window.top.location = window.location;}
    </script>
    <script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.js"></script>
    <link rel="stylesheet" href="${root}/tools/toastr/toastr.min.css">
</head>

<body>

<div class="top">
    <a class="logo" href="/"><img src="${root}images/life.png" style="height: 100%" class="logo_img"/> </a>
</div>
<div class="banner">
    <div id="bannerbg"><!--onLoad="checked();"-->
        <img alt="background" src="http://ob2dt6f5q.bkt.clouddn.com/xingkong1.jpg?imageslim&imageView2/0/w/1920/h/1080" style="FILTER:blendTrans(duration=10)" name="imgs" id="imgs" width="1920" height="570"/>
        <img alt="ui" src="${root}/images/login/bannerhover01.png"/>
        <img alt="ui-2" src="${root}/images/login/bannerhover02.png"/>
    </div>
</div>
<div class="login">
    <p class="user_login">用户登录</p>
    <form action="<#--/user/loginpost-->" role="form" <#--method="post"-->
          id="loginform" <#--onsubmit="return ch();"-->>
        <input type="text" name="username" value="" id="username" placeholder="请输入用户名" class="login_input login_name" minlength="2" maxlength="32">
        <input type="password" name="password" value="" id="password" placeholder="请输入密码" class="login_input login_password" minlength="6">
        <div class="login_message">
            <a class="login_a forget">忘记密码</a> <a href="/register" class="login_a login_registered">立即注册</a>
        </div>
        <button type="button" value="登录" onclick="check()" class="login_btn" style="background: #102456">登录</button>
    </form>
</div>

<!-- Modal -->

<div id="myModal" class="modal fade"
     style="opacity:1;position:fixed;top:20;right:0;left:0;bottom:0;z-index:1040;display:none;overflow-x: hidden;overflow-y: auto;">
    <div style="opacity: 0.5;position:fixed;top:0;right:0;bottom:0;left:0;background-color: #000;"></div>
    <div class="modal-dialog" style="width:600px;margin:30px auto;position:relation;">
        <div class="modal-content"
             style="box-shadow: none;border:none;position:relative;background-color: #fff;background-clip: padding-box;outline: 0;">
            <div class="modal-header"
                 style="background: #65CEA7;color:#fff;border:none;min-height:16.43px;padding:15px;">
                <button type="button" onclick="closeWind()" class="close" data-dismiss="modal"
                        style="color:#fff;opacity: 0.5;margin-top:-2px;padding:0;cursor: pointer;background: 0 0;border:0;float:right;font-size:21px;font-weight: 700;line-height: 1;">&times;</button>
                <h4 class="modal-title" style="font-size:18px;margin:0;line-height: 1.4285;">忘记密码?</h4>
            </div>
            <div class="modal-body" style="position: relative;padding:15px;">
                <p style="text-align: left;
                    color:#b6b6b6;font-size:16px;font-weight: normal;margin: 0 0 10px;">请填写您的邮箱地址</p>
                <input type="text" name="email" id="email" placeholder="Email"
                       style="margin-top:15px;border:1px solid #eaeaec;background: #eaeaec;box-shadow: none;font-size:14px;position: relative;height:auto;padding:10px;"
                       class="form-control placeholder-no-fix" required>

            </div>
            <div class="modal-footer" style="padding:15px;text-align:right;border-top:1px solid #e5e5e5;">
                <button class="btn btn-default"
                        style="background:#fff;padding: 6px 15px;border:1px solid #333;border-radius:4px;text-align:center;vertical-align: middle;color:#333;font-size:14px;cursor: pointer;"
                        onclick="closeWind()" type="button">取消
                </button>
                <button class="btn btn-primary" type="button"
                        style="background-color: #424F63;border-color: #374152;color: #FFFFFF;padding: 6px 15px;border-radius:4px;text-align:center;vertical-align: middle;font-size:14px;cursor: pointer;"
                        onclick="findPwd()">确定
                </button>
            </div>
        </div>
    </div>
</div>


<div class="footer">
    <div class="footer_con">
        <p class="company_message">经营者名称：德玛西亚 许可证编号：
            <a target="_blank" style="padding-left: 10px;color:#a4a4a4;" href="/user/checkID">JY14403030012013</a>
        </p>
        <p class="company_message">Copyright © 2018 live Inc. All Rights Reserved.
            <a target="_blank" style="padding-left: 10px;color:#a4a4a4;"  href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action">粤ICP备11021598号-2</a>
        </p>

        <p class="company_message">全国热线：4008-310-521</p>
    </div>
</div>

<div class="rightbox" style="margin-right: 3%">
    <div class="iphone">
        <span style="margin-left:none;">全国热线</span>
        <div class="iphonehover">
            <img src="http://ob2dt6f5q.bkt.clouddn.com/iphonehover.png"/>
        </div>
    </div>
    <div class="iphone code">
        <span style="margin-left:none;">官方微信</span>
        <div class="codehover">
            <img src="http://ob2dt6f5q.bkt.clouddn.com/codehover.png"/>
        </div>
    </div>
</div>

<script src="${root}/tools/toastr/toastr.min.js"></script>
<script src="${root}/tools/jquery/jquery.logosDistort.min.js"></script>
<script>
    $("#bannerbg").logosDistort();
</script>
<script src="${root}/tools/flickity.pkgd.min.js"></script>
<script>

    var ImgSrc = new Array();//图片地址
    ImgSrc[0] = "http://ob2dt6f5q.bkt.clouddn.com/xingkong1.jpg?imageslim&imageView2/0/w/1920/h/1080"
   /* ImgSrc[1] = "${root}/images/login/banner04.jpg"
    ImgSrc[2] = "${root}/images/login/banner01.jpg"
    ImgSrc[3] = "${root}/images/login/banner03.jpg"*/
    for (var i = 0; i < ImgSrc.length; i++) {
        (new Image()).src = ImgSrc[i];
    }//预加载图片
    var ImgAlt = new Array();//鼠标放上去显示的文字
    ImgAlt[0] = ""
    ImgAlt[1] = ""
    ImgAlt[2] = ""
    ImgAlt[3] = ""
    var ImgHerf = new Array();//链接
    ImgHerf[0] = ""
    ImgHerf[1] = ""
    ImgHerf[2] = ""
    ImgAlt[3] = ""
    var step = 0;
    function slideit() {
        var oImg = document.getElementById("imgs");
        if (document.all) {
            oImg.filters.blendTrans.apply();
        }
        oImg.src = ImgSrc[step];
        document.getElementById("bannerbg").href = ImgHerf[step];
        oImg.title = ImgAlt[step];
        if (document.all) {
            oImg.filters.blendTrans.play();
        }
        step = (step < (ImgSrc.length - 1)) ? (step + 1) : 0;
        (new Image()).src = ImgSrc[step];//加载下一个图片
    }
    setInterval("slideit()", 2000);
</script>

<script>
    $(document).keydown(function (e) {
        if (e.keyCode == 13) {
            check();
        }
    });
    function check() {
        var username = $("#username").val();
        var password = $("#password").val();
        if (!username) {
            toastr.error("请输入用户名", '请输入3-50位数字、字母、下划线');
            return false;
        } else if (!password) {
            toastr.error("请输入密码", '密码由6-16位数字、字母组成');
            return false;
        } else {
            $.ajax({
                url: '/login',
                type: 'post',
                data: {'username': username, 'password': password},
                dataType: 'json',
                success: function (d) {
                    if (d.status==200) {
                        window.location.href = "/m";
                    } else {
                        toastr.error(d.msg, "提示");
                    }
                }
            });
        }
    }

    $(".forget").click(function () {
        $("#myModal").css("display", "block");
    })


    var closeWind = function () {
        $("#myModal").css("display", "none");
        window.location.reload();
    }
    var findPwd = function () {
        var email = $("#email").val();
        var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if (!myreg.test(email)) {
            toastr.warning('请输入正确的邮箱', '提示')
            return false;
        } else {
            $.ajax({
                'url': '/user/findPasswordByEmail',
                'type': 'post',
                'data': {'email': email},
                'dataType': "json",
                'success': function (resp) {
                    if (resp.success) {
                        /*toastr.warning(resp.msg, '提示')*/
                        alert(resp.msg)
                        /*$("#myModal").;*/
                        $("#myModal").css("display", "none");
                        window.location.reload();

                    } else {
                        toastr.warning(resp.msg, '提示')
                    }
                }, 'error': function (resp) {
                    toastr.error(resp.msg, '提示')
                }
            });
        }
    }

</script>
</body>
</@html>
