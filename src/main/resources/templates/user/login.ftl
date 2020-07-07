<#include "../macro/navbar.ftl">
<#include "../macro/footer.ftl">

<!DOCTYPE html>

<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

        <title>登录 - kcatBBS</title>
    </head>

    <body>

    <@navbar currentUser/>


    <div class="container">
        <div class="page-header">
            <h1>登录</h1>
        </div>
        <div class="col-md-4">
            <form action="/user/login" method="post">
                <div class="form-group">
                    <label>用户名</label>
                    <input id="id-input-username" type="text" class="form-control" name="username" placeholder="请输入用户名" value="测试用户">
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input id="id-input-password" type="password" class="form-control" name="password" placeholder="请输入密码" value="asdf123">
                </div>
                <button type="submit" class="btn btn-default">登录</button>
            </form>
            <br>
            <p>
                忘记密码? <a href="/reset/index" class="btn btn-info" role="button">重置密码</a>
            </p>
            <p>
                没有账号? <a href="/register" class="btn btn-info" role="button">注册</a>
            </p>
            <p>
                 <button id="id-button-admin" class="btn btn-danger">测试用户[admin]</button>
            </p>
            <p>
                <button id="id-button-normal" class="btn btn-primary">测试用户[normal]</button>
            </p>
        </div>
    </div>
    <@footer/>

    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="/sweet.js"></script>
    <script>
        let bindFillEvent = function () {
            let e = document.querySelector("#id-button-admin")
            e.addEventListener("click", function () {
                let inputUsername = document.querySelector("#id-input-username")
                let inputPassword = document.querySelector("#id-input-password")
                inputUsername.setAttribute("value", "测试用户[admin]")
                inputPassword.setAttribute("value", "asdf123")
            })
        }

        let bindFillEvent2 = function () {
            let e = document.querySelector("#id-button-normal")
            e.addEventListener("click", function () {
                let inputUsername = document.querySelector("#id-input-username")
                let inputPassword = document.querySelector("#id-input-password")
                inputUsername.setAttribute("value", "测试用户[normal]")
                inputPassword.setAttribute("value", "asdf123")
            })
        }

        bindFillEvent()
        bindFillEvent2()
    </script>
    </body>
</html>
