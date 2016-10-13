FROM registry.aliyuncs.com/leansw/java-base
EXPOSE 9900
COPY lib /lean/java/lib
RUN touch /lean/java/lib/*.jar
ADD entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT sh /entrypoint.sh
VOLUME /tmp
ADD graph-service-*-SNAPSHOT.jar /graph-service.jar
RUN touch /graph-service.jar