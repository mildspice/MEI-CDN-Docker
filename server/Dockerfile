FROM openjdk:latest
# No need to install java since the openjdk image already has that covered

WORKDIR /app

COPY target/rmi-server-1.0.jar target/
COPY run.sh .

VOLUME /serverdata

CMD ["sh", "/app/run.sh"]