# Myblog Springboot开发

该项目需要有Springboot/java/Maven/Jpa/thymeleaf/ElasticSearch/Security/MySQL/Thymeleaf/Bootstrap/jQuery等基本概念

# 关于项目

## 测试环境

```
后端：springboot+jpa
测试环境：IDEA + tomcat8 + mysql5.1.6 + jdk8 + maven
前端：thymeleaf+jQuery+Bootstrap
```

## 技术栈

- 项目环境采用`IDEA` + `MAVEN` + `Tomcat` + `MySQL`，持久层使用`Spring Data JPA`

## 项目功能

- 用户管理
- 安全设置
- 搜索功能
- 博客管理
- 评论功能
- 分类功能
- 标签功能

## 项目运行步骤

1. 安装Elasticsearch 2.4.2，并启动(见项目Elasticsearch 2.4.2)。

2. 启动 file-server （可选）：用于博客系统图片上传用。如果不涉及博客图片上传、头像上传，则可以不启用。

3. 启动 Application.java。

4. 启动后，访问主页 <http://localhost:8080>如果是首次运行项目，则页面数据为空

5. 点击登录，或者进行注册，系统默认提供如下账号：

   管理员账号admin 密码 123456

   普通用户账号jack 密码 123456

![1564799945665](C:\Users\Jack Ren\AppData\Roaming\Typora\typora-user-images\1564799945665.png)

## 项目结构

