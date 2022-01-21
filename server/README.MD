# MEI | CDN 2021-2022
Simple IRC Server - Using Java RMI

## Create a Docker image/container for this service individually

- Make sure to open a terminal on this README directory:

- First, create a network named 'rmi' with a 'bridge' driver if none exists:
    > docker network create --driver bridge rmi

- Build the client app image:
    > docker build -t rmi-server:1.0 .

- Run a container using the image created. Additionally, **configure a volume** and **add the container to the created network**:
    > docker run -it --name rmi-server -v ./serverdata:/serverdata --network rmi rmi-server:1.0