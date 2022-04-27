## 前言

搭建时每个项目的POM文件可以从以下[Gitee](https://gitee.com/lixiaogou/cloud2020/tree/master)中复制：



## 项目背景



### GIT

项目搭建全程手动git命令，文件的颜色表示如下状态

```
绿色，已经加入控制暂未提交
红色，未加入版本控制
蓝色，加入，已提交，有改动
白色，加入，已提交，无改动
灰色：版本控制已忽略文件；
```

#### 本地仓库搭建

```bash
# 初始话本地仓库
git init

# 关联远程仓库
git add remote <远程仓库地址>

# 拉取远程仓库文件，远程仓库建立时可能有自带的LICENSE/README.md
git pull origin main
```


#### 提交前查看文件状态

```bash
# 查看文件状态，可以列出那些文件被修改了
git status
```

![image-20220426175755785](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220426175755.png)

同步到GitHub分为多种方法，如果项目只有你一个人维护，你可以在每次写完之后全部提交到GitHub，走一套完整的流程，如果是多个人维护项目，如果你一次提交很多代码那么可能会与别人的代码发生冲突，所以此时我们就需要提交单个文件夹/文件

#### 提交单个文件

```bash
# 提交单个文件，只需要在后面加上要提交的文件名即可，剩下的流程就和提交到GitHub的步骤一样了
git add ./<文件名>

# 如下，git status 查看文件状态后发现
modified:   README.md
modified:   cloudAlibaba-consumer-order80/src/main/java/com/marshio/cloudAlibaba/controller/OrderController.java
modified:   cloudAlibaba-eureka/pom.xml
modified:   pom.xml

# 此时我想提交README.md

# 第一步，单独将README.md加到暂存区
git add ./README.md

# 第二步，提交到本地仓库
git commit -m "更新README.md"

# 第三步,提交到你的分支
git push remote main

# 同理，提交有较长路径的文件： OrderController.java，此时我们可以使用通配符
git add ./**/OrderController.java

# 提交一个文件夹或多个文件夹
git add cloudAlibaba-consumer-order80/src/main/java/com/marshio/cloudAlibaba/controller

# 提交多个文件夹
git add filePath1 filePath2 ... filePathN

# 如果想提交整个项目,
git add ./
```

#### 撤销提交

```bash
# 未使用add提交代码时，如果想撤销全部，将fileName替换成 。
git checkout fileName
git checkout .

# 使用add提交代码后，未commit之前
git reset HEAD fileName
git reset HEAD .

# 使用commit提交代码之后，未push之前，实验了一下，差点又得重码一边
git reset --h 

# 指定回退版本
git reset --h <commitid>

# 查看commitid
git log

# 退出log模式
q
```


## 项目架构

基础架构：Spring Cloud + Spring Cloud Alibaba + Spring Boot，采用分布式微服务系统架构

- 采用eureka作为服务注册中心
- 



## 父项目

### 搭建过程

#### 1、new project

new project -> Maven项目,项目名称 **SpringcloudAlibaba**

#### 2、改POM

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <!--GAV  maven 坐标-->
  <groupId>com.marshio.springcloud</groupId>
  <artifactId>SpringcloudAlibaba</artifactId>
  <version>1.0-SNAPSHOT</version>
  <!-- 选择打包方式为pom -->
  <packaging>pom</packaging>

  <description>Demo project for Springboot + Spring Cloud + Spring Cloud Alibaba</description>


  <!-- 统一管理jar包版本 -->
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <junit.version>4.12</junit.version>
    <log4j.version>1.2.17</log4j.version>
    <lombok.version>1.16.18</lombok.version>
    <mysql.version>5.1.47</mysql.version>
    <druid.version>1.1.16</druid.version>
    <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
  </properties>

  <!-- 1、只是声明依赖，并不实际引入，子项目按需声明使用的依赖 -->
  <!-- 2、子项目可以继承父项目的 version 和 scope -->
  <!-- 3、子项目若指定了 version 和 scope，以子项目为准 -->
  <dependencyManagement>
    <dependencies>
      <!--spring boot 2.2.2-->
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>2.2.2.RELEASE</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--spring cloud Hoxton.SR1-->
      <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-dependencies</artifactId>
        <version>Hoxton.SR1</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--spring cloud alibaba 2.1.0.RELEASE-->
      <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-alibaba-dependencies</artifactId>
        <version>2.1.0.RELEASE</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>

      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>${mysql.version}</version>
      </dependency>
      <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>${druid.version}</version>
      </dependency>
      <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>${mybatis.spring.boot.version}</version>
      </dependency>
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>${junit.version}</version>
      </dependency>
      <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>${log4j.version}</version>
      </dependency>
      <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>${lombok.version}</version>
        <optional>true</optional>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <!--Devtools热部署插件-->
  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
          <fork>true</fork>
          <addResources>true</addResources>
        </configuration>
      </plugin>
    </plugins>
  </build>

</project>
```

> dependencyManagement 和 dependency 的区别
>
> dependencyManagement一般用于父类管理，父POM，可以让所有在子项目中引用一个依赖而不用显示的声明版本号
>
> Maven会沿着父子层次向上走，直到找到一个拥有dependencyManagement元素的项目，然后子项目就会使用dependencyManagement元素中指定的版本

#### 3、设置项目编码格式



#### 4、设置显示过滤文件

找到setting -> Editor -> File types，注意要用分号分开

![image-20220426171849129](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220426171849.png)

## 支付模块

### 搭建过程

#### 1、new model

new model -> Maven项目,模块名称：**cloudAlibaba-provider-order80**

#### 2、改POM

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>SpringcloudAlibaba</artifactId>
        <groupId>com.marshio.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <description>支付模块，端口8001</description>

    <!--解决ignored pom.xml  file->settings->build,..->maven->ignored file->去掉勾即可-->
    <artifactId>cloudAlibaba-provider-payment8001</artifactId>


    <dependencies>

        <dependency>
            <groupId>com.marshio.springcloud</groupId>
            <artifactId>cloudAlibaba-commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <!-- web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- 监控 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- mybatis -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>

        <!-- alibaba.druid -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.2.8</version>
        </dependency>

        <!-- mysql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <!-- jdbc -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>

        <!-- devtools热部署工具 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <!-- lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!--分布式跟踪系统，有助于收集解决微服务架构中得延迟问题所需的时序数据，它管理这些数据的收集和查找，包含了sleuth+zipkin-->
        <!--sleuth为分布式追踪提供了自动配置-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>

    </dependencies>
</project>
```

#### 3、建yml

在该模块下的resource文件下新建`application.yml`文件，注意，如果此时文件没有自动变成“齿轮炮”的状态，那么需要在maven那里刷新一下

#### 4、主启动



#### 5、业务类

#### 6、开启热部署

在微服务开发过程中，我们经常需要重启服务来重新编译代码进行测试，而重启服务又是一个很麻烦的事，我们可以开启一个热部署来自动重启修改过后的服务

##### 1、添加依赖

```xml
<!-- devtools热部署工具 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

##### 2、添加插件

> 注意：需要将以下插件放入父工程POM文件

```xml
<!--Devtools热部署插件-->
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <fork>true</fork>
                <addResources>true</addResources>
            </configuration>
        </plugin>
    </plugins>
</build>
```

##### 3、开启

> 在`setting->Build->Complier`路径下开启以下选项
>
> ![image-20220424172429880](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220424172454.png)

##### 4、Set Value

> 按如下快捷键 `ctrl+alt+shift+/`，选择`registry`，对如下内容打勾
>
> ![image-20220424172844712](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220424172844.png)

##### 5、重启IDEA

此时，IDEA的热部署就完成了

8、测试


## 消费模块

### 搭建过程

#### 1、new model

new model -> Maven项目，模块名称： **cloudAlibaba-provider-order80**

#### 2、改POM

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>SpringcloudAlibaba</artifactId>
        <groupId>com.marshio.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudAlibaba-consumer-order80</artifactId>


    <dependencies>

        <!-- web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- 监控 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- mybatis -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>

        <!-- alibaba.druid -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.2.8</version>
        </dependency>

        <!-- mysql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <!-- jdbc -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>

        <!-- devtools热部署工具 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <!-- lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!--分布式跟踪系统，有助于收集解决微服务架构中得延迟问题所需的时序数据，它管理这些数据的收集和查找，包含了sleuth+zipkin-->
        <!--sleuth为分布式追踪提供了自动配置-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>

    </dependencies>
</project>
```

#### 3、建yml

在该模块下的resource文件下新建application.yml文件，注意，如果此时文件没有自动变成“齿轮炮”的状态，那么需要在maven那里刷新一下

```yaml
server:
  # 指定端口，服务端，供用户直接访问使用，所以使用默认的80端口
  port: 80

spring:
  application:
    # 服务名称
    name: cloudAlibaba-order-service
  datasource:
    # 数据源信息
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: org.gjt.mm.mysql.Driver
    url: jdbc:mysql://localhost:3306/cloudalibaba?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: 20173602

mybatis:
  # mapper文件所在位置
  mapper-locations: classpath:mapper/*.xml
  # 实体类所在文件
  type-aliases-package: com.marshio.cloudAlibaba.entities
```

#### 4、主启动

- 在Java文件夹下新建**com.marshio.cloudAlibaba.OrderApplication**的Java类
- 添加```@SpringBootApplication```的注解
- 添加```main```方法
- 添加如下代码 ```SpringApplication.run(OrderApplication.class,args);```

#### 5、业务类

注意，由于我们当前的订单业务，是直接去调用Payment的接口，并没有实际性的业务，所以我们只需要写一个```XXX Controller```去调用```PaymentController```即可.
这就是所谓的**服务调用** 



##### RestTemplate

是HTTP Client的封装版

#### 6、开启热部署

同上



#### 7、测试

请求：





#### 错误

1、 **Expected scheme-specific part at index 10: localhost:**
    这是由于自定义的url未拼接 ``` http:// ```

## 工程重构

由于两个模块中存在相同的类，这样当系统很大时，就会造成系统代码冗余，所以我们需要整理各子模块都能用到的重复代码，将其放在一个通用模块下，然后当模块想要使用其中的类时，可以通过pom文件将其导入。

冗余代码

![image-20220426133620940](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220426133621.png)



### 重构过程

1、新建commons模块

![image-20220426133814180](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220426133814.png)

2、将通用代码移到改commons模块中

3、通过pom文件将commons模块引入

```xml
<!--引入自定义的通用包-->
<dependency>
    <groupId>com.marshio.springcloud</groupId>
    <artifactId>cloudAlibaba-commons</artifactId>
    <version>${project.version}</version>
</dependency>
```





![image-20220426135330688](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220426135330.png)





![image-20220426135852655](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220426135852.png)

## Eureka注册中心

#### 1、new model

new model -> Maven项目,模块名称：**cloudAlibaba-eureka7001**

#### 2、改POM

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>SpringcloudAlibaba</artifactId>
        <groupId>com.marshio.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudAlibaba-eureka</artifactId>

    <description>使用eureka作为注册中心</description>


    <dependencies>
        <!-- 服务注册中心的服务端 eureka-server -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>

        <!-- web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- actuator  -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

    </dependencies>
</project>
```

#### 3、建yml

在该模块下的resource文件下新建`application.yml`文件，注意，如果此时文件没有自动变成“齿轮炮”的状态，那么需要在maven那里刷新一下

```yaml
server:
  # 服务端口
  port: 7001

spring:
  application:
    # 服务名称
    name: eurake7001
eureka:
  instance:
    # eureka服务端的实例名称
    hostname: eurekaServe
  client:
    # 是否将自己注册给eureka注册中心，由于自己就是服务端，所以不需要将自己注册给自己
    register-with-eureka: false
    # 服务发现，是否从Eureka注册中心获取服务列表，由于自己是服务端，无需发现服务
    fetch-registry: false
    service-url:
      # K-V，设置eureka server的地址，注意defaultZone并不是属性，只是K-V里的key
      defaultZone: http://localhost:7001/eureka/
```

#### 4、主启动

- 在Java文件夹下新建**com.marshio.cloudAlibaba.OrderApplication**的Java类
- 添加```@SpringBootApplication```的注解
- 添加```@EnableEurekaServer```的注解，标注这是Eureka服务端
- 添加```main```方法
- 添加如下代码 ```SpringApplication.run(OrderApplication.class,args);```

#### 5、业务类

无

#### 6、测试

#### 7、注册Order服务

##### 1、修改POM

找到服务提供者的POM文件，添加如下依赖

```xml
<!-- 服务注册中心的客户端 eureka-client -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

##### 2、修改YML

找到服务提供者的YML文件，添加如下代码

```yaml
eureka:
  client:
    # 表示自己需要被注册到注册中心
    register-with-eureka: true
    # 表示自己需要发现服务
    fetch-registry: true
    service-url:
      # Eureka服务端地址，可以写多个
      defaultZone: http://localhost:7001/eureka/
```

##### 3、修改启动类

找到客户端的主启动类，在类上添加注释```@EnableEurekaClient```

```java
// 标识这是一个Eureka客户端
@EnableEurekaClient
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
```

##### 4、测试

打开Eureka注册中心的页面，能发现多了一个服务

![image-20220427160858441](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220427160858.png)

#### 8、注册Payment服务

同上

#### 9、服务集群

##### 1、新建模块

新建一个名为 **cloudAlibaba-provider-payment8002** 的 model，过程参考 [支付模块](# 支付模块)

##### 2、修改YML

设置新模块的端口号为**非8001**端口

##### 3、启动服务

启动所有服务，如下

![image-20220427174106938](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220427174107.png)

