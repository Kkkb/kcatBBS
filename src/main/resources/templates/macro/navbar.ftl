<#macro navbar currentUser>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">kcatBBS</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li role="presentation"><a href="/">all</a></li>
                    <li role="presentation"><a href="/?tab=hot">最热</a></li>
                    <li role="presentation"><a href="/?tab=tech">技术</a></li>
                    <li role="presentation"><a href="/?tab=dm">动漫</a></li>
                    <li role="presentation"><a href="/?tab=movie">电影</a></li>
                    <li role="presentation"><a href="/?tab=social">社会</a></li>
                    <li role="presentation"><a href="/?tab=game">游戏</a></li>
                </ul>
                <ul class="navbar-form navbar-left">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button class="btn btn-default" id="id-botton-search">Submit</button>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <#if currentUser.role == "guest">
                        <li><a href="/login">登录</a></li>
                        <li><a href="/register">注册</a></li>
                    </#if>
                    <#if currentUser.role == "admin">
                        <li role="presentation"><a href="/user/profile/${currentUser.id}">${currentUser.username} ❤️</a></li>
                    <#elseif currentUser.role == "guest">
                        <li role="presentation"><a href="/user/profile/${currentUser.id}">${currentUser.username}</a></li>
                    <#else>
                        <li role="presentation"><a href="/user/profile/${currentUser.id}">${currentUser.username} 🌱</a></li>
                    </#if>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/user/profile/${currentUser.id}">我的主页</a></li>
                            <li><a href="/topic/add/view">发布话题</a></li>
                            <li><a href="/users">用户图库</a></li>
                            <li><a href="/user/edit?id=${currentUser.id}">个人设置</a></li>
                            <li><a href="/admin">用户管理</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="/logout">退出</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</#macro>
