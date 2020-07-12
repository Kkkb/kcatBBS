<#include "../macro/timeline.ftl">
<#include "../macro/navbar.ftl">
<#include "../macro/footer.ftl">

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/main.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <title>首页 - kcatBBS</title>
</head>
<body>

<@navbar currentUser/>

<div class="container">

    <div class="col-md-8 main-topics">
        <div class="tab-navbar">
            <a href="/">kcatBBS</a> >
            <#if !tab??>
                <a href="/">all</a>
            <#elseif tab == "hot">
                <a href="/?tab=hot">最热</a>
            <#elseif tab == "tech">
                <a href="/?tab=tech">技术</a>
            <#elseif tab == "dm">
                <a href="/?tab=dm">动漫</a>
            <#elseif tab == "movie">
                <a href="/?tab=movie">电影</a>
            <#elseif tab == "social">
                <a href="/?tab=social">社会</a>
            <#elseif tab == "game">
                <a href="/?tab=game">游戏</a>
            </#if>
        </div>
    <ul class="media-list">
    <#list topics as t>
        <li class="media topic-list">
            <div class="media-left media-middle">
                <a href="/user/profile/${t.userId}">
                    <img class="media-object img-thumbnail" src="${t.user.avatar}" alt="..." width="50" height="50">
                </a>
            </div>
            <div class="media-body">
                <a href="/topic/detail/${t.id}" class="none-decoration-a"><h4 class="media-heading topic-title">${t.title}
                    <small>

                    </small>
                </h4></a>
                <span>
                    <#if t.user.role == "admin">
                        <a class="admin-a" href="/user/profile/${t.userId}">${t.user.username}</a>
                    <#else>
                        <a href="/user/profile/${t.userId}">${t.user.username}</a>
                    </#if>

                </span>
                <span class="grey-color">   •   <@timeline_dt datetime = t.updatedTime * 1000/>
                <#if currentUser == t.user || currentUser.role == "admin">
                    •  <a class="delete-edit-a" href="/topic/edit?id=${t.id}">编辑</a> •
                    <a class="delete-edit-a" href="/topic/delete?id=${t.id}&token=${token}" onclick=alert("确认要删除吗?")>删除</a>
                </#if>
                </span>
            </div>
            <div class="media-right media-middle">

            </div>
            <div class="media-right media-middle">
                <#if t.type == "最热">
                    <span class="label label-danger">${t.type}</span>
                <#elseif t.type == "技术">
                    <span class="label label-success">${t.type}</span>
                <#elseif t.type == "动漫">
                    <span class="label label-warning">${t.type}</span>
                <#elseif t.type == "游戏">
                    <span class="label label-primary">${t.type}</span>
                <#else>
                    <span class="label label-info">${t.type}</span>
                </#if>
            </div>
            <div class="media-right media-middle">

                <span class="label label-default">${t.numberOfComments} / ${t.clickCount}</span>

            </div>
        </li>
    </#list>
    </ul>
    </div>

    <div class="col-md-offset-9 sidebar shadow visible-md-block visible-lg-block">
        <a href="/user/profile/${currentUser.id}">
            <img class="media-object img-thumbnail" src="${currentUser.avatar}" alt="..." width="100" height="100">
            <h3><a href="/user/profile/${currentUser.id}">${currentUser.username}</a></h3>
        </a>
        <h5>"${currentUser.note}"</h5>
        <br>
        <p>发帖 ${numberOfTopic}</p>
        <p>回复 ${numberOfComment}</p>
        <a href="/topic/add/view" class="btn btn-default" role="button">发布话题</a>
    </div>


</div>
<#if numberOfPages != 0>
    <nav aria-label="Page navigation" class="nav-page col-xs-offset-4">
        <ul class="pagination">
            <#list 1..(numberOfPages + 1) as i>
                <#if tab??>
                <#else>
                    <li ><a class="visit-a" href="/?page=${i}">${i}</a></li>
                </#if>
            </#list>
        </ul>
    </nav>
</#if>

<@footer/>

</body>
</html>
