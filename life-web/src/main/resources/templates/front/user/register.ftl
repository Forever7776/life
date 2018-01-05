<#include "../inc/inc.ftl"/>
<@html title_="注册" fontawesome=true>
<div style="padding: 100px 100px 10px;">
    <div class="form-group">
        <label for="inputText" class="col-sm-1 control-label">邮箱账号：</label>
        <div class="col-sm-2 col-md-2 ">
            <input type="text" name="email"  class="form-control" placeholder="请输入邮箱账号"/>
            <#--
            <a class="yzm" id="imageCodeRefresh" title="看不清，换一张" style="cursor: pointer;float: right;" >
            <img id="imageCodeImg" class="yzmImg" alt="验证码" src="$!root/api/captcha" style="width:124px;height: 46px;"/></a>
            -->
        </div>
        <button type="button" class="btn btn-info btn-xs" style="cursor:pointer">获取验证码</button>
    </div>
    <div class="form-group">
        <label for="inputText" class="col-sm-1 control-label">验证码：</label>
        <div class="col-sm-2 col-md-2 ">
            <input type="text" id="imageCode"  name="imageCode" style="width: 100%" class="form-control" />
        </div>
    </div>

    <div class="form-group">
        <label for="inputText" class="col-sm-1 control-label">密码：</label>
        <div class="col-sm-2 col-md-2 ">
            <input type="password" id="password"  name="password" style="width: 100%" class="form-control" />
        </div>
    </div>

    <br/><br/>
    <div class="form-group">
        <label for="inputText" class="col-sm-1 control-label"></label>
        <button class="btn btn-success active" type="button" id="btn">注册</button>
    </div>

</div>

<@script_>
<script src="${root}/js/index.js"></script>
<script>
    $("#imageCodeImg").click(function(){
        $(this).find("#imageCodeImg").attr("src", "${root}/api/captcha?" + Math.random());
    });

    $("#btn").click(function(){
        console.log(checkCode());
    });

    function checkCode() {
        var check = false;
        var code = $("#imageCode").val();
        if(!code && code.length != 4){
            return check;
        }
        var options = {
            url:  '${root}/api/captcha/check?imageCode='+$("#imageCode").val(),
            async: false,
            success : function(data){
                if(data == '1'){
                    check = true;
                }
            }
        };
        $.ajax(options);
        return check;

    }
</script>
</@script_>
</@html>