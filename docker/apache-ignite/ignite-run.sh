#!/usr/bin/env bash
docker pull apacheignite/ignite
docker run -it --net=host -e "IGNITE_CONFIG=https://raw.githubusercontent.com/apache/ignite/master/examples/config/example-cache.xml" apacheignite/ignite