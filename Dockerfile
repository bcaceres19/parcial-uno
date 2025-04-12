# Etapa 1: Compilar la aplicación con Maven
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
# Copiar los archivos de configuración primero para aprovechar la cache de Maven
COPY pom.xml .
# Descargar dependencias (esto se cachéa si el pom.xml no cambia)
RUN mvn dependency:go-offline -B
# Copiar el resto del código fuente
COPY src ./src
# Compilar la aplicación y ejecutar los tests si fuera necesario (se puede omitir con -DskipTests)
RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar la aplicación con una imagen optimizada
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copiar el JAR compilado desde la etapa de build
COPY --from=build /app/target/primero-0.0.1-SNAPSHOT.jar primero-ms.jar
# Exponer el puerto que utilizará la aplicación
EXPOSE 4500
# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "primero-ms.jar"]
