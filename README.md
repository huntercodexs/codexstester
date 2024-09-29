# CODEXS TESTER
- Este projeto é um espaço de trabalho completo para escrever testes unitarios e de integração


# Versões

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

- Portugues Brazil (pt-br)
- <a href="README-EN.md">English (en)</a>


# Releases

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
