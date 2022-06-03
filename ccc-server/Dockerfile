FROM eclipse-temurin:17-jdk-alpine as builder
RUN apk update
RUN apk add maven
WORKDIR /home/app
COPY . .
RUN mvn package -DskipTests -f pom.xml

FROM eclipse-temurin:17-jre-alpine

EXPOSE 8080/tcp
WORKDIR /home/app
# CHANGE: Copy the correct jar.
COPY --from=builder /home/app/target/ccc-server-1.0-SNAPSHOT.jar app.jar

ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}

CMD ["java", "-jar", "./app.jar"]