# kcatBBS

基于 Spring + Spring MVC + MyBatis 的 个人论坛

> http://120.78.166.99/

数据存储使用 MySQL，ORM 采用 MyBatis，模板引擎基于 FreeMarker, SpringMVC 架构。

**主要功能**
- 用户登陆、个人主页、信息管理功能；
- 话题的编辑、发布、删除、评论，分类, 分页功能；
- 管理员模块, 拥有最高权限, 可进行用户管理, 可查看所有用户列表, 修改用户字段；
- 使用邮箱注册，使用注册邮箱找回密码。

**安全与优化**
- 实现对 CSRF、XSS、SQL 注入攻击的防御；
- 使用服务器端 Session 实现用户登录、登出;
- 密码要求 6 位以上, 并且加盐后以摘要形式保存到数据库
- 采用 AOP 实现权限控制, 如必须登录才能浏览全部网页

**项目部署**
- 使用 nginx 进行反向代理，过滤静态资源请求；
- 使用 Tomcat 作为 Servlet 容器, SpringMVC 处理逻辑。
- 系统版本 Linus Ubuntu 18.04

## 演示
### 登录/注册
![登录/注册演示](https://s1.ax1x.com/2020/07/07/UAp3uD.gif)

### 发布话题及评论
![话题/评论演示](https://s1.ax1x.com/2020/07/07/UAp8De.gif)

### 主题分类及帖子分页
![主题分类演示](https://s1.ax1x.com/2020/07/07/UApGHH.gif)

### 个人主页及图库
![个人主页演示](https://s1.ax1x.com/2020/07/07/UAFvHU.gif)

### 管理员权限
![管理员账号演示](https://s1.ax1x.com/2020/07/07/UAkn4H.gif)
