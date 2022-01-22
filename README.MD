# MEI | CDN 2021-2022
RMI file transfer with Docker containers.

## Build and Execute with docker-compose

*If you want to build each image individually, check the README.md files on the client and server directories*

- Make sure to have [Docker](https://www.docker.com/get-started) installed on your machine.

- Open a terminal on this README directory and run:
    > docker-compose up

    *this command is enough to make everything running, though it is recomended to check on other relevant ``docker-compose`` commands, like ``docker-compose build`` to rebuild an image when changes occur*

    *docker-compose uses the ``depends_on`` flag to ensure the client container runs after the server container as started, although if both images were not already built the client container may still start before the server (since the client image may build faster than the server)*

- To open an STDIN to interact with the client-container (make sure the container has started):
    > docker attach rmi-client-maven