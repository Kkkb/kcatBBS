<#include "../macro/navbar.ftl">
<#include "../macro/footer.ftl">

<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

        <title>编辑 - ${user.username}</title>
    </head>

    <body>

    <@navbar currentUser/>

    <div class="container">
        <div class="page-header">
            <img class="img-thumbnail" src="${user.avatar}" width="280" height="280">
            <h1>${user.username}</h1>
        </div>
        <form action="/admin/update" method="POST">
            <input name="id" value="${user.id}" hidden>
            <div class="form-group">
                    <label>username</label>
                    <input class="form-control" name="username" placeholder="username" value="${user.username}">
                    <label>password</label>
                    <input class="form-control" name="password" placeholder="password" value="${user.password}">
                    <label>note</label>
                    <input class="form-control" name="note" placeholder="note" value="${user.note}">
                    <label>email</label>
                    <input class="form-control" name="email" placeholder="email" value="${user.email}">
                    <label>avatar</label>
                    <input class="form-control" name="avatar" placeholder="avatar" value="${user.avatar}">
                    <label>createdTime</label>
                    <input class="form-control" name="createdTime" placeholder="createdTime" value="${user.createdTime?c}">
                    <label>role</label>
                    <input class="form-control" name="role" placeholder="role" value="${user.role}">
            </div>
            <button class="btn btn-danger" type="submit">Update</button>
        </form>
        <h3>Upload Avatar</h3>
        <form method="post" action="/upload" enctype="multipart/form-data">
            <input name="id" value="${user.id}" hidden>
            <input type="file" name="file">
            <br>
            <button class="btn btn-warning" type="submit">Upload</button>
        </form>
    </div>
    <@footer/>

    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="/sweet.js"></script>
    </body>
</html>
