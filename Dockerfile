# Usando OpenJDK 21
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Instala dependências básicas
RUN apk update && apk add --no-cache bash curl git netcat-openbsd

# Copia Maven wrapper e pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x mvnw

# Baixa dependências offline
RUN ./mvnw dependency:go-offline -B

# Copia código-fonte
COPY src ./src

# Build da aplicação
RUN ./mvnw clean package -DskipTests

# Expõe a porta do Spring Boot
EXPOSE 8081

# Comando para iniciar a API
CMD ["java", "-jar", "target/mscadastroclientes-0.0.1-SNAPSHOT.jar"]
