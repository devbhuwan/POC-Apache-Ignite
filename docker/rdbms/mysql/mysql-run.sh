#!/usr/bin/env bash
docker pull mysql
docker run --name ignite-mysql -e MYSQL_ROOT_PASSWORD=passwd -d  -p 13306:3306 mysql:latest