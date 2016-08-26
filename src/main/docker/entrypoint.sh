#!/bin/env sh
PROFILE=${ACTIVE_PROFILE:=default}
HOST=${GOCD_SERVER_HOST:=gocd-server}
PORT=${GOCD_SERVER_PORT:=8153}
ROOT_PATH=${GOCD_SERVER_ROOT:=/go}
USERNAME=${GOCD_USERNAME:=admin}
PASSWORD=${GOCD_PASSWORD:=badger}

java -Xmx512m -Djava.security.egd=file:/dev/./urandom \
-cp $JAVA_HOME/lib/*:/lean/java/lib/*:/graph-service.jar com.thoughtworks.lean.graph.GraphService \
--spring.profiles.active=$PROFILE
