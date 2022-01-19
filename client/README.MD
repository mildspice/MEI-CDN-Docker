# MEI | CDN 2021-2022
Simple IRC Client - Using Java RMI

## Create a Docker image/container for this service individually

- Make sure to open a terminal on this README directory:

    > mvn clean install package

    > docker build -t rmi-client:1.0 .

    > docker run -it -v ./clientdata:/clientdata rmi-client:1.0