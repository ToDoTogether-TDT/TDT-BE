FROM openjdk:11
COPY ./backend/build/libs/backend-0.0.1-SNAPSHOT.jar tdt-backend.jar
ENTRYPOINT ["java","-jar","/tdt-backend.jar"]
