## 码匠社区
## 资料
[Spring 文档](https://spring.io/guides)
<br/>
[Github](https://github.com/codedrinker/community)
<br/>
[Github OauthDOC api](https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app)
<br/>
[Bootstrap 文档](https://v3.bootcss.com/css/#grid)
<br/>
[es社区](https://elasticsearch.cn/explore/)
<br/>
[Okhttp 用java实现Post](https://square.github.io/okhttp/)
<br/>
[MySQL 学习](https://www.runoob.com/mysql/mysql-create-database.html)
<brl>
[Spring boot学习文档](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/)
## 工具
[git download](https://git-scm.com/download)
<br/>
[mvn 下载jar](https://mvnrepository.com/artifact/com.squareup.okio/okio/3.0.0-alpha.5)
<br/>
[Flyaway](https://flywaydb.org/)
<br/>
[Lambok](https://projectlombok.org/setup/maven)
<br/>
[thymelef](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)
<br/>
[Mybatis](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
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
```