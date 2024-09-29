# CODEXS TESTER
- This project is a complete workspace for writing unit and integration tests


# Versions

- Java8
- Java11
- Java17
- Java21
  - Pre-Requisites
    - Spring Boot 3.3.2
    - spring-boot-starter-validation 3.3.2
    - javax.persistence-api 2.2
    - validation-api 2.0.1.Final
    - mysql-connector-java 8.0.33
      - org.hibernate.dialect.MySQLDialect
    - logback adjusts
      - <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %5p ${hostName} : %m%n%ex</pattern>
    - change from javax.persistence to jakarta.persistence in the Entity
      - import jakarta.persistence.*;
    - codexstester adjusts
      - protected HttpComponentsClientHttpRequestFactory externalHttpClientFactory() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        return new HttpComponentsClientHttpRequestFactory();
        }


# Idiomas

- <a href="README.md">Portugues Brazil (pt-br)</a>
- English


# Releases

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
