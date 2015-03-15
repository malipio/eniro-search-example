----------------------------------------------------
This project demonstrates usage of Eniro Search API with Spring Boot (MVC, Data JPA, Jackson)
and PojoBuilder.
----------------------------------------------------
Run in Tomcat 7:
mvn package tomcat7:run 

Run in Tomcat 8 embedded container:
mvn package
java -jar target\eniro-search-example-1.0.0-SNAPSHOT.war

Note that:
1. Java 8 is required
2. Some tests are integration tests and require access to Eniro API.  