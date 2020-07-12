<#include "../macro/timeline.ftl">
<#include "../macro/navbar.ftl">
<#include "../macro/footer.ftl">


<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="/main.css" rel="stylesheet">

        <title>权限不足 - ${currentUser.username}</title>
    </head>

    <body>

    <@navbar currentUser/>

    <div class="container">
        <div class="page-header text-center" id="id-profile-header">
            <img id="id-user-image" class="img-thumbnail" src="${currentUser.avatar}" width="280" height="280">
            <h1>${currentUser.username}</h1>
            <p style="color: red">权限不足</p>
            admin > <a href="/login">login</a>
        </div>
    </div>
    <@footer/>

    <script>
        swal({
            title: "权限不足",
            text: "error",
            icon: "error",
        })
    </script>
    </body>
</html>
