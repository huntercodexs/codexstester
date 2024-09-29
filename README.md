# CODEXS TESTER
- Este projeto é um espaço de trabalho completo para escrever testes unitarios e de integração


### Idiomas

- Portugues Brazil (pt-br)
- <a href="README-EN.md">English (en)</a>


### Releases

> Portugues (PT-BR)

- <a href="data/pt-br/RELEASE_1.0.4.md">Release 1.0.4</a>
- <a href="data/pt-br/RELEASE_1.0.5.md">Release 1.0.5</a>
- <a href="data/pt-br/RELEASE_1.0.6.md">Release 1.0.6</a>
- <a href="data/pt-br/RELEASE_1.0.7.md">Release 1.0.7</a>
  - Bugs de requests corrigidos
  - Adicionado modelos e exemplos de uso
  - Adicionado de configurações de segurança OAuth2
- <a href="data/pt-br/RELEASE_1.0.8.md">Release 1.0.8</a>
  - Atualizado metodo para chamada OAuth2
  - Correção de bugs no requests do tipo externos
  - Compatibilidade com Java 17


### Versões Suportadas Java

- Java 8 ![check-green.png](data/midias/check-green.png)
- Java 11 ![check-green.png](data/midias/check-green.png)
- Java 17 ![check-green.png](data/midias/check-green.png)
- Java 21 ![check-green.png](data/midias/check-green.png)

###### Sobre Java 21

Faça as alterações abaixo na sua aplicação para utilizar a vesão 21 do Java

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

Ajuste no arquivo application properties

<pre>
org.hibernate.dialect.MySQLDialect
</pre>

Ajustes no Logback

<code>

    <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %5p ${hostName} : %m%n%ex</pattern>

</code>

Altere as @Entity do projeto de javax.persistence para jakarta.persistence

<pre>
import jakarta.persistence.*;
</pre>
  
Faça a seguinte atualização no codexstester

<pre>
    protected HttpComponentsClientHttpRequestFactory externalHttpClientFactory() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        return new HttpComponentsClientHttpRequestFactory();
    }
</pre>
