<!doctype html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Hello Amaze UI</title>

    <!-- Set render engine for 360 browser -->
    <meta name="renderer" content="webkit">

    <!-- No Baidu Siteapp-->
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="stylesheet" href="http://cdn.amazeui.org/amazeui/2.2.1/css/amazeui.min.css">
</head>
<body>
<#--<form class="am-form" action="http://api.weixin.qq.com/cgi-bin/material/add_material?access_token=1RftxZrrN5_UuVR477wiWqlNvlVzksZxWkCKMY6YR9yQAqlN3KhJ5vvXWLGtKUQJ9n7Yq_KSglA3AIVqCoM65J5e2ZHqL7KY3Z__EwQBXKA" method="post" enctype="multipart/form-data">-->
<form class="am-form"
      action="http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=BDRl6yJRjg7bDq1RXobZwjmYuYx3bOIA1O5wP8ibpUkYJ1orLsBR7VgVV6RDYx_foxS5NgFEmSUd7BcHhDo6LEWskbNxCyIztAPNTuaNOM0&type=image"
      method="post" enctype="multipart/form-data">
    <fieldset>
        <legend>表单标题</legend>

        <div class="am-form-group">
            <label for="doc-ipt-file-1">文件上传域</label>
            <input type="file" id="media" name="media">
            <p class="am-form-help">请选择要上传的文件...</p>
        </div>

        <p>
            <button type="submit" class="am-btn am-btn-default">提交</button>
        </p>
    </fieldset>
</form>
</body>
</html>