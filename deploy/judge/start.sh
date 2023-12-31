#!/bin/bash
docker stop judge
docker rm judge
docker rmi judge
cd /root/deploy/judge
docker build -f Dockerfile -t judge .
docker run -p 8080:8080 --name judge --net=host --restart=always judge