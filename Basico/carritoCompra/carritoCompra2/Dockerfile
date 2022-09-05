FROM redeskako/tomcat-mysql
MAINTAINER carlosl.sanchez@gmail.com

ENV DEBIAN_FRONTEND noninteractive
ENV TOMCAT_MAJOR_VERSION=11
#ENV TOMCAT_VERSION=8.5.14
ENV TOMCAT_HOME=/usr/local/tomcat
ENV MYSQL_DATABASE=libreria
ENV MYSQL_ROOT_PASSWORD=uneduned
ENV MYSQL_USER=uned
ENV MYSQL_PASSWORD=uned

RUN apt-get update && \
  apt-get upgrade -y

WORKDIR /tmp

WORKDIR /

ADD bind_0.cnf /etc/mysql/conf.d/bind_0.cnf
ADD mysql-run.sh /mysql-run.sh
ADD supervisord-mysql.conf /etc/supervisor/conf.d/supervisord-mysql.conf
ADD settings.xml $TOMCAT_HOME/conf/settings.xml
ADD tomcat-users.xml $TOMCAT_HOME/conf/tomcat-users.xml
ADD context.xml $TOMCAT_HOME/webapps/manager/META-INF/context.xml
RUN chmod 755 /*.sh

COPY ./target/carritoCompra2-0.0.2.war /usr/local/tomcat/webapps/
COPY ./db/mysql-init.sql:/docker-entrypoint-initdb.d/mysql-init.sql

#VOLUME ["/var/lib/mysql"]

EXPOSE 3306 8080

ENTRYPOINT ["/run.sh"]
