# Nacos



## 下载



我们可以在[Nacos-Git官网](https://github.com/alibaba/nacos/releases)中下载合适的版本。

![image-20220707211007614](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/image-20220707211007614.png)

Nacos分Linux版和Windows版，两者并无太大区别，我们在学习过程中可以使用WIndows版。



## 配置

Nacos启动分为单机和集群模式。



### 单机模式

```bash
# 单机启动命令,需要切换到bin目录下,并指定启动模式为standalone模式
startup.sh -m standalone
```





### 集群模式

```bash
# 配置数据库，在conf目录下编辑application.properties
### If use MySQL as datasource:
spring.datasource.platform=mysql

### Count of DB:
db.num=1

### Connect URL of DB:记得换成自己的地址
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user.0=nacos
db.password.0=nacos
```



### 其他配置

```bash
### If turn on auth system:如果你开启了认证系统，就必须要配置deng'lu
nacos.core.auth.enabled=true
```

