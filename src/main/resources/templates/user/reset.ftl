<#include "../macro/navbar.ftl">
<#include "../macro/footer.ftl">

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

        <title>更新密码</title>
    </head>

    <body>

    <@navbar currentUser/>

    <div class="container">
        <div class="page-header">
            <h1>更新密码</h1>
        </div>
        <div class="col-md-4">
            <form action="/reset/update" method="post">
                <div class="form-group">
                    <input type="password" class="form-control" name="password" placeholder="请输入新密码">
                    <input type="password" class="form-control" name="token" style="display: none" value="${token}">
                </div>
                <button type="submit" class="btn btn-default">更新密码</button>
            </form>
        </div>
    </div>

    <@footer/>

    </body>
</html>
