#!/usr/bin/env bash
echo "please connect mysql using docker ip, password=passwd and port=13306"

docker run --name ignite-mysql -e MYSQL_ROOT_PASSWORD=passwd -d  -p 13306:3306 mysql:latest