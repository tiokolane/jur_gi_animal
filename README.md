# jur_gi_auth

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![Coverage Status](https://coveralls.io/repos/github/codecentric/springboot-sample-app/badge.svg?branch=master)](https://coveralls.io/github/codecentric/springboot-sample-app?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven ](https://maven.apache.org)

## Running the application locally

Open src/main/resources/application.properties
Don’t forget to change the spring.datasource.username and spring.datasource.password as per your MySQL installation. 

You don’t need to create any tables. The tables will automatically be created by Hibernate from the Employee entity that we will define in the next step. This is made possible by the property spring.jpa.hibernate.ddl-auto = update.

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```


Swagger
http://localhost:8080/swagger-ui/index.html

Open API

http://localhost:8080/v3/api-docs



ADD ROLE_USER to mysql table ROLE