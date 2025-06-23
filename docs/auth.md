# Authorization
For adding a secuity layers on API requests. Lets say we want to make sure that only authorized user is allowd to write in db table using /tasks/create endpoint. 
 - Add dependency in pom.xml file
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
    </dependency>  
    ```
 - Update application.properties file
    ```
    spring.security.oauth2.resourceserver.jwt.issuer-uri=https://dev-springboot-test.us.auth0.com/
    spring.security.oauth2.resourceserver.jwt.audience=https://dev-springboot-test.us.auth0.com/api/v2/
    ```
    These URLs are created from here: https://auth0.com/
 - Secure your controller: [SecurityConfig.java](../org.learning.spring.boot.learning/src/main/java/org/learning/spring/boot/learning/security/SecurityConfig.java)