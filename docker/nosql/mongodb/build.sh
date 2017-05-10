#!/usr/bin/env bash
docker pull mongo
docker run --name ignite-mongo -p 27017:27017 -d mongo --auth
docker exec -it ignite-mongo mongo admin