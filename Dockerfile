FROM openjdk:17
EXPOSE 8082
ADD target/employee-service-images.jar employee-service-images.jar
ENTRYPOINT ["java","-jar","/employee-service-images.jar"]