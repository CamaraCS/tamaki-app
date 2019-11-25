FROM openjdk:8-jdk

MAINTAINER Renato T Tamaki <renato.tamaki@gmail.com>

RUN mkdir /data
RUN wget https://github.com/kelseyhightower/confd/releases/download/v0.15.0/confd-0.15.0-linux-amd64  && \
    mkdir -p /opt/confd/bin  && \
    mkdir -p /etc/confd/{conf.d,templates}  && \
    mv confd-0.15.0-linux-amd64 /opt/confd/bin/confd && \
    chmod +x /opt/confd/bin/confd
COPY target/*.jar /data/app.jar
COPY confd_spring/*.toml /etc/confd/conf.d/
COPY confd_spring/*.tmpl /etc/confd/templates/
COPY boot_spring.sh /data/boot_spring.sh
RUN chmod +x /data/boot_spring.sh

EXPOSE 8090
HEALTHCHECK --interval=10s --timeout=2s --retries=6 CMD curl --fail http://localhost:8090/management/health || exit 1

ENTRYPOINT ["/data/boot_spring.sh"]