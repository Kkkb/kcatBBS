<#include "../macro/timeline.ftl">
<#include "../macro/navbar.ftl">
<#include "../macro/footer.ftl">


<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="/main.css" rel="stylesheet">

        <title>关于本站</title>
    </head>

    <body>

    <@navbar currentUser/>

    <div class="container">
        <div class="page-header text-center" id="id-profile-header">
            <img id="id-user-image" class="img-thumbnail" src="/avatar/default.jpg" width="280" height="280">
            <h3>⭐kcatBBS</h3>
            <h4>💫颗猫的日常分享</h4>
            <span class="label label-primary">Ubuntu 18.04</span>
            <span class="label label-info">🌱SSM</span>
            <span class="label label-info">BootStrap</span>
            <h5>online⚡2020.6.28</h5>
            <a href="https://github.com/Kkkb/kcatBBS" target="_blank" class="copyright">GitHub</a>
        </div>
    </div>
    <@footer/>

    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="/sweet.js"></script>
    </body>
</html>
