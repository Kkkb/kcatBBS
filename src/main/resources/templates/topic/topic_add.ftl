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
    <script src="https://twemoji.maxcdn.com/v/latest/twemoji.min.js" crossorigin="anonymous"></script>
    <link href="/main.css" rel="stylesheet">
    <title>发布话题 ${currentUser.username}</title>
</head>
<body>

<@navbar currentUser/>


<div class="container">
    <div class="page-header">
        <h1>发布话题</h1>

    </div>
        <form action="/topic/add" method="POST">
            <div class="form-group">
                <input class="form-control" type="text" name="title" placeholder="请输入帖子名字">
            </div>
            <span>选择板块</span>

            <select name="type" id="board" required="">
                <option value="all">all</option>
                <option value="技术">技术</option>
                <option value="动漫">动漫</option>
                <option value="电影">电影</option>
                <option value="社会">社会</option>
                <option value="游戏">游戏</option>
            </select>

            <div class="form-group markdown-body">
                <textarea name="rawContent"class="form-control" id="oriContent" onkeyup="convert()" style="height: 200px" placeholder="支持 Markdown"></textarea>
            </div>

            <div class="form-group">
                <textarea type="text" name="content" id="id-result" style="display: none"></textarea>
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

<script type="text/javascript">
    function convert(){
        var text = document.getElementById("oriContent").value;
        var html = marked(text);
        document.getElementById("id-result").innerHTML = html;
        document.getElementById("id-preview").innerHTML = html;
    }
</script>

</body>
</html>
