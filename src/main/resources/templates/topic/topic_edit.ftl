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
    <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
    <link href="/main.css" rel="stylesheet">
    <title>编辑文章 - ${topic.title}</title>
</head>
<body>

<@navbar currentUser/>


<div class="container">
    <div class="page-header">
        <h1>编辑文章</h1>

    </div>
    <form action="/topic/update" method="POST">
        <div class="form-group">
            <input name="id" placeholder="id" value="${topic.id}" style="display: none">
            <input class="form-control" type="text" name="title" placeholder="请输入帖子名字" value="${topic.title}">
        </div>
        <span>选择板块</span>

        <select name="type" id="board" required="">
            <#if topic.type == "技术">
                <option value="all">all</option>
                <option value="技术" selected>技术</option>
                <option value="动漫">动漫</option>
                <option value="电影">电影</option>
                <option value="社会">社会</option>
                <option value="游戏">游戏</option>
            <#elseif topic.type == "动漫">
                <option value="技术">技术</option>
                <option value="动漫" selected>动漫</option>
                <option value="电影">电影</option>
                <option value="社会">社会</option>
                <option value="游戏">游戏</option>
            <#elseif topic.type == "电影">
                <option value="技术">技术</option>
                <option value="动漫">动漫</option>
                <option value="电影" selected>电影</option>
                <option value="社会">社会</option>
                <option value="游戏">游戏</option>
            <#elseif topic.type == "社会">
                <option value="技术">技术</option>
                <option value="动漫">动漫</option>
                <option value="电影">电影</option>
                <option value="社会" selected>社会</option>
                <option value="游戏">游戏</option>
            <#elseif topic.type == "游戏">
                <option value="技术">技术</option>
                <option value="动漫">动漫</option>
                <option value="电影">电影</option>
                <option value="社会">社会</option>
                <option value="游戏" selected>游戏</option>
            <#else>
                <option value="all">all</option>
                <option value="技术">技术</option>
                <option value="动漫">动漫</option>
                <option value="电影">电影</option>
                <option value="社会">社会</option>
                <option value="游戏">游戏</option>
            </#if>
        </select>

        <div class="form-group markdown-body">
            <textarea class="form-control" id="oriContent" onkeyup="convert()" style="height: 200px" placeholder="支持 Markdown" name="rawContent">${topic.rawContent}</textarea>
        </div>

        <div class="form-group">
            <textarea type="text" name="content" id="id-result" style="display: none"></textarea>
            <input type="text" name="topicId" value="${topic.id}" style="display: none">
            <br>
            <br>
            <div class="markdown-preview">MarkDown 预览:
            <div type="text" id="id-preview"></div>
            </div>
        </div>
        <button type="submit" class="btn btn-default">发送</button>
    </form>

</div>
<@footer/>

<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function convert(){
        var text = document.getElementById("oriContent").value;
        var html = marked(text);
        document.getElementById("id-result").innerHTML = html;
        document.getElementById("id-preview").innerHTML = html;
    }
</script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="/sweet.js"></script>
</body>
</html>

