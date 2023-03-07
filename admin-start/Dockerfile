FROM eclipse-temurin:17.0.6_10-jre-jammy
ARG JAR_FILE
COPY target/${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
