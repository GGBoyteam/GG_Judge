#!/bin/bash
docker stop judge
docker rm judge
docker rmi judge
cd /root/docker-compose/judge
docker build -f Dockerfile -t judge .
docker run -p 8080:8080 --name judge --restart=always judge