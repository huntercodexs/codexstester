# CODEXS TESTER
- This project is a complete workspace for writing unit and integration tests


### Idiomas

- <a href="README.md">Portugues Brazil (pt-br)</a>
- English


### Releases

> Inglês (EN)

- <a href="data/en/EN-RELEASE_1.0.4.md">Release 1.0.4</a>
- <a href="data/en/EN-RELEASE_1.0.5.md">Release 1.0.5</a>
- <a href="data/en/EN-RELEASE_1.0.6.md">Release 1.0.6</a>
- <a href="data/en/EN-RELEASE_1.0.7.md">Release 1.0.7</a>
  - Request bugs fixed
  - Added templates and usage examples
  - Added OAuth2 security settings
- <a href="data/en/EN-RELEASE_1.0.8.md">Release 1.0.8</a>
  - Updated method to call OAuth2
  - Fix bugs on requests of external type
  - Java 17 compatibility

  
### Java Version Support

- Java 8 ![check-green.png](data/midias/check-green.png)
- Java 11 ![check-green.png](data/midias/check-green.png)
- Java 17 ![check-green.png](data/midias/check-green.png)
- Java 21 ![check-green.png](data/midias/check-green.png)

###### About Java 21

Make the changes below to your application to use Java version 21

Spring Boot 3

<code>

    <parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.3.2</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

</code>

Spring Boot Starter Validation

<code>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
        <version>3.3.2</version>
        <scope>runtime</scope>
    </dependency>

</code>


Java Persistence API

<code>

    <dependency>
        <groupId>javax.persistence</groupId>
        <artifactId>javax.persistence-api</artifactId>
        <version>2.2</version>
    </dependency>
    <dependency>
        <groupId>javax.validation</groupId>
        <artifactId>validation-api</artifactId>
        <version>2.0.1.Final</version>
    </dependency>

</code>

Mysql Connector 8

<code>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version><!--Java21 and Spring-Boot-3.3.2-->
        <scope>runtime</scope>
    </dependency>

</code>

Adjustment in the application properties file

<pre>
org.hibernate.dialect.MySQLDialect
</pre>

Logback Adjustments

<code>

    <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %5p ${hostName} : %m%n%ex</pattern>

</code>

Change the project's @Entity from javax.persistence to jakarta.persistence

<pre>
import jakarta.persistence.*;
</pre>

Make the following update in codexstester

<pre>
    protected HttpComponentsClientHttpRequestFactory externalHttpClientFactory() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        return new HttpComponentsClientHttpRequestFactory();
    }
</pre>

