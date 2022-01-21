FROM openjdk:latest
# No need to install java since the openjdk image already has that covered

WORKDIR /app

COPY target/rmi-client-1.0.jar target/
COPY target/classes target/
COPY run.sh .

VOLUME /clientdata

CMD ["sh", "/app/run.sh"]