<#include "../macro/navbar.ftl">
<#include "../macro/footer.ftl">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/main.css" rel="stylesheet">

    <title>用户管理 - kcatBBS</title>
</head>
<body>

<@navbar currentUser/>

<div class="container">
    <div class="page-header">
        <h3>⚡ 用户管理</h3>
    </div>

    <div class="row">
        <ul class="media-list">
        <#list users as u>
            <li class="media topic-list">
                <div class="media-left media-middle">
                    <a href="/user/profile/${u.id}">
                        <img class="media-object img-thumbnail" src="${u.avatar}" alt="..." width="55" height="55">
                    </a>
                </div>
                <div class="media-body">
                    <#if u.role == "admin">
                        <span><a href="/user/profile/${u.id}" class="admin-a">${u.username}</a></span>
                    <#else>
                        <span><a href="/user/profile/${u.id}">${u.username}</a></span>
                    </#if>
                    <p class="media-heading" >
                        id(${u.id}) · role(${u.role}) · email(${u.email}) · note(${u.note}) · Joined on (${(u.createdTime * 1000)?number_to_datetime?string("yyyy-MM-dd hh:mm:ss")})
                    </p>
                </div>
                <div class="media-right media-middle">
                    <a href="/admin/edit/?id=${u.id}">Edit</a>
                </div>
            </li>
        </#list>
        </ul>
    </div>
</div>
<@footer/>


    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="/sweet.js"></script>
</body>
</html>
