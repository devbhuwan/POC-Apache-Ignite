#!/usr/bin/env bash
docker pull cassandra
docker run --name ignite-cassandra -d  -p 9042:9042 cassandra:latest
#docker run --name ignite-cassandra -e A=passwd -d  -p 19042:9042 cassandra:latest
docker run -it --link ignite-cassandra:cassandra --rm cassandra sh -c 'exec cqlsh "$CASSANDRA_PORT_9042_TCP_ADDR"'