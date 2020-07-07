<#include "../macro/timeline.ftl">
<#include "../macro/navbar.ftl">
<#include "../macro/footer.ftl">

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/main.css" rel="stylesheet">
    <title>帖子详情 - ${topic.title}</title>
</head>
<body>

<@navbar currentUser/>


<div class="container">
    <div class="page-header text-center">
        <h1>${topic.title}</h1>
        <span class="label label-info">${topic.type}</span>
        <h5>
            发布于 ${(topic.createdTime * 1000)?number_to_datetime}. 上次编辑 <@timeline_dt datetime = topic.updatedTime * 1000/>.
            ${topic.clickCount} 次浏览.
        </h5>
        作者 <a href="/user/profile/${topic.user.id}">${topic.user.username}</a>.
            <#if currentUser.id == topic.userId || currentUser.role == "admin">
                <a class="delete-edit-a" href="/topic/edit?id=${topic.id}" >编辑</a>
                <a class="delete-edit-a" href="/topic/delete?id=${topic.id}" onclick=alert("确认要删除吗?") >删除</a>
            </#if>
    </div>
    <div class="container topic-content">
    ${topic.content}
    </div>
    <div class="container comment-list">
        <h4>评论:</h4>
        <ul class="media-list col-md-8">
        <#list topic.commentList as c>
        <li class="media">
            <div class="media-left media-middle">
                <a href="/user/profile/${c.userId}">
                    <img class="media-object img-thumbnail" src="${c.user.avatar}" alt="..." width="50" height="50">
                </a>
            </div>
            <div class="media-body">
                <h5><a href="/user/profile/${c.userId}">${c.user.username}</a>. <@timeline_dt datetime = c.updatedTime * 1000/>.</h5>
                 <p>
                    ${c.content}
                    <#if currentUser == c.user || currentUser.role == "admin">
                        <a href="/comment/edit?id=${c.id}" class="delete-edit-a">编辑</a>
                        <a id="id-delete" href="/comment/delete?id=${c.id}" class="delete-edit-a">删除</a>
                     </#if>
                 </p>

            </div>
        </li>
        </#list>
        </ul>

        <#if currentUser.role != "guest">
        <form action="/topic/detail/${topic.id}/comment" method="POST">
            <div class="form-group">
                <input class="form-control" type="text" name="content" placeholder="请输入评论">
            </div>
            <button type="submit" class="btn btn-primary">发送</button>
        </form>
        </#if>
    </div>
</div>
<@footer/>

<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="/sweet.js"></script>
</body>
</html>
