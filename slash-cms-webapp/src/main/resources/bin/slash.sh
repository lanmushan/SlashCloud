#!/bin/bash
#这里可替换为你自己的执行程序，其他代码无需更改
APP_NAME=@project.artifactId@-@project.version@.jar
#使用说明，用来提示输入参数
usage() {
 echo "请输入启动参数 ./slash.sh [start|stop|restart|status]"
 exit 1
}
 
#检查程序是否在运行
function isRun() {
 pid=`ps -ef | grep $APP_NAME | grep -v grep | awk '{print $2}' `
 #如果不存在返回1，存在返回0
 if [ -z "${pid}" ]; then
     echo "${APP_NAME}程序未运行"
     return 0
 else
      echo "${APP_NAME}程序正在运行${pid}"
     return 1
 fi
}
 
#启动方法
function start() {
isRun
if [ $? == 0 ];
then
    read -n 100 jvm < jvm.conf
     echo "准备启动[${APP_NAME}]"
     nohup java -jar ../$APP_NAME ${jvm} > /dev/null 2>&1 &
else
     echo "退出程序"
     exit 1
fi
}
 
#停止方法
function stop() {
isRun
if [ $pid != 0 ];
then
    pid=`ps -ef | grep $APP_NAME | grep -v grep | awk '{print $2}' `
    kill -9 $pid
     echo "已经执行${APP_NAME}程序关闭${pid}"
else
    echo "退出程序"
fi
}
 
#输出运行状态
status() {
isRun
}
 
#重启
restart() {
stop
start
}
 
#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
"start")
 start
 ;;
"stop")
 stop
 ;;
"status")
 status
 ;;
"restart")
 restart
 ;;
*)
 usage
 ;;
esac