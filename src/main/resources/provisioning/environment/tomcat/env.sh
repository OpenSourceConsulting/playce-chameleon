#!/bin/bash
#************************ Warning **********************************
#*                                                                 *
#* This configuration is property of OPEN SOURCE CONSULTING, INC.  *
#* Don't distribute this to other project.                         *
#*                                                                 *
#* Contact jchoi@osci.kr(http://www.osci.kr)                       *
#*******************************************************************

# This is tomcat env.sh for iosp by Open Source Consulting, Inc

## Set Tomcat base env
export JAVA_HOME=${java.home}
export SERVER_NAME=${server.name}
# The following variable is to point where tomcat engine is installed
# Ex: export CATALINA_HOME=/opt/tomcat-6.0.25
export CATALINA_HOME=${catalina.home}
# Please set the tomcat instance name(Business level)
# Ex: export CATALINA_BASE=/BizPlatform/servers/$SERVER_NAME
export CATALINA_BASE=${catalina.base}

## Set Port Configuration
#########################################
#  Default Ports are as below      	#
#  HTTP_PORT : 8080			#
#  SSL_PORT : 8443			#
#  SHUTDOWN_PORT : 8005			#
#########################################
export HTTP_PORT=${http.port}
export AJP_PORT=${ajp.port}
export SSL_PORT=${ssl.port}
export SHUTDOWN_PORT=${shutdown.port}

export COMP_USER=${user.account}

if [ "x$JAVA_OPTS" = "x" ]; then
   JAVA_OPTS="-server"
   JAVA_OPTS="$JAVA_OPTS -Dserver=$SERVER_NAME"
   JAVA_OPTS="$JAVA_OPTS -Dhttp.port=$HTTP_PORT"
   JAVA_OPTS="$JAVA_OPTS -Dajp.port=$AJP_PORT"
   JAVA_OPTS="$JAVA_OPTS -Dssl.port=$SSL_PORT"
   JAVA_OPTS="$JAVA_OPTS -Dshutdown.port=$SHUTDOWN_PORT"

#   JAVA_OPTS="$JAVA_OPTS -noverify"
   JAVA_OPTS="$JAVA_OPTS -Xms512m"
   JAVA_OPTS="$JAVA_OPTS -Xmx512m"
   JAVA_OPTS="$JAVA_OPTS -XX:PermSize=256m"
   JAVA_OPTS="$JAVA_OPTS -XX:MaxPermSize=256m"
#   JAVA_OPTS="$JAVA_OPTS -Xss128k"

   JAVA_OPTS="$JAVA_OPTS -verbose:gc"
   JAVA_OPTS="$JAVA_OPTS -Xloggc:$CATALINA_BASE/logs/gc.log"
   JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCDetails"
   JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCTimeStamps"
   JAVA_OPTS="$JAVA_OPTS -XX:+PrintHeapAtGC"
   JAVA_OPTS="$JAVA_OPTS -XX:+HeapDumpOnOutOfMemoryError"
   JAVA_OPTS="$JAVA_OPTS -XX:HeapDumpPath=$CATALINA_BASE/logs/java_pid.hprof"

#   JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote"
#   JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.port=8186"
#   JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.ssl=false"
#   JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.authenticate=false"

   JAVA_OPTS="$JAVA_OPTS -Djava.net.preferIPv4Stack=true"
   JAVA_OPTS="$JAVA_OPTS -Dsun.rmi.dgc.client.gcInterval=3600000"
   JAVA_OPTS="$JAVA_OPTS -Dsun.rmi.dgc.server.gcInterval=3600000"
   JAVA_OPTS="$JAVA_OPTS -Dsun.lang.ClassLoader.allowArraySyntax=true "
fi

export JAVA_OPTS

echo "================================================"
echo "CATALINA_HOME=$CATALINA_HOME"
echo "SERVER_HOME=$CATALINA_BASE"
echo "================================================"


