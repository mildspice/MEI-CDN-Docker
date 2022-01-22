# MEI | CDN 2021-2022
Simple IRC Client - Using Java RMI

## Create a Docker image/container for this service individually

- Make sure to open a terminal on this README directory:

- First, build and package the app, with maven:
    > mvn clean install package

- Then, create a network named 'rmi' with a 'bridge' driver if none exists:
    > docker network create --driver bridge rmi

- Then, create a volume for the client:
    > docker volume create clientvol

- Build the client app image:
    > docker build -f Dockerfile -t rmi-client:1.0 .

- Run a container using the image created. Additionally, **link the container to the volume and network created before** and **create a link to 'server'**:
    > docker run -it --attach STDIN --attach STDOUT --attach STDERR --name rmi-client -v clientvol:/clientdata --network rmi --link rmi-server:server rmi-client:1.0

<br>

### NOTE: Make sure that the server container is up and running before starting the client!