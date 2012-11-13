#!/bin/sh

DATE=`date +%Y%m%d%H%M%S`

export JBOSS_HOME=${jboss.home}
export SERVER_HOME=${server.home}
export SERVER_NAME=${server.name}
export PARTITION_NAME=${partition.name}
export MULTICAST_ADDR=228.1.4.1
export BIND_ADDR=${bind.address}
export PORT_BINDING=${bind.port}

export JAVA_OPTS="-Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true"

# JVM Options : Memory / GC
export JAVA_OPTS="-server -Xms512m -Xmx512m -XX:MaxPermSize=256m $JAVA_OPTS"
export JAVA_OPTS=" $JAVA_OPTS -verbose:gc "
export JAVA_OPTS=" $JAVA_OPTS -XX:+PrintGCTimeStamps "
export JAVA_OPTS=" $JAVA_OPTS -XX:+PrintGCDetails "
export JAVA_OPTS=" $JAVA_OPTS -Xloggc:log/gc_$DATE.log "
export JAVA_OPTS=" $JAVA_OPTS -XX:+UseParallelGC "
#export JAVA_OPTS=" $JAVA_OPTS -XX:+UseConcMarkSweepGC "
export JAVA_OPTS=" $JAVA_OPTS -XX:+ExplicitGCInvokesConcurrent "
export JAVA_OPTS=" $JAVA_OPTS -XX:-HeapDumpOnOutOfMemoryError "
export JAVA_OPTS=" $JAVA_OPTS  -XX:+UseLargePages "
#export JAVA_OPTS=" $JAVA_OPTS -Dfile.encoding=EUR-KR "

# Port Binding 
export JAVA_OPTS=" $JAVA_OPTS -Djboss.service.binding.set=$PORT_BINDING "

# JBoss Messaging 
export JAVA_OPTS=" $JAVA_OPTS -Djboss.jgroups.jbm_data.mping_mcast_addr=$MULTICAST_ADDR "

# JConsole
#export JAVA_OPTS=" $JAVA_OPTS -Djava.rmi.server.hostname=127.0.0.1 "
#export JAVA_OPTS=" $JAVA_OPTS -Dcom.sun.management.jmxremote.port=1111 "
#export JAVA_OPTS=" $JAVA_OPTS -Dcom.sun.management.jmxremote.authenticate=false "
#export JAVA_OPTS=" $JAVA_OPTS -Dcom.sun.management.jmxremote.ssl=false "
#export JAVA_OPTS=" $JAVA_OPTS -Dcom.sun.management.jmxremote"
#export JAVA_OPTS=" $JAVA_OPTS -Djboss.platform.mbeanserver"
#export JAVA_OPTS=" $JAVA_OPTS -Djavax.management.builder.initial=org.jboss.system.server.jmx.MBeanServerBuilderImpl"


echo "================================================"
echo "JBOSS_HOME=$JBOSS_HOME"
echo "SERVER_HOME=$SERVER_HOME"
echo "SERVER_NAME=$SERVER_NAME"
echo "PORT_BINDING=$PORT_BINDING"
echo "================================================"
