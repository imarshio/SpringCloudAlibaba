1、如果你出现如下报错，那么你就双击运行`runZipkin.bat`

```java
org.springframework.web.client.ResourceAccessException: I/O error on POST request for "http://localhost:9411/api/v2/spans": connect timed out; nested exception is java.net.SocketTimeoutException: connect timed out
	at org.springframework.web.client.RestTemplate.doExecute(RestTemplate.java:751) ~[spring-web-5.2.2.RELEASE.jar:5.2.2.RELEASE]
	at org.springframework.cloud.sleuth.zipkin2.sender.ZipkinRestTemplateWrapper.doExecute(ZipkinRestTemplateSenderConfiguration.java:228) ~[spring-cloud-sleuth-zipkin-2.2.1.RELEASE.jar:2.2.1.RELEASE]
	at org.springframework.web.client.RestTemplate.exchange(RestTemplate.java:644) ~[spring-web-5.2.2.RELEASE.jar:5.2.2.RELEASE]
	at org.springframework.cloud.sleuth.zipkin2.sender.RestTemplateSender.post(RestTemplateSender.java:129) ~[spring-cloud-sleuth-zipkin-2.2.1.RELEASE.jar:2.2.1.RELEASE]
	at org.springframework.cloud.sleuth.zipkin2.sender.RestTemplateSender$HttpPostCall.doExecute(RestTemplateSender.java:142) ~[spring-cloud-sleuth-zipkin-2.2.1.RELEASE.jar:2.2.1.RELEASE]
	at org.springframework.cloud.sleuth.zipkin2.sender.RestTemplateSender$HttpPostCall.doExecute(RestTemplateSender.java:132) ~[spring-cloud-sleuth-zipkin-2.2.1.RELEASE.jar:2.2.1.RELEASE]
	at zipkin2.Call$Base.execute(Call.java:380) ~[zipkin-2.19.0.jar:na]
	at zipkin2.reporter.AsyncReporter$BoundedAsyncReporter.flush(AsyncReporter.java:285) ~[zipkin-reporter-2.11.0.jar:na]
	at zipkin2.reporter.AsyncReporter$Flusher.run(AsyncReporter.java:354) [zipkin-reporter-2.11.0.jar:na]
	at java.lang.Thread.run(Thread.java:748) [na:1.8.0_241]
Caused by: java.net.SocketTimeoutException: connect timed out
	at java.net.DualStackPlainSocketImpl.waitForConnect(Native Method) ~[na:1.8.0_241]
	at java.net.DualStackPlainSocketImpl.socketConnect(DualStackPlainSocketImpl.java:85) ~[na:1.8.0_241]
	at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:350) ~[na:1.8.0_241]
	at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:206) ~[na:1.8.0_241]
	at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:188) ~[na:1.8.0_241]
	at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:172) ~[na:1.8.0_241]
	at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392) ~[na:1.8.0_241]
	at java.net.Socket.connect(Socket.java:606) ~[na:1.8.0_241]
	at sun.net.NetworkClient.doConnect(NetworkClient.java:175) ~[na:1.8.0_241]
	at sun.net.www.http.HttpClient.openServer(HttpClient.java:463) ~[na:1.8.0_241]
	at sun.net.www.http.HttpClient.openServer(HttpClient.java:558) ~[na:1.8.0_241]
	at sun.net.www.http.HttpClient.<init>(HttpClient.java:242) ~[na:1.8.0_241]
	at sun.net.www.http.HttpClient.New(HttpClient.java:339) ~[na:1.8.0_241]
	at sun.net.www.http.HttpClient.New(HttpClient.java:357) ~[na:1.8.0_241]
	at sun.net.www.protocol.http.HttpURLConnection.getNewHttpClient(HttpURLConnection.java:1226) ~[na:1.8.0_241]
	at sun.net.www.protocol.http.HttpURLConnection.plainConnect0(HttpURLConnection.java:1162) ~[na:1.8.0_241]
	at sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:1056) ~[na:1.8.0_241]
	at sun.net.www.protocol.http.HttpURLConnection.connect(HttpURLConnection.java:990) ~[na:1.8.0_241]
	at org.springframework.http.client.SimpleBufferingClientHttpRequest.executeInternal(SimpleBufferingClientHttpRequest.java:76) ~[spring-web-5.2.2.RELEASE.jar:5.2.2.RELEASE]
	at org.springframework.http.client.AbstractBufferingClientHttpRequest.executeInternal(AbstractBufferingClientHttpRequest.java:48) ~[spring-web-5.2.2.RELEASE.jar:5.2.2.RELEASE]
	at org.springframework.http.client.AbstractClientHttpRequest.execute(AbstractClientHttpRequest.java:53) ~[spring-web-5.2.2.RELEASE.jar:5.2.2.RELEASE]
	at org.springframework.web.client.RestTemplate.doExecute(RestTemplate.java:742) ~[spring-web-5.2.2.RELEASE.jar:5.2.2.RELEASE]
	... 9 common frames omitted
```