```
│  README.md
│  pom.xml
src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─jack
│  │  │          └─myblog
│  │  │              │  Application.java
│  │  │              │  
│  │  │              ├─config
│  │  │              │      SecurityConfig.java
│  │  │              │      
│  │  │              ├─controller
│  │  │              │      AdminController.java
│  │  │              │      BlogController.java
│  │  │              │      CatalogController.java
│  │  │              │      CommentController.java
│  │  │              │      MainController.java
│  │  │              │      UserController.java
│  │  │              │      UserspaceController.java
│  │  │              │      VoteController.java
│  │  │              │      
│  │  │              ├─dao
│  │  │              │  │  AuthorityDao.java
│  │  │              │  │  BlogDao.java
│  │  │              │  │  CatalogDao.java
│  │  │              │  │  CommentDao.java
│  │  │              │  │  UserDao.java
│  │  │              │  │  VoteDao.java
│  │  │              │  │  
│  │  │              │  └─es
│  │  │              │          EsBlogDao.java
│  │  │              │          
│  │  │              ├─pojo
│  │  │              │  │  Authority.java
│  │  │              │  │  Blog.java
│  │  │              │  │  Catalog.java
│  │  │              │  │  Comment.java
│  │  │              │  │  User.java
│  │  │              │  │  Vote.java
│  │  │              │  │  
│  │  │              │  └─es
│  │  │              │          EsBlog.java
│  │  │              │          
│  │  │              ├─result
│  │  │              │      Response.java
│  │  │              │      
│  │  │              ├─service
│  │  │              │  │  AuthorityService.java
│  │  │              │  │  BlogService.java
│  │  │              │  │  CatalogService.java
│  │  │              │  │  CommentService.java
│  │  │              │  │  EsBlogService.java
│  │  │              │  │  UserService.java
│  │  │              │  │  VoteService.java
│  │  │              │  │  
│  │  │              │  └─impl
│  │  │              │          AuthorityServiceImpl.java
│  │  │              │          BlogServiceImpl.java
│  │  │              │          CatalogServiceImpl.java
│  │  │              │          CommentServiceImpl.java
│  │  │              │          EsBlogServiceImpl.java
│  │  │              │          UserServiceImpl.java
│  │  │              │          VoteServiceImpl.java
│  │  │              │          
│  │  │              │      
│  │  │              ├─utils
│  │  │              │      ConstraintViolationExceptionHandler.java
│  │  │              │      
│  │  │              └─vo
│  │  │                      CatalogVO.java
│  │  │                      Menu.java
│  │  │                      TagVO.java
│  │  │                      
│  │  └─resources
│  │      │  application.properties
│  │      │  import.sql
│  │      │  
│  │      ├─static
│  │      │  │  favicon.ico
│  │      │  │  
│  │      │  ├─css
│  │      │  │  │  blog.css
│  │      │  │  │  bootstrap-grid.css
│  │      │  │  │  bootstrap-grid.css.map
│  │      │  │  │  bootstrap-grid.min.css
│  │      │  │  │  bootstrap-grid.min.css.map
│  │      │  │  │  bootstrap-reboot.css
│  │      │  │  │  bootstrap-reboot.css.map
│  │      │  │  │  bootstrap-reboot.min.css
│  │      │  │  │  bootstrap-reboot.min.css.map
│  │      │  │  │  bootstrap-table.css
│  │      │  │  │  bootstrap-table.min.css
│  │      │  │  │  bootstrap.css
│  │      │  │  │  bootstrap.css.map
│  │      │  │  │  bootstrap.min.css
│  │      │  │  │  bootstrap.min.css.map
│  │      │  │  │  component-chosen.css
│  │      │  │  │  component-chosen.min.css
│  │      │  │  │  component-tageditor.css
│  │      │  │  │  component-tageditor.min.css
│  │      │  │  │  cropbox.css
│  │      │  │  │  font-awesome.css
│  │      │  │  │  font-awesome.css.map
│  │      │  │  │  font-awesome.min.css
│  │      │  │  │  jquery.tagsinput.min.css
│  │      │  │  │  nprogress.css
│  │      │  │  │  style.css
│  │      │  │  │  tether-theme-arrows-dark.css
│  │      │  │  │  tether-theme-arrows-dark.min.css
│  │      │  │  │  tether-theme-arrows.css
│  │      │  │  │  tether-theme-arrows.min.css
│  │      │  │  │  tether-theme-basic.css
│  │      │  │  │  tether-theme-basic.min.css
│  │      │  │  │  tether.css
│  │      │  │  │  tether.min.css
│  │      │  │  │  thinker-md.vendor.css
│  │      │  │  │  thymeleaf-bootstrap-paginator.css
│  │      │  │  │  toastr.css
│  │      │  │  │  toastr.min.css
│  │      │  │  │  
│  │      │  │  ├─emoji
│  │      │  │  │      nature.css
│  │      │  │  │      object.css
│  │      │  │  │      people.css
│  │      │  │  │      place.css
│  │      │  │  │      Sysmbols.css
│  │      │  │  │      twemoji.css
│  │      │  │  │      
│  │      │  │  └─images
│  │      │  │      └─emoji
│  │      │  │              nature.png
│  │      │  │              object.png
│  │      │  │              people.png
│  │      │  │              place.png
│  │      │  │              Sysmbols.png
│  │      │  │              twemoji.png
│  │      │  │              
│  │      │  ├─data
│  │      │  │      data1.json
│  │      │  │      
│  │      │  ├─fonts
│  │      │  │      fontawesome-webfont.eot
│  │      │  │      fontawesome-webfont.svg
│  │      │  │      fontawesome-webfont.ttf
│  │      │  │      fontawesome-webfont.woff
│  │      │  │      fontawesome-webfont.woff2
│  │      │  │      FontAwesome.otf
│  │      │  │      gly-halflings-regular.eot
│  │      │  │      gly-halflings-regular.svg
│  │      │  │      gly-halflings-regular.ttf
│  │      │  │      gly-halflings-regular.woff
│  │      │  │      gly-halflings-regular.woff2
│  │      │  │      
│  │      │  ├─images
│  │      │  │      avatar-defualt.jpg
│  │      │  │      delete.png
│  │      │  │      
│  │      │  └─js
│  │      │      │  blog.js
│  │      │      │  blogedit.js
│  │      │      │  bootstrap.js
│  │      │      │  bootstrap.min.js
│  │      │      │  catalog-generator.js
│  │      │      │  chosen.jquery.js
│  │      │      │  cropbox.js
│  │      │      │  index.js
│  │      │      │  jquery-3.1.1.min.js
│  │      │      │  jquery.form.min.js
│  │      │      │  jquery.tag-editor.js
│  │      │      │  jquery.tag-editor.min.js
│  │      │      │  jquery.tagsinput.min.js
│  │      │      │  main.js
│  │      │      │  nprogress.js
│  │      │      │  tether.js
│  │      │      │  tether.min.js
│  │      │      │  thinker-md.vendor.js
│  │      │      │  thinker-md.vendor.min.js
│  │      │      │  thinker-md.vendor.min.map
│  │      │      │  thymeleaf-bootstrap-paginator.js
│  │      │      │  toastr.min.js
│  │      │      │  
│  │      │      ├─admins
│  │      │      │      main.js
│  │      │      │      
│  │      │      ├─users
│  │      │      │      main.js
│  │      │      │      
│  │      │      └─userspace
│  │      │              blog.js
│  │      │              blogedit.js
│  │      │              main.js
│  │      │              u.js
│  │      │              
│  │      └─templates
│  │          │  index.html
│  │          │  login.html
│  │          │  register.html
│  │          │  search.html
│  │          │  
│  │          ├─admins
│  │          │      index.html
│  │          │      
│  │          ├─fragments
│  │          │      footer.html
│  │          │      header.html
│  │          │      page.html
│  │          │      
│  │          ├─users
│  │          │      add.html
│  │          │      edit.html
│  │          │      list.html
│  │          │      
│  │          └─userspace
│  │                  avatar.html
│  │                  blog.html
│  │                  blogedit.html
│  │                  catalogedit.html
│  │                  profile.html
│  │                  u.html
```



