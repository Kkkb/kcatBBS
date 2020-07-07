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
                <button type="submit" class="btn btn-default">忘记密码</button>
            </form>
        </div>
    </div>

    <@footer/>

    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="/sweet.js"></script>
    </body>
</html>
