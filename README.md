# Getting Started

### Expert Search Service
The RestFul Api exposes Expert Search Operations and is build with Spring-boot and Java.

Api operation can be access using once it is ran locally
* [Swagger](http://localhost:8080/swagger-ui.html#/)

### Technical Approach

* The service is using Spring Data to connect to H2 database as persistence layer.
* Spring Security must be added to restrict unauthorised access to the API.
* The API excepts and responds with both json and xml.
* The sorting and other operation are done at DB level. However for production code it would be 
  better to have some cache which is refreshed periodically and sorting can be done at code level.
* The service has some testing be implemented but a lot more can be added
* All the expected Expert search options could have been implemented within same controller endpoint 
  however it is done separately to keep separation of concerns. 
  

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

