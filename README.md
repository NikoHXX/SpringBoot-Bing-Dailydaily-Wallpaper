>   本项目以war方式启动，不需要war启动，依赖，打包方式改成jar，删除启动类的继承和重写方法即可
##### 简单的必应今日壁纸获取，效果如图，实现了每天23点保存到服务器中
![Alt text](http://60.205.223.166:8081/bingimages/)

##### springboot项目war包启动
-   添加依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <scope>provided</scope>
</dependency>
```
-   打包方式改成war
```xml
<packaging>war</packaging>
```

-   修改启动类
```java
@SpringBootApplication
public class BingimagesApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BingimagesApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BingimagesApplication.class);
    }
}
```
##### docker部署
```
docker run -d -v /usr/local/src/Bing/war:/usr/local/tomcat/webapps -v /usr/local/src/Bing/logs:/usr/local/tomcat/logs -v /usr/local/src/Bing/images:/usr/local/src/Bing/images -p 8081:8080 tomcat
```
