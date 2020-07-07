<#include "../macro/navbar.ftl">
<#include "../macro/footer.ftl">

<!DOCTYPE html>

<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

        <title>注册</title>
    </head>

    <body>

    <@navbar currentUser/>

    <div class="container">
        <div class="page-header">
            <h1>注册</h1>
        </div>
        <div class="col-md-4">
            <form action="/user/register" method="post">
                <div class="form-group">
                    <label>用户名</label>
                    <input type="text" class="form-control" name="username" placeholder="请输入用户名">
                    <#if existUsername>
                        <span> 用户名已存在</span>
                    </#if>
                </div>
                <div class="form-group">
                    <label>邮箱</label>
                    <input type="text" class="form-control" name="email" placeholder="请输入邮箱">
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="password" class="form-control" name="password" placeholder="请输入密码(大于6位)">
                </div>
                <button type="submit" class="btn btn-default">注册</button>
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
