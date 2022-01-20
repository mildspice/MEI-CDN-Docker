FROM openjdk:latest
# No need to install java since the openjdk image already has that covered

ARG MAVEN_VERSION=3.3.9
ARG USER_HOME_DIR="/root"
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p /usr/share/maven \
  && curl -fsSL ${BASE_URL}/apache-maven-$MAVEN_VERSION-bin.tar.gz \
    | tar -xzC /usr/share/maven --strip-components=1 \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

WORKDIR /app

COPY ../src ./src
COPY ../pom.xml .
COPY run_maven.sh run.sh

VOLUME /serverdata

CMD ["sh", "/app/run.sh"]