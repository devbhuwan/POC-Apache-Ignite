#!/usr/bin/env bash
docker run -d -p 80:80 -p 3001:3001 -v /host_absolute_path:/var/lib/mongodb --name web-console-standalone apacheignite/web-console-standalone