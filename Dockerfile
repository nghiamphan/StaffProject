FROM openjdk:17

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN mvn dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]