# 需求实现

## **用户管理（后台）**

**方法1：根据用户名进行分页模糊查询list()**

控制层：

1.先构建Pageable，入参为pageIndex, pageSize；

2.然后进入业务方法，通过userService调用方法userService.listUsersByNameLike(name, pageable)返回Page<User> page；

3.获取集合List<User> list通过page.getContent()；model增加属性page和list；

4.返回ModelAndView对象，并在标签#mainContainerRepleace显示，视图地址users/list，并返回model对象

业务层：

1.先重新组装name（前后增加%），因为需进行模糊查询

2.获取Page<User> users，通过userDao.findByNameLike(name, pageable)。因userDao已经实现JpaRepository，所以不用写具体的方法

3.返回users（Page<User>对象）

**方法2：新建用户**

控制层：

先创建Authority的集合authorities，获取Authority实体通过authorityService.getAuthorityById获取，然后添加到集合authorities中，然后再设置user的权限。在业务层通过userService.saveUser(user)保存用户(需对保存用户是否成功做判断)，如果保存成功，则返回ResponseEntity.ok().body(new Response(true, "处理成功", user))，Response是复用的响应对象

**方法3：获取 form 表单页面**

控制层：通过model增加空的user属性，新建ModelAndView并将该model返回（new ModelAndView("users/add", "userModel", model)）

**方法4：获取修改用户的界面，及数据**

控制层：获取user通过userService.getUserById(id)，然后model增加user属性，新建ModelAndView并返回model对象（new ModelAndView("users/edit", "userModel", model)）

**方法5：删除用户**

控制层：直接通过userService.removeUser(id)删除用户，然后对是否删除成功进行判断，如果成功，返回ResponseEntity.ok().body( new Response(true, "处理成功"))

**登录：**

访问login，跳转到login页面；登录错误，跳转到login-error页面，并在model中添加true和错误信息，然后返回。

**注册：**

get访问register，跳转到register页面；post注册用户,先构建authorities对象，通过authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID))，然后用户设置权限user.setAuthorities(authorities)，之后通过userService.saveUser(user)保存用户，需对保存是否成功进行判断，成功，跳转到login页面，不成功，跳转到register页面。

**主页控制：**

访问根路径/，重定向到index页面，访问index页面，重定向到blogs页面。



## **安全设置**

**1、User实体类的处理**

1.User实体需实现UserDetails，并重写相应的方法，返回值都改为true。另外getUsername和getPassword需重写，去除原来生成的

2.重写getAuthorities()，需将 List<Authority> 转成 List<SimpleGrantedAuthority>，否则前端拿不到角色列表名称

3.对于设置密码时的加密处理，需新增setEncodePassword()，使用BCryptPasswordEncoder加密

**2、实现类UserServiceImpl的处理**

1.UserServiceImpl需实现UserDetailsService

2.重写loadUserByUsername()，返回UserDetail通过userDao.findByUsername(username)

**3、权限Authority实体的处理**

1.Authority需实现GrantedAuthority接口

2.重写getAuthority()，返回权限名name

**4、创建安全配置类SecurityConfig** 

