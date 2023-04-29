FROM openjdk:17-alpine3.14
COPY ./target/marketservice-0.0.1-SNAPSHOT-shaded.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]