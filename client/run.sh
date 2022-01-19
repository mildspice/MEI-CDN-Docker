#!/bin/bash
if [ -e target/ ]
then
    java -cp target/rmi-client-1.0.jar -Djava.rmi.server.useCodebaseOnly=false -Djava.rmi.server.codebase="file:target/rmi-client-1.0.jar" -Djava.security.policy=target/classes/grant.policy Client server
else
    mvn clean install package
    
    java -cp target/rmi-client-1.0.jar -Djava.rmi.server.useCodebaseOnly=false -Djava.rmi.server.codebase="file:target/rmi-client-1.0.jar" -Djava.security.policy=target/classes/grant.policy Client server
fi