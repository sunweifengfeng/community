## 码匠社区

## 部署服务器
### 依赖
- git 下载代码
- JDK 编译
- maven 构建项目
- MySQL 部署到生产环境中的数据库  
### 步骤
- yum update 更新源 由于不知道系统多久没有更新了
- yum install git 安装git 用git status查看是否成功
- mkdir app 创建app文件
- cd app进入app文件项目目录
- git clone https://github.com/sunweifengfeng/community.git 进入clone阶段
- cd community/进入到我们的项目目录里  
- yum install mawen 安装maven
- mvn -v 看maven版本
- pwd 看当前所在的文件夹
- mvn clean compile package  其中clean可以加可以不加 作用是编译项目 并打包(jar) 第一次回去下载依赖
- more src/main/resources/application.properties more查看指定文件夹下的文件内容 这里的配置是我们本地的 需要进行服务端的配置
- cp src/main/resources/application.properties src/main/resources/application-production.properties cp命令时复制文件 并对新文件命名  
- git status查看是否复制成功
- vim src/main/resources/application-production.properties 编辑文件 为什么单独创建文件 不要在服务器中改原来的 而且容易泄露  
- 修改github的个人资料的url 和callback 为创建服务器的外网ip或者我的域名
- mvn package 把新功能再打进包内  其中启动项目用javac -jar就可以
- java -jar -Dspring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar 运行程序 profile是用于区分不同环境
- 复制外网ip到浏览可以访问我们的web  但是此时不对需要修改回调地址navigation.html中的回调地址为：
<a th:href="@{https://github.com/login/oauth/authorize(client_id='2859958f9f059979ed3a',
redirect_uri=${#httpServletRequest.getServletContext().getAttribute('redirectUri')},scope='user',state=1)}">
上述是在本地修改 需要上传到git 中
在本地git add commit push
- ps -aux | grep java 检查当前进程是否存在
- 在服务器git pull 拉倒远端的git代码
- mvn package  
ctrl R java 再ctrl R就可以翻到历史 再次运行java -jar -Dspring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar
还是不对 因为之前重新调整了question和user的Id 但是不是自增的 所以新增V13
ALTER TABLE QUESTION ALTER COLUMN ID BIGINT auto_increment NOT NULL;
ALTER TABLE USER ALTER COLUMN ID BIGINT auto_increment NOT NULL;
任何本地进行git add -p通过n和y控制上传哪一个 然后 git commit git push
- ctrl C 关掉进程
- ctrl K 把命令行清掉
- git pull 拉到最新代码
- mvn flyway:migrate 如果运行了一次mvn flyway:migrate但是有错误 就是已经产生了一次V版本 再次运行的时候会报错 
可以用 mvn flyway:repair进行修复或者手动删除
然后再进行mvn flyway:migrate
- 再次运行项目java -jar -Dspring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar
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
[ucloud 服务器学习](https://console.ucloud.cn/ufile/ufile)  
[ucloud ufile](https://github.com/ucloud/ufile-sdk-java)     
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
[spring boot log](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging)  
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