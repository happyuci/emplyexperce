FROM 10.51.151.1:5000/ygopenjdk:8-jre-alpine
LABEL MAINTAINER="DevCloud <zhangying14@ygsoft.com>"

ADD target/com.ygsoft.necp.boot-1.1.0-SNAPSHOT.jar /app/com.ygsoft.necp.boot-1.1.0-SNAPSHOT.jar

EXPOSE 9999

CMD ["java","-jar","/app/com.ygsoft.necp.boot-1.1.0-SNAPSHOT.jar"]