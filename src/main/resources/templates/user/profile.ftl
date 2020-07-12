<#include "../macro/timeline.ftl">
<#include "../macro/navbar.ftl">
<#include "../macro/footer.ftl">

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>个人主页 - ${user.username}</title>
    </head>

    <body>

    <@navbar currentUser/>

    <div class="container">
        <div class="page-header text-center" id="id-profile-header">
            <img id="id-user-image" class="img-thumbnail" src="${user.avatar}" width="280" height="280">
            <h3>${user.username}</h3>
            <#if user.role == "admin">
                <span class="label label-danger">admin</span>
            </#if>
            <h4>${user.note}</h4>
            <h5>注册于 <@timeline_dt datetime = user.createdTime * 1000/></h5>
            <h5>第 ${user.id} 位</h5>
            <h5>发帖 ${numberOfTopic}</h5>
            <h5>回复 ${numberOfComment}</h5>
            <#if currentUser.role == "admin" || currentUser.id == user.id>
                <a href="/user/edit?id=${user.id}">编辑个人资料</a>
            </#if>
        </div>
        <h4>最近创建的话题:</h4>
        <ul class="media-list">
        <#list topics as t>
            <li class="media topic-list">
                <div class="media-left media-middle">
                    <a href="/user/profile/${t.userId}">
                        <img class="media-object img-thumbnail" src="${t.user.avatar}" alt="..." width="80" height="80">
                    </a>
                </div>

                <div class="media-body">
                    <h4 class="media-heading" >
                        <a href="/topic/detail/${t.id}">${t.title}</a>
                        <small>${t.numberOfComments}/${t.clickCount}</small>
                    </h4>
                    <p><a href="/user/profile/${t.userId}">${t.user.username}</a> •
                        <@timeline_dt datetime = t.updatedTime * 1000/>
                        <#if currentUser.id == t.userId || currentUser.role == "admin">
                            • <a href="/topic/edit?id=${t.id}" class="delete-edit-a">编辑</a> •
                            <a href="/topic/delete?id=${t.id}" class="delete-edit-a">删除</a>
                        </#if>
                    </p>
                </div>
                <div class="media-right">
                    <#if t.latestComment??>
                        <a href="/user/profile/${t.latestComment.user.id}">
                            <img class="media-object img-thumbnail" src="${t.latestComment.user.avatar}" alt="..." width="50" height="50">
                        </a>
                            <h6><@timeline_dt datetime = t.latestComment.updatedTime * 1000/></h6>
                    </#if>
                </div>
            </li>
        </#list>
        </ul>
        <h4 id="id-recent-topic">最近参与的话题:</h4>
        <ul class="media-list">
            <#list joinedTopics as t>
                <li class="media topic-list">
                    <div class="media-left media-middle">
                        <a href="/user/profile/${t.userId}">
                            <img class="media-object img-thumbnail" src="${t.user.avatar}" alt="..." width="80" height="80">
                        </a>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading" >
                            <a href="/topic/detail/${t.id}">${t.title}</a>
                            <small>${t.numberOfComments}/${t.clickCount}</small>
                        </h4>
                        <p><a href="/user/profile/${t.user.id}">${t.user.username}</a> •
                            <@timeline_dt datetime = t.updatedTime * 1000/>
                            <#if currentUser.id == t.userId>
                                • <a href="/topic/edit?id=${t.id}" class="delete-edit-a">编辑</a> •
                                <a href="/topic/delete?id=${t.id}" class="delete-edit-a">删除</a>
                            </#if>
                        </p>
                    </div>
                    <div class="media-right">
                        <#if t.latestComment??>
                            <a href="/user/profile/${t.latestComment.user.id}">
                                <img class="media-object img-thumbnail" src="${t.latestComment.user.avatar}" alt="..." width="50" height="50">
                            </a>
                            <h6><@timeline_dt datetime = t.latestComment.updatedTime * 1000/></h6>
                        </#if>
                    </div>
                </li>
            </#list>
        </ul>
    </div>
    <@footer/>

    <script>
            var e = function (selector) {
                return document.querySelector(selector)
            }

            var bindImageClick = function () {
                var Image = e("#id-user-image")
                Image.addEventListener("click", function (event) {

                    swal({
                        title: "Hello",
                        text: "😀 你点到我啦~",
                        icon: "success",
                    });

                })
            }

            var _main = function () {
                bindImageClick()
            }

            _main()
        </script>
    </body>
</html>
