<#include "../macro/navbar.ftl">
<#include "../macro/footer.ftl">

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
    <title>编辑评论 - kcatBBS</title>
</head>
<body>

<@navbar currentUser/>

<div class="container">
    <div class="page-header">
        <h1>编辑评论</h1>

    </div>
    <form action="/comment/update" method="POST">
        <div class="form-group">
            <input name="id" placeholder="id" value="${comment.id}" style="display: none">
            <input class="form-control" type="text" name="content" placeholder="content" value="${comment.content}">
        </div>
        <button type="submit" class="btn btn-default">提交修改</button>
    </form>

</div>
<@footer/>

    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="/sweet.js"></script>
</body>
</html>
