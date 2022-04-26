# 前言

因为在本模块涉及到了Order服务与Payment服务之间的调用，服务间的调用不同于在一个项目中调用API那样简单，服务与服务之间端口不同，IP也可能不同，导致了服务与服务之间的调用没那么简单。
常见的远程服务调用方式有两种

- RPC
- HTTP

他们最本质的区别就是RPC服务是基于TCP/IP协议的，而HTTP服务是基于HTTP协议的，而HTTP协议是应用在应用层上的，TCP/IP是应用在传输层协议上的，这就导致了RPC效率会更高一点。应用层需要先进行三次握手建立连接才能互通。
部分参考自：[服务调用](https://www.sohu.com/a/325862611_268033)、[直观讲解一下 RPC 调用和 HTTP 调用的区别！](https://baijiahao.baidu.com/s?id=1685504157302223127&wfr=spider&for=pc)


## OSI网络的七层模型

因为上面说到了两种服务是基于不同协议的，所以我们有必要先来了解一下OSI七层模型（实际应用中基本只有五层模型）

- 第7层：应用层，即各种应用程序所在的层，规定了各种应用程序协议，如HTTP协议，也是HTTP远程服务调用的地方
- 第6层：表示层，定义不同的数据传输格式，编码解码规范
- 第5层：会话层，负责与其他服务建立连接，管理用户的会话
- 第4层：传输层，管理网络中端到端的数据传输，规定了TCP和UDP两种传输协议，也是RPC远程服务调用的地方
- 第3层：网络层，定义网络间设备如何传输数据
- 第2层：数据链路层，将上层网络层的数据包装成帧，然后传输给物理层
- 第1层：物理层，将帧转化成比特流，进行透明的比特流传输

![image-20220426093903458](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220426093903.png)

在实际应用中，是没有表示层和会话层的，因为他们和应用层合并了。



# RPC

RPC，Remote Procedure Call，远程过程调用，即调用远程机器的服务，工作在传输层，自定义数据格式，所以需要双方约定好数据格式（如JSON）。
其优点是速度快，效率高，早期的webService,现在的dubbo都是RPC的代表

常见RPC的实现

- dubbo
- gRPC
- Thrift


# HTTP

HTTP，基于HTTP协议的远程服务调用，由于Http已经约定了数据格式，所以在传输时只需要按照Http协议的数据格式发送数据/接收数据即可，并且对语言无限制，只需要直到对方的服务接口就可以调用。
缺点是当请求的数据过多时，会导致请求体很大，传输速度变慢。
例如 输入baidu.com 搜索就是我们在调用百度的搜索服务，你可以在任何语言中发起对百度搜索接口的访问（当然，你需要知道百度的请求格式）。

HTTP常见的实现

- HttpClient 
- OKHttp
- URLConnection
- jsoup

很多HTTP远程服务调用的实现都是采用的Restful风格。

举个:chestnut:

```java
// 提供的服务接口为：http://localhost:8001/payment/getPaymentById/11
public CloseableHttpResponse httpClientRPC(String url) {
    // url = http://localhost:8001/payment/getPaymentById/11，在实际使用中都是通过拼接获取url的
    // 开启默认的客户端 
    CloseableHttpClient httpClient = HttpClients.createDefault();
    // 新建get请求
    HttpGet httpGet = new HttpGet(url);
    // 声明响应体
    CloseableHttpResponse response = null;
    try{
        // 执行请求
        response = httpClient.execute(httpGet);
        if(response.getStatusLine().getStatusCode() == 200){ // 判断响应状态
            // 获取返回结果，这里根据需要获取，我这里只是单纯的演示，实际上应该是用到JSON等数据格式获取
            String rt = EntityUtils.toString(response.getEntity(), "UTF8");
            // 日志打印
            log.info(rt);
        }
    }catch (Exception e){
        log.info("没获取到");
    }
    // 返回响应体
    return response;
}
```

Http远程服务调用在Spring中被封装成了RestTemplate，其包含多种HTTP客户端的使用

举个:chestnut:
```java
// 以本次的Order服务调用Payment服务为例
// 
@GetMapping("/comsumer/payment/get/{id}")
public ResponseBean<Payment> getPayment(@PathVariable("id") Long id) {
    String url = PAYMENT_URL + "/payment/getPaymentById/" + id;
    log.info("url:" + url);
    // 根据url发起请求，返回类型为指定的 ResponseBean.class
    ResponseBean forObject = restTemplate.getForObject(url, ResponseBean.class);
    return forObject;
}
```