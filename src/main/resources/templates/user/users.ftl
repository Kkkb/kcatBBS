<#include "../macro/navbar.ftl">
<#include "../macro/footer.ftl">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>首页 - kcatBBS</title>
</head>
<body>

<@navbar currentUser/>

<div class="container">
    <div class="page-header">
        <h3>😺 收录所有用户头像</h3>
    </div>

    <div class="row">
        <#list users as u>
        <div class="col-xs-4 col-md-2">
            <a href="/user/profile/${u.id}" class="thumbnail">
                <img src="${u.avatar}" alt="...">
            </a>
        </div>
        </#list>
    </div>
</div>
<@footer/>

    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="/sweet.js"></script>
</body>
</html>
