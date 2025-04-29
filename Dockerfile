FROM ubuntu
LABEL authors="Jac0912"
RUN  apt update && apt install -y openjdk-17-jdk
COPY target/AI-Teacher-0.0.1-SNAPSHOT.jar app.jar
CMD java -Duser.timezone=Asia/Shanghai -jar app.jar
EXPOSE 8080