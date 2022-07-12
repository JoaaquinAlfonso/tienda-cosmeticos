FROM openjdk:11
COPY "./target/TiendaDeCosmeticos-0.0.1-SNAPSHOT.jar" "back_spring.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","back_spring.jar"]