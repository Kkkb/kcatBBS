<#include "../macro/navbar.ftl">
<#include "../macro/footer.ftl">

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

        <title>登录</title>
    </head>

    <body>

    <@navbar currentUser/>

    <div class="container">
        <div class="page-header">
            <h1>重置密码</h1>
        </div>
        <div class="col-md-4">
            <form action="/reset/send" method="post">
                <div class="form-group">
                    <p>请输入您的用户名。您会收到一封包含创建新密码链接的电子邮件。</p>
                    <input type="text" class="form-control" name="username" placeholder="请输入用户名">
                </div>
                <button type="submit" class="btn btn-default" id="id-button-send">忘记密码</button>
            </form>
        </div>
    </div>

    <@footer/>

    <script>
    <#if existUser??>
        <#if existUser == true>
            swal("发送成功", "请注意查收邮件", "success");
        <#else>
            swal("用户名不存在", "请尝试重新输入", "error");
        </#if>
    </#if>
    </script>
    </body>
</html>