2、如果你出现如下错误，那就说明你的Eureka服务端有问题

- Eureka服务端没启动
- Eureka服务端地址配错了
- Eureka客户端地址配错了



```java
com.sun.jersey.api.client.ClientHandlerException: java.net.ConnectException: Connection refused: connect
	at com.sun.jersey.client.apache4.ApacheHttpClient4Handler.handle(ApacheHttpClient4Handler.java:187) ~[jersey-apache-client4-1.19.1.jar:1.19.1]
	at com.sun.jersey.api.client.filter.GZIPContentEncodingFilter.handle(GZIPContentEncodingFilter.java:123) ~[jersey-client-1.19.1.jar:1.19.1]
	at com.netflix.discovery.EurekaIdentityHeaderFilter.handle(EurekaIdentityHeaderFilter.java:27) ~[eureka-client-1.9.13.jar:1.9.13]
	at com.sun.jersey.api.client.Client.handle(Client.java:652) ~[jersey-client-1.19.1.jar:1.19.1]
	at com.sun.jersey.api.client.WebResource.handle(WebResource.java:682) ~[jersey-client-1.19.1.jar:1.19.1]
	at com.sun.jersey.api.client.WebResource.access$200(WebResource.java:74) ~[jersey-client-1.19.1.jar:1.19.1]
	at com.sun.jersey.api.client.WebResource$Builder.get(WebResource.java:509) ~[jersey-client-1.19.1.jar:1.19.1]
	at com.netflix.discovery.shared.transport.jersey.AbstractJerseyEurekaHttpClient.getApplicationsInternal(AbstractJerseyEurekaHttpClient.java:194) ~[eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.shared.transport.jersey.AbstractJerseyEurekaHttpClient.getApplications(AbstractJerseyEurekaHttpClient.java:165) ~[eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$6.execute(EurekaHttpClientDecorator.java:137) [eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.shared.transport.decorator.MetricsCollectingEurekaHttpClient.execute(MetricsCollectingEurekaHttpClient.java:73) ~[eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.getApplications(EurekaHttpClientDecorator.java:134) [eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$6.execute(EurekaHttpClientDecorator.java:137) [eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.shared.transport.decorator.RedirectingEurekaHttpClient.executeOnNewServer(RedirectingEurekaHttpClient.java:118) ~[eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.shared.transport.decorator.RedirectingEurekaHttpClient.execute(RedirectingEurekaHttpClient.java:79) ~[eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.getApplications(EurekaHttpClientDecorator.java:134) [eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$6.execute(EurekaHttpClientDecorator.java:137) [eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.shared.transport.decorator.RetryableEurekaHttpClient.execute(RetryableEurekaHttpClient.java:120) [eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.getApplications(EurekaHttpClientDecorator.java:134) [eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$6.execute(EurekaHttpClientDecorator.java:137) [eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.shared.transport.decorator.SessionedEurekaHttpClient.execute(SessionedEurekaHttpClient.java:77) [eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.getApplications(EurekaHttpClientDecorator.java:134) [eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.DiscoveryClient.getAndStoreFullRegistry(DiscoveryClient.java:1069) [eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.DiscoveryClient.fetchRegistry(DiscoveryClient.java:983) [eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.DiscoveryClient.refreshRegistry(DiscoveryClient.java:1497) [eureka-client-1.9.13.jar:1.9.13]
	at com.netflix.discovery.DiscoveryClient$CacheRefreshThread.run(DiscoveryClient.java:1464) [eureka-client-1.9.13.jar:1.9.13]
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511) [na:1.8.0_241]
	at java.util.concurrent.FutureTask.run$$$capture(FutureTask.java:266) [na:1.8.0_241]
	at java.util.concurrent.FutureTask.run(FutureTask.java) [na:1.8.0_241]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [na:1.8.0_241]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [na:1.8.0_241]
	at java.lang.Thread.run(Thread.java:748) [na:1.8.0_241]
Caused by: java.net.ConnectException: Connection refused: connect
	at java.net.DualStackPlainSocketImpl.waitForConnect(Native Method) ~[na:1.8.0_241]
	at java.net.DualStackPlainSocketImpl.socketConnect(DualStackPlainSocketImpl.java:85) ~[na:1.8.0_241]
	at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:350) ~[na:1.8.0_241]
	at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:206) ~[na:1.8.0_241]
	at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:188) ~[na:1.8.0_241]
	at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:172) ~[na:1.8.0_241]
	at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392) ~[na:1.8.0_241]
	at java.net.Socket.connect(Socket.java:606) ~[na:1.8.0_241]
	at org.apache.http.conn.scheme.PlainSocketFactory.connectSocket(PlainSocketFactory.java:121) ~[httpclient-4.5.10.jar:4.5.10]
	at org.apache.http.impl.conn.DefaultClientConnectionOperator.openConnection(DefaultClientConnectionOperator.java:180) ~[httpclient-4.5.10.jar:4.5.10]
	at org.apache.http.impl.conn.AbstractPoolEntry.open(AbstractPoolEntry.java:144) ~[httpclient-4.5.10.jar:4.5.10]
	at org.apache.http.impl.conn.AbstractPooledConnAdapter.open(AbstractPooledConnAdapter.java:134) ~[httpclient-4.5.10.jar:4.5.10]
	at org.apache.http.impl.client.DefaultRequestDirector.tryConnect(DefaultRequestDirector.java:605) ~[httpclient-4.5.10.jar:4.5.10]
	at org.apache.http.impl.client.DefaultRequestDirector.execute(DefaultRequestDirector.java:440) ~[httpclient-4.5.10.jar:4.5.10]
	at org.apache.http.impl.client.AbstractHttpClient.doExecute(AbstractHttpClient.java:835) ~[httpclient-4.5.10.jar:4.5.10]
	at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:118) ~[httpclient-4.5.10.jar:4.5.10]
	at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:56) ~[httpclient-4.5.10.jar:4.5.10]
	at com.sun.jersey.client.apache4.ApacheHttpClient4Handler.handle(ApacheHttpClient4Handler.java:173) ~[jersey-apache-client4-1.19.1.jar:1.19.1]
	... 31 common frames omitted

```



