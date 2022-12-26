FROM openjdk:11
COPY ./backend/build/libs/backend-0.0.1-SNAPSHOT.jar tdt-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/tdt-backend.jar"]
