<!DOCTYPE HTML>
<html lang="zh">
<head>
    <title>发送邮件</title>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>

<form action="/mail/send" method="get">
    <label>
        <input type="text" name="address" placeholder="地址">
    </label>
    <br>
    <label>
        <input type="text" name="title" placeholder="标题">
    </label>
    <br>
    <label>
        <input type="text" name="content" placeholder="内容">
    </label>
    <br>
    <button type="submit">发送测试邮件</button>
</form>


<form action="/mail/send/async" method="post">
    <label>
        <input type="text" name="address" placeholder="地址">
    </label>
    <br>
    <label>
        <input type="text" name="title" placeholder="标题">
    </label>
    <br>
    <label>
        <input type="text" name="content" placeholder="内容">
    </label>
    <br>
    <button type="submit">发送异步邮件</button>
</form>

<br>

</body>
</html>
