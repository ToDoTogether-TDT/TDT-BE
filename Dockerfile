FROM openjdk:11
COPY ./backend/build/libs/*.jar tdt-backend.jar
ENTRYPOINT ["java","-jar","/tdt-backend.jar"]
