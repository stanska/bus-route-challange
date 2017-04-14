LOGFILE=/tmp/bus-route-challenge.log
case "$1" in
        start)
            BUS_SERVICE_PATH=$2;java -jar ./target/bus-route-challenge-1.0-SNAPSHOT-fat.jar  -conf ./src/main/conf/bus-route-challenge-conf.json >> $LOGFILE  2>&1 &
            ;;
        stop)
            ps -ef | grep "java -jar ./target/bus-route-challenge-1.0-SNAPSHOT-fat.jar" | grep -v grep | awk '{print $2}' | xargs kill
            ;;
        *)
            echo $"Usage: $0 {start FILE|stop}"
            exit 1

esac
