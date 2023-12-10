FROM eclipse-temurin:21.0.1_12-jre-jammy
ARG JAR_FILE
COPY target/${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
