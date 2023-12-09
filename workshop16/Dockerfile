# start with this linux server
FROM maven:3.9-eclipse-temurin-21

#build the application

#create directory call /app ('/' means root)
# go into the directory cd /app
WORKDIR /app

#copy files into /app directory
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
#copy directory - write 2 times
COPY .mvn .mvn
COPY src src

#Build the application - this run is for building
RUN mvn package -Dmaven.test.skip=true

# run the application 
# define railway environment variable
ENV PORT=8080 

#expose this port - reference environment variable
EXPOSE ${PORT} 

#run the program 
ENTRYPOINT SERVER_PORT=${PORT} java -jar target/workshop16-0.0.1-SNAPSHOT.jar
