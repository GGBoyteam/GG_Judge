FROM frolvlad/alpine-java:jdk8-slim
ARG JAR_FILE=GG_judgeServer-0.0.1-SNAPSHOT.jar
ARG PORT=8080
ARG Xms="128M"
ARG Xmx="256M"
ARG PermSize="64M"
ARG MaxPermSize="128M"
ADD ./$JAR_FILE app.jar
ENV server_port="--server.port=${PORT}"
ENV xms="-Xms"+$Xms
ENV xmx="-Xmx"+$Xmx
ENV permsize="-XX:PermSize="+$permsize
ENV maxpermsize="-XX:MaxPermSize="+$maxpermsize
ENTRYPOINT java -jar /app.jar $server_port $xms $xmx $permsize $maxpermsize
