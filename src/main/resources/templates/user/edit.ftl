<#include "../macro/navbar.ftl">
<#include "../macro/footer.ftl">

<!DOCTYPE html>

<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

        <title>编辑个人信息 - ${user.username}</title>
    </head>

    <body>

    <@navbar currentUser/>

    <div class="container">
        <div class="page-header text-center">
            <img class="img-thumbnail" src="${user.avatar}" width="280" height="280">
            <h3>${user.username}</h3>
        </div>
        <form action="/user/update" method="POST">
            <input name="id" value="${user.id}" hidden>
            <div class="form-group">
                <#if currentUser.role == "admin">
                    <input class="form-control" name="username" placeholder="username" value="${user.username}">
                </#if>
                <label>签名</label>
                <input class="form-control hidden" name="username" placeholder="username" value="${user.username}">
                <input class="form-control" name="note" placeholder="note" value="${user.note}">
            </div>
            <button class="btn btn-default" type="submit">提交修改</button>
        </form>

        <h3>修改头像</h3>
        <form method="post" action="/upload" enctype="multipart/form-data">
            <input name="id" value="${user.id}" hidden>
            <input type="file" name="file">
            <br>
            <button class="btn btn-default" type="submit">上传</button>
        </form>
    </div>
    <@footer/>

    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="/sweet.js"></script>
    </body>
</html>
