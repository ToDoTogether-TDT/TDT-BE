FROM openjdk:11
ARG JAR_FILE=backend/build/libs/*.jar
COPY --from=build backend/build/libs/*.jar tdt-backend.jar
ENTRYPOINT ["java","-jar","/tdt-backend.jar"]
