# BitEra

## 项目简介
BitEra 是一个基于 Java 技术栈构建的多功能学习/知识社区项目。后端基于 Spring Boot。系统提供文章管理、用户中心、评论互动等功能，便于快速搭建内容社区。

## 主要功能
- 文章发布、编辑、详情阅读
- 评论、点赞等互动能力
- 用户注册登录与个人信息维护
- 侧边栏公告、推荐位展示
- 账户迁移与多种绑定方式
- 后续会补充ai等功能

## 项目首页

![image-20251126203906267](https://typoraimagehosting.oss-cn-hangzhou.aliyuncs.com/img/image-20251126203906267.png)

## 技术选型

**后端技术栈**

|        技术        | 说明                              | 官网                                                         |
| :----------------: | --------------------------------- | ------------------------------------------------------------ |
| Spring & SpringMVC | Java 全栈应用框架与 Web 容器实现  | https://spring.io/                                           |
|     SpringBoot     | Spring 应用一站式开发框架         | https://spring.io/projects/spring-boot                       |
|      MyBatis       | ORM 框架                          | https://mybatis.org                                          |
|    MyBatis-Plus    | MyBatis 增强工具                 | https://baomidou.com/                                        |
| MyBatis PageHelper | MyBatis 分页插件                 | https://github.com/pagehelper/Mybatis-PageHelper             |
|   Elasticsearch    | 近实时文本搜索                   | https://www.elastic.co/cn/elasticsearch/                     |
|       Redis        | 缓存与分布式锁                   | https://redis.io                                             |
|      RabbitMQ      | 消息队列                         | https://www.rabbitmq.com                                     |
|        OSS         | 对象存储                         | https://help.aliyun.com/document_detail/31883.html           |
|       HTTPS        | 证书及安全访问                   | https://letsencrypt.org/                                     |
|        JWT         | Token 登录                       | https://jwt.io                                               |
|       Lombok       | Java 语言增强库                  | https://projectlombok.org                                    |
|      Swagger       | API 文档生成                     | https://swagger.io                                           |
|      Jackson       | JSON/XML 处理                    | https://github.com/FasterXML/jackson                         |
|     WebSocket      | 长连接能力                       | https://docs.spring.io/spring/reference/web/websocket.html   |
|  sensitive-word    | 敏感词过滤                       | https://github.com/houbb/sensitive-word                      |

## 目录结构（核心模块）
```
├── pom.xml                 # 聚合根
├── README.md
├── paicoding-api           # 对外 API/DTO 定义
├── paicoding-core          # 公共工具、配置、基础组件
├── paicoding-service       # 业务服务层（Mapper、Service、Manager）
├── paicoding-web           # Web 启动模块（Controller、配置、任务等）
├── paicoding-ui            # 前端模块（Thymeleaf 模板与静态资源）
├── logs                    # 运行日志
└── ...
```


## 环境配置说明

资源配置都放在 `paicoding-web` 模块的资源路径下，通过maven的env进行环境选择切换

当前提供了四种开发环境

- resources-env/dev: 本地开发环境，也是默认环境
- resources-env/test: 测试环境
- resources-env/pre: 预发环境
- resources-env/prod: 生产环境

环境切换命令

```bash
# 如切换生产环境
mvn clean install -DskipTests=true -Pprod
```

## 配置文件说明

- resources
  - application.yml: 主配置文件入口
  - application-config.yml: 全局的站点信息配置文件
  - logback-spring.xml: 日志打印相关配置文件
- resources-env
  - xxx/application-dal.yml: 定义数据库相关的配置信息
  - xxx/application-image.yml: 定义上传图片的相关配置信息
  - xxx/application-web.yml: 定义web相关的配置信息


## 快速开始
1. 克隆项目：
   ```bash
   git clone https://github.com/yourname/BitEra.git
   cd BitEra
   ```
2. 配置数据库，并在 `application.yml`中填写连接信息。
3. 安装依赖并启动（以 Maven 为例）：
   ```bash
   mvn clean package
   mvn spring-boot:run
   ```
   
   运行启动类 QuickForumApplication 即可。
4. 浏览器访问 `http://localhost:8080`。





