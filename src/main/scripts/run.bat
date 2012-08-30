@CLS
@ECHO OFF
:: ======================================================================
::                  Athena Chameleon Startup Script                      
:: ======================================================================

SET LOGGING_DIRECTORY=./log
SET JAVA_OPTS="-Dlogging.directory=%LOGGING_DIRECTORY%"

IF DEFINED JAVA_HOME (
    SET JAVA="%JAVA_HOME%\bin\java"
)
IF NOT DEFINED JAVA_HOME (
    SET JAVA="%cd%\jre\bin\java"
)

:: Setup the Input Parameter(FQFN)
SET FQFN=%1

:: Display our environment
echo
echo =========================================================================
echo 
echo   Athena Chameleon Bootstrap Environment
echo 
echo   JAVA: %JAVA%
echo 
echo   JAVA_OPTS: %JAVA_OPTS%
echo 
echo   Parameter: %FQFN%
echo 
echo =========================================================================
echo 

%JAVA% %JAVA_OPTS% -jar athena-chameleon.jar %FQFN%
