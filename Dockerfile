FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN apk update && apk add tzdata &&\
    cp /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime &&\
    echo "America/Sao_Paulo" > /etc/timezone &&\
    apk del tzdata && rm -rf /var/cache/apk/*
CMD chown root:root /etc/crontabs/root && /usr/sbin/crond -f
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]