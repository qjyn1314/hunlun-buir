# hunlun-buir

为实现分布式事务的管理，而创建的spring-boot项目，现已实现分布式事务的正常事务，以及异常后事务的回滚

项目中使用的框架：

spring-boot:2.2.2.RELEASE

#监控分布式中每一个服务的其概况，以及是否启动成功
#需要注意的是，spring-boot-admin中adminService是单独的一个springboot服务，其client就是其他的各个服务
spring-boot-admin:2.2.1

#项目的api文档
swagger2:2.9.2

#持久层框架使用mybatis-plus
mybatis-plus-boot-starter:3.3.0

#数据库连接池使用durid
druid:1.1.21

#分布式中使用的rpc框架dubbo
dubbo-spring-boot-starter:2.0.0

#分布式中的注册中心使用zookeeper
zookeeper:3.5.6
zkclient:0.11

#阿里开源的分布式事务的解决方案
seata-all:1.0.0

项目环境：

编译器：idea2019.1

编译jar工具：maven3.5.4

数据库：mysql5.7





























 
