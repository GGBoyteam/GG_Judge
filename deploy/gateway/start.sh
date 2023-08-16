#!/bin/bash
docker stop gateway
docker rm gateway
docker rmi gateway
cd /root/docker-compose/gateway
docker build -f Dockerfile -t gateway .
docker run -p 8080:8080 --name gateway --net=host --restart=always gateway