#!/usr/bin/env bash
export X_RUN_MODE=$1
export X_JAR="./build/libs/poc-apache-ignite-app1-corporate-service-1.0-SNAPSHOT.jar"
export X_DEBUG_PARAM="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n"

gradle clean build

if [ "$X_RUN_MODE" == "debug" ]
then
    echo "Debug Mode"
    java -jar $X_DEBUG_PARAM $X_JAR
else
    echo "Normal Mode"
    java -jar $X_JAR
fi
