# MEI | CDN 2021-2022
Simple IRC Server - Using Java RMI

## Create a Docker image/container for this service individually

- Make sure to open a terminal on this README directory:

    > mvn clean install package

    > docker build -t rmi-server:1.0 .

    > docker run -it -v ./serverdata:/serverdata rmi-server:1.0