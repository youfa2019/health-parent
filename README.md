# 传智健康

### 传智健康项目简介

- 传智健康管理系统是一款应用于健康管理机构的业务系统，实现健康管理机构工作内容可视化、会员管理专业化、健康评估数字化、健康干预流程化、知识库集成化，从而提高健康管理师的工作效率，加强与会员间的互动，增强管理者对健康管理机构运营情况的了解。
- 项目包括前端和后端，前端基于Vue+ElementUI，后端基于SSM，并且使用dubbo+zookeeper实现远程调用。

### 传智健康项目功能点

1. web端

   - 预约管理-检查项管理


   - 预约管理-检查组管理
   - 预约管理-套餐管理
   - 预约管理-套餐管理
   - 报表管理

2. 移动端

   - 用户登录及权限控制
   - 体检预约

### 传智健康技术点

1. 采用SSM架构
2. 使用RPC框架dubbo
3. 使用zookeeper作为注册中心
4. 分布式文件系统FastDFS
5. 使用权限认证框架SpringSecurity
6. 分布式缓存Redis
7. 第三方短信平台阿里云短信
8. 第三方云存储七牛云
9. 微信公众号开发平台
10. 百度Echarts生成图表
11. quartz定时任务框架
12. apache POI导入导出Excel表格
13. 前端采用VUE+ElementUI+ajax
14. 及Redis、ElasticSearch服务集群

### 模块简介

#### 功能模块

- health_parent：父工程，打包方式为pom，统一锁定依赖的版本，同时聚合其他子模块便于统一执行maven命令
- health_common：通用模块，打包方式为jar，存放项目中使用到的一些工具类和常量类
- health_pojo：打包方式为jar，存放实体类和返回结果类等
- health_dao：持久层模块，打包方式为jar，存放Dao接口和Mapper映射文件等
- health_interface：打包方式为jar，存放服务接口
- health_service：Dubbo服务模块，打包方式为**war**，存放服务实现类，作为服务提供方，需要部署到tomcat运行
- health_web：打包方式为war，作为Dubbo服务消费方，存放Controller、HTML页面、js、css、spring配置文件等，需要部署到tomcat运行

