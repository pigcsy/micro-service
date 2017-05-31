source /etc/profile
SERVER_NAME=@build.finalName@
SERVER=./
cd $SERVER
start(){
  echo ".....starting $SERVER_NAME task......."
if [ ! -d "$SERVER/logs/" ]; then
   echo '创建日志文件目录....'
   mkdir "$SERVER/logs/"
fi

  LOG_FILE=$SERVER/logs/$SERVER_NAME'_'`date +%Y-%m-%d`.log



nohup java @jvm.arguments@ -D$SERVER  -Dfile.encoding=utf-8  -jar $SERVER_NAME.jar >$LOG_FILE 2>&1 &
}

stop(){
echo "stop $SERVER_NAME task......"

ps -ef | grep "$SERVER_NAME".jar  | awk '{print $2}'| while read pid
do

     C_PID=$(ps --no-heading $pid | wc -l)
     echo "当前PID=$pid"
     if [ "$C_PID" == "1" ]; then
          echo "PID=$pid 准备结束"
          kill -9 $pid
          echo "PID=$pid 已经结束"
      else
          echo "PID=$pid 不存在"
      fi
done

}

case "$1" in
start) start;;
stop) stop;;
*)
printf 'Usage: %s {start|stop}\n' "$prog"
exit 1
esac