3、如果你报如下错误，那就是consul服务端没开。



```java
com.ecwid.consul.transport.TransportException: org.apache.http.conn.HttpHostConnectException: Connect to localhost:8500 [localhost/127.0.0.1, localhost/0:0:0:0:0:0:0:1] failed: Connection refused: connect
	at com.ecwid.consul.transport.AbstractHttpTransport.executeRequest(AbstractHttpTransport.java:77) ~[consul-api-1.4.1.jar:na]
	at com.ecwid.consul.transport.AbstractHttpTransport.makePutRequest(AbstractHttpTransport.java:41) ~[consul-api-1.4.1.jar:na]
	at com.ecwid.consul.v1.ConsulRawClient.makePutRequest(ConsulRawClient.java:135) ~[consul-api-1.4.1.jar:na]
	at com.ecwid.consul.v1.agent.AgentConsulClient.agentServiceRegister(AgentConsulClient.java:273) ~[consul-api-1.4.1.jar:na]
	at com.ecwid.consul.v1.ConsulClient.agentServiceRegister(ConsulClient.java:309) ~[consul-api-1.4.1.jar:na]
	at org.springframework.cloud.consul.serviceregistry.ConsulServiceRegistry.register(ConsulServiceRegistry.java:67) ~[spring-cloud-consul-discovery-2.2.1.RELEASE.jar:2.2.1.RELEASE]
	at org.springframework.cloud.consul.serviceregistry.ConsulServiceRegistry.register(ConsulServiceRegistry.java:42) ~[spring-cloud-consul-discovery-2.2.1.RELEASE.jar:2.2.1.RELEASE]
	at org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration.register(AbstractAutoServiceRegistration.java:239) ~[spring-cloud-commons-2.2.1.RELEASE.jar:2.2.1.RELEASE]
	at org.springframework.cloud.consul.serviceregistry.ConsulAutoServiceRegistration.register(ConsulAutoServiceRegistration.java:83) ~[spring-cloud-consul-discovery-2.2.1.RELEASE.jar:2.2.1.RELEASE]
	at org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration.start(AbstractAutoServiceRegistration.java:138) ~[spring-cloud-commons-2.2.1.RELEASE.jar:2.2.1.RELEASE]
	at org.springframework.cloud.consul.serviceregistry.ConsulAutoServiceRegistration.start(ConsulAutoServiceRegistration.java:73) ~[spring-cloud-consul-discovery-2.2.1.RELEASE.jar:2.2.1.RELEASE]
	at org.springframework.cloud.consul.serviceregistry.ConsulAutoServiceRegistrationListener.onApplicationEvent(ConsulAutoServiceRegistrationListener.java:63) ~[spring-cloud-consul-discovery-2.2.1.RELEASE.jar:2.2.1.RELEASE]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:172) ~[spring-context-5.2.2.RELEASE.jar:5.2.2.RELEASE]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:165) ~[spring-context-5.2.2.RELEASE.jar:5.2.2.RELEASE]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:139) ~[spring-context-5.2.2.RELEASE.jar:5.2.2.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:403) ~[spring-context-5.2.2.RELEASE.jar:5.2.2.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:360) ~[spring-context-5.2.2.RELEASE.jar:5.2.2.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.finishRefresh(ServletWebServerApplicationContext.java:165) ~[spring-boot-2.2.2.RELEASE.jar:2.2.2.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:553) ~[spring-context-5.2.2.RELEASE.jar:5.2.2.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:141) ~[spring-boot-2.2.2.RELEASE.jar:2.2.2.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:747) [spring-boot-2.2.2.RELEASE.jar:2.2.2.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:397) [spring-boot-2.2.2.RELEASE.jar:2.2.2.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:315) [spring-boot-2.2.2.RELEASE.jar:2.2.2.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1226) [spring-boot-2.2.2.RELEASE.jar:2.2.2.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1215) [spring-boot-2.2.2.RELEASE.jar:2.2.2.RELEASE]
	at com.marshio.cloudAlibaba.Payment8004Application.main(Payment8004Application.java:18) [classes/:na]
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.0_241]
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:1.8.0_241]
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:1.8.0_241]
	at java.lang.reflect.Method.invoke(Method.java:498) ~[na:1.8.0_241]
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:49) [spring-boot-devtools-2.2.2.RELEASE.jar:2.2.2.RELEASE]

```

