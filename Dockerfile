FROM openjdk:17-jdk-alpine
EXPOSE 8056
COPY target/api-0.0.1-SNAPSHOT.jar app-1.0.0.jar
ENTRYPOINT [ "java", "-jar", "app-1.0.0.jar" ]

#BASED ON:
#https://dev.to/francescoxx/java-crud-rest-api-using-spring-boot-hibernate-postgres-docker-and-docker-compose-5cln