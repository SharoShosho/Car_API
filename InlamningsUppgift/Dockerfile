FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Kopiera Maven-filer
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Gör mvnw körbart
RUN chmod +x ./mvnw

# Kopiera källkod
COPY src ./src

# Bygg applikationen
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

# Kör applikationen - ANPASSAD FÖR DITT PROJEKT
ENTRYPOINT ["java", "-jar", "target/inlamnings-uppgift-1.0.0.jar"]