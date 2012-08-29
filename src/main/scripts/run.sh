#!/bin/sh
### ====================================================================== ###
##                                                                          ##
##                   Athena Chameleon Startup Script                        ##
##                                                                          ##
### ====================================================================== ###

LOGGING_DIRECTORY="./log"
JAVA_OPTS="-Xms128m -Xmx256m -Dlogging.directory=${LOGGING_DIRECTORY}"
LANG=ko_KR.UTF-8
export LANG

# Setup the JVM
if [ "x$JAVA" = "x" ]; then
    if [ "x$JAVA_HOME" != "x" ]; then
        JAVA="$JAVA_HOME/bin/java"
    else
        JAVA="java"
    fi
fi

# Setup the Input Parameter(FQFN)
FQFN=$1

# Display our environment
echo "========================================================================="
echo ""
echo "  Athena Chameleon Bootstrap Environment"
echo ""
echo "  JAVA: $JAVA"
echo ""
echo "  JAVA_OPTS: $JAVA_OPTS"
echo ""
echo "  Parameter: $FQFN"
echo ""
echo "========================================================================="
echo ""

${JAVA} ${JAVA_OPTS} -jar athena-chameleon.jar ${FQFN}
