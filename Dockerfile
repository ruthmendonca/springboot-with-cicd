FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copia o jar gerado (o workflow baixa o artifact e o coloca aqui como app.jar)
COPY app.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
