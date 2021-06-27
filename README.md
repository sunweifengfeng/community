## 码匠社区
## 资料
[Spring 文档](https://spring.io/guides)  
[Github](https://github.com/codedrinker/community)  
[Github OauthDOC api](https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app)  
[Bootstrap 文档](https://v3.bootcss.com/css/#grid)  
[es社区](https://elasticsearch.cn/explore/)  
[Okhttp 用java实现Post](https://square.github.io/okhttp/)  
[MySQL 学习](https://www.runoob.com/mysql/mysql-create-database.html)  
[Spring boot学习文档](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/)  
[thymelef](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values) 
[Spring MVC](https://docs.spring.io/spring-framework/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-handlermapping-interceptor) 
[Mybatis](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)  
[pagehelper分页github](https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md)  
[markdown 开发资料](http://editor.md.ipandao.com/)  
## 工具
[git download](https://git-scm.com/download)  
[mvn 下载jar](https://mvnrepository.com/artifact/com.squareup.okio/okio/3.0.0-alpha.5) 
[Flyaway](https://flywaydb.org/)  
[Lambok](https://projectlombok.org/setup/maven)  
[Mybatis](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/) 
[JSON 在线调试](https://jsoneditoronline.org/) 
[ApiPost](https://www.apipost.cn/)  
[JQuery](https://api.jquery.com/)
[localStorage JS](https://www.runoob.com/jsref/prop-win-localstorage.html)
[localStorage HTML](https://www.runoob.com/html/html5-webstorage.html)
## 脚本
```sql
create table USER
(
	ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL ,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MOD BIGINT
);
```
```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```