#!/bin/bash
name=system
docker stop $name
docker rm $name
docker rmi $name
cd /root/deploy/$name
docker build -f Dockerfile -t $name .
docker run -p 8080:8080 --net=host --name $name --restart=always $name