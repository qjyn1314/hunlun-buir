# hunlun-buir

为学习分布式项目的管理，而创建的spring-boot项目

### 项目中使用的框架：

spring-boot:2.2.2.RELEASE

### 监控分布式中每一个服务的其概况，以及是否启动成功
### 需要注意的是，spring-boot-admin中adminService是单独的一个springboot服务，其client就是其他的各个服务
spring-boot-admin:2.2.1

### 项目的api文档
swagger2:2.9.2

### 持久层框架使用mybatis-plus
mybatis-plus-boot-starter:3.3.0

### 数据库连接池使用durid
druid:1.1.21  

### 分布式中使用的rpc框架dubbo
org.apache.dubbo:dubbo-spring-boot-starter:2.7.5

### 分布式中的注册中心使用zookeeper
zookeeper:3.5.6
zkclient:0.11

### 分布式中的验证框架使用springsecurity
spring-boot-starter-security 2.2.2 

## 项目环境：

编译器：idea2019.1

编译jar工具：maven3.5.4

数据库：mysql5.7

账号：admin
密码：admin

## 生产线上环境
java -jar 部署的环境地址：evening.hulunbuir.vip
docker中部署的环境地址：docker.hulunbuir.vip

##本地部署步骤：

1、执行evening.sql文件

以及此目录下的 hunlun-buir\calm-datasource\src\main\resources\datasource_conf.sql 文件。

2、更改application-dev.properties 配置
mysql用户名和密码
密码可参考： com.hulunbuir.clam.parent.tool.JasyptUtil 类

3、启动项目。

访问地址：127.0.0.1:8036

接口文档地址：127.0.0.1:8036/swagger-ui/index.html
