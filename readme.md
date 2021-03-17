## 平台简介
  基于SpringBoot开发的一套敏捷开发基础框架，主要为中小型团队或个人开发者提供，同时支持分布式部署和单机部署。后台管理基于Vue开发，提供相关部门管理，角色管理，用户管理，菜单管理，数据权限控制等多个基础功能模块。    另外内置了一个基于平台开发的小型CMS系统，可以用于企业官网，展示型网站等小型网站快速搭建（不需要移除相关依赖即可），更多功能持续更新。
## 项目特点
1. 兼容支持单机和分布式部署
2. 灵活的数据权限控制，多数情况下开发人员无需关注数据权限
## 在线体验
- 后管地址：
  [http://slash.lanmushan.site:6688/](https://mp.weixin.qq.com/s/lM808MxUu6tp8zU8SBu3sg)

- CMS展示体验

  http://blog.lanmushan.site

- 登录账号：admin   登录密码：123456 

- 详细文档地址：[开发手册](https://www.yuque.com/u134302/slash)
## 技术简介
### 后端主要技术框架

| 名称                          |        版本         |           描述 |
| :---------------------------- | :-----------------: | -------------: |
| spring-cloud                  |  Greenwich.RELEASE  |                |
| spring-cloud-alibaba          |    2.1.0.RELEASE    |                |
| spring-boot                   |    2.1.8.RELEASE    |                |
| mysql                         |       8.0.16        |                |
| mybatis+tk.mybatis+pagehelper | 3.5.5+2.1.5+5.2.0 | 数据库持久相关 |
| redis                         |                     |                |
| seata-server                  |        1.1.0        |     分布式事务 |
| nocas                         |        1.2.1        |       注册中心 |


### 后管主要技术框架

| 名称       |  版本  | 描述 |
| :--------- | :----: | ---: |
| vue        | 2.5.2  |      |
| element-ui | 2.13.1 |      |
| axios      | 0.19.2 |      |
### CMS主要技术
框架

| 名称       | 版本  |       描述 |
| :--------- | :---: | ---------: |
| freemarker |  18   | 用于页面渲染 |
| groovy     | 3.0.7 | 脚本用于动态更新 |
## 内置功能模块
### 基础服务
- 字典管理：对系统中经常使用的一些较为固定的数据进行维护。
- 操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
- 登录日志：系统登录日志记录查询包含登录异常。
- 连接池监视：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。
### 权限服务
- 用户管理：用户是系统操作者，该功能主要完成系统用户配置。
- 部门管理：配置树结构展现支持。
- 岗位管理：配置系统用户所属担任职务。
- 菜单管理：配置系统菜单，操作权限，按钮权限标识等。
- 角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
- 在线用户：实时查看在线用户（单个账号只能单处登录）
### CMS服务
- 数据源管理:支持Sql,Json,String,GroovyScript脚本等多种动态数据来源配置
- 请求管理：动态添加RequestMapping映射，进行处理，关联模板和数据，直接展示页面，开发方便
- 文章管理：管理CMS基础文章数据
- 文章类别：维护CMS基础文章类别数据
- 文件管理：针对服务器资源文件进行管理（不完善）
### 其他
- 代码生成：提供代码生成模板，自行使用IDEA中EasyCode插件进行生成（暂无前端模板）。
## 开发运行部署
请参考 [开发手册](https://www.yuque.com/u134302/slash)



![登录效果](https://cdn.nlark.com/yuque/0/2021/png/271631/1615991423771-6c1584b2-6cf9-4754-b8c5-c72992acb226.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_14%2Ctext_U2xhc2g%3D%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10%2Fresize%2Cw_746)

![img](https://cdn.nlark.com/yuque/0/2021/png/271631/1615991479484-fb57c31f-35e0-40b2-9664-b0d328736340.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_14%2Ctext_U2xhc2g%3D%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10%2Fresize%2Cw_746)

![img](https://cdn.nlark.com/yuque/0/2021/png/271631/1615991489091-3dfc966d-f13f-4f9a-9166-86fc296676fe.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_14%2Ctext_U2xhc2g%3D%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10%2Fresize%2Cw_746)

![img](https://cdn.nlark.com/yuque/0/2021/png/271631/1615991500261-45e5ec9d-70c6-4dba-9c46-bb8973dddd28.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_14%2Ctext_U2xhc2g%3D%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10%2Fresize%2Cw_746)

![img](https://cdn.nlark.com/yuque/0/2021/png/271631/1615991517736-ab10ffe7-4f08-4e2b-a536-27a00345d937.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_14%2Ctext_U2xhc2g%3D%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10%2Fresize%2Cw_746)

![img](https://cdn.nlark.com/yuque/0/2021/png/271631/1615991787711-474003e1-5046-4fea-93d6-af11d0a1f11d.png?x-oss-process=image%2Fwatermark%2Ctype_d3F5LW1pY3JvaGVp%2Csize_14%2Ctext_U2xhc2g%3D%2Ccolor_FFFFFF%2Cshadow_50%2Ct_80%2Cg_se%2Cx_10%2Cy_10%2Fresize%2Cw_746)

