#!/usr/bin/env bash

#source $(dirname $0)/../../env.sh
SERVERJAR="database-project-0.0.1-SNAPSHOT.jar"
base_dir=$(dirname $0)
cd ..


if [ "$JAVA_HOME" != "" ]; then
 JAVA="$JAVA_HOME/bin/java"
else
  JAVA=java
fi



JAVA_OPTS="-server -Xms32m -Xmx32m -Xmn24m -Xss256K   \
-XX:SurvivorRatio=4  -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection \
-XX:CMSInitiatingOccupancyFraction=60 -XX:+PrintGCDateStamps \
-XX:+PrintGCDetails -Xloggc:$base_dir/gc.log"

echo -n "Starting server ..."
    PID=$(ps -ef | grep database-project-0.0.1-SNAPSHOT.jar | grep -v grep |awk '{print $2}')
if [ -z "$PID" ]; then
   echo Application is already stopped
else
    echo kill $PID
    kill -9 $PID
fi

echo `pwd`
echo $SERVERJAR
echo $JAVA
echo $JAVA_OPTS
echo $JAVA_DEBUG_OPT

nohup $JAVA $JAVA_OPTS $JAVA_DEBUG_OPT -jar lib/$SERVERJAR > $base_dir/nohup.out  &

if [ $? -eq 0 ];then
 # echo -n $! > "$PIDFILE"
  if [ $? -eq 0 ]
  then
    sleep 1
    echo STARTED
   else
    echo FAILED TO WRITE PID
    exit 1
   fi
else
 echo SERVER DID NOT START
  exit 1
fi
