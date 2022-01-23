#!/bin/bash
if [ -e target/ ]
then
    if $build_maven
    then
        mvn clean install package
    fi
else
    mvn clean install package
fi
java -cp target/rmi-server-1.0.jar -Djava.rmi.server.useCodebaseOnly=false -Djava.rmi.server.codebase="file:target/rmi-server-1.0.jar" -Djava.security.policy=target/classes/grant.policy Server localhost 1099
# EOF