1、SecurityConfig 需继承适配器WebSecurityConfigurerAdapter 

2、注入UserDetailsService和PasswordEncoder，并将PasswordEncoder和AuthenticationProvider纳入spring的bean管理， 获取authenticationProvider通过创建DaoAuthenticationProvider()，然后authenticationProvider需setUserDetailsService和setPasswordEncoder

3、自定义配置configure()重写

4、注入认证信息的配置configureGlobal()，入参为AuthenticationManagerBuilder ，重新配置userDetailsService()和authenticationProvider()



## **搜索功能实现**

**后台编码：**

1.创建EsBlog文档类，使用nosql存储,注意构造函数将blog实体传进来，再绑定到Esblog

2.在bom.xml中增加相关依赖

3.在application.properties中配置ES

4.创建EsBlogDao继承ElasticsearchRepository，并定义相应的方法

5.创建TagVO，这是tags的值对象，需把相关的信息传到前台去（因首页需展示热门标签）

6.创建EsBlogService接口，并实现相应的接口

7.创建EsBlogServiceImpl

8..修改userService，增加用户名集合查询用户的详细信息方法

9.修改userServiceImpl，增加相关实现

10.修改userDao

11.修改blogServiceImpl，在保存博客的时候（关系型数据库），增加EsBlog的存储，并注入EsBlogService。在移除博客的时候，也需要移除EsBlog

12.修改BlogController

13.修改MainController，当访问index时，重定向到blogs(BlogController)

**前台编码：**

1.修改index.html

2.修改header.html



## **博客管理实现**

**个人设置和头像设置：**

1.在pom.xml中加入markdown解析器txtmark

2.下载文件服务器

3.在template文件下创建userspace文件，然后在该文件下创建blog.html,blogedit.html,u.html,profile.html

4.新建UserspaceController，并对相应的url做处理

5.在application.properties中引入文件服务器接口位置

6.因重新进行个人设置的时候，密码可能会修改，所以需在user实体中设置加密的方法，增加setEncodePassword方法

7.个人头像设置，在template/userspace文件中新增avatar.html，可以对头像图片进行处理，使用cropper第三方js进行处理（需引入cropbox.js）

8.在static文件下创建userspace文件夹，然后在该文件夹下创建main.js，用于处理头像的动作js

**博客管理实现：**

1.新建blog实体

2.新建blogDao,并实现相应的方法

3.新建blogService和blogServiceImpl，并实现相应所需的方法

4.修改和新增userspaceController方法

5.修改login.html和header.html(使得登录前后的显示不同)

6.重新设置import.sql，将明文密码改为加密密码

7."记住我"的功能标签为name

<input type="checkbox" name="remember-me"> 记住我



## **评论功能实现**

1.先创建Comment实体对象

2.修改blog，注意blog和comment是一对多的关系

3.创建commentDao接口并继承JpaRepository

4.创建commentService接口，并实现相应的方法

5.创建commentServiceImpl

6.修改blogService，增加createComment和removeComment方法，并做实现

7.创建commentController，并实现相应的方法

8.修改blog.html

9.在static/js/userspace下增加blog.js，blogedit.js，u.js



## **分类功能实现**

1.创建Category实体

2.修改blog实体

3.创建CategoryVO，接收前台传递的参数

4.创建CategoryDao，继承JpaRepository，并创建相应的接口

5.创建CategoryService，并创建相应的服务方法

6.修改BlogDao，增加根据分类查询博客列表

7.创建CategoryServiceImpl

8.修改blogService，增加一个根据分类查询的接口

9.修改blogServiceImpl，增加新增接口的实现

10.创建CategoryController

11.修改userspaceController

前台编码：

1.修改usersp/u.html

2.修改blogedit.html

3.修改blog.html

4.新增categoryedit.html

5.修改userspace/u.js



## **企业级博客-标签功能**

**标签功能实现**

**后台编码：**

1.新建vote实体

2.新建接口VoteService

3.新建实现VoteServiceImpl 

4.新建控制器VoteController 

5.修改blog实体，增加tags属性，并实现get和set方法

6.修改UserspaceController，在修改博客的时候，将tags设置进去

**前台编码：**

1.修改blog.html，遍历标签转后的数组

2.修改blogedit.html

3.修改blogedit.js

具体见代码所示















