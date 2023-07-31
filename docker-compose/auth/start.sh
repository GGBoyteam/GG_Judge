#!/bin/bash
docker stop auth
docker rm auth
docker rmi auth
cd /root/docker-compose/auth
docker build -f Dockerfile -t auth .
docker run -p 8080:8080 --name auth --restart=always auth