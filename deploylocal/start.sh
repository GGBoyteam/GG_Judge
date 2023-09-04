yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
				  
yum install -y yum-utils

yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
	
yum install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

systemctl enable docker

mkdir -p /etc/docker

tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://qkjfxye4.mirror.aliyuncs.com"]
}
EOF

systemctl daemon-reload


systemctl restart docker


cd `dirname $0`


docker stop mysql
docker rm mysql
docker rmi mysql
docker build -f ./mysql_dockerfile -t mysql .
docker run \
--name mysql \
--restart=always \
-e MYSQL_ROOT_PASSWORD=root \
-p 3306:3306 \
-d mysql

docker stop nacos
docker rm nacos
docker run -it \
-e NACOS_AUTH_ENABLE=true \
-e PREFER_HOST_MODE=ip \
-e MODE=standalone \
-e SPRING_DATASOURCE_PLATFORM=mysql \
-e MYSQL_SERVICE_HOST=172.17.0.1 \
-e MYSQL_SERVICE_PORT=3306 \
-e MYSQL_SERVICE_DB_NAME=nacos \
-e MYSQL_SERVICE_USER=root \
-e MYSQL_SERVICE_PASSWORD=root \
-p 8848:8848 \
-p 9848:9848 \
-p 9849:9848 \
--name nacos \
--restart=always \
nacos/nacos-server:2.0.3
