# MEI | CDN 2021-2022
Simple IRC Client - Using Java RMI

## Create a Docker image/container for this service individually

- Make sure to open a terminal on this README directory:

- First, create a network named 'rmi' with a 'bridge' driver if none exists:
    > docker network create --attachable --driver bridge rmi

- Then, create a volume for the client:
    > docker volume create clientvol

- Build the client app image:
    > docker build -t rmi-client:1.0 .

- Run a container using the image created. Additionally, **configure a volume**, **add the container to the created network** and **create a link to 'server'**:
    > docker run -it --name rmi-client -v clientvol:/clientdata --network rmi --link rmi-server:server rmi-client:1.0

```diff
! MAKE SURE TO RUN THE CLIENT BEFORE THE SERVER
```