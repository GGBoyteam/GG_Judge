#!/bin/bash
name=system
docker stop $name
docker rm $name
docker rmi $name
cd /root/docker-compose/$name
docker build -f Dockerfile -t $name .
docker run -p 8080:8080 --name $name --restart=always $name