case "$1" in
        start)
            java -jar ./target/bus-route-challenge-1.0-SNAPSHOT-fat.jar  -conf ./src/main/conf/bus-route-challenge-conf.json&
            ;;
        stop)
            ps -ef | grep "java -jar ./target/bus-route-challenge-1.0-SNAPSHOT-fat.jar" | grep -v grep | awk '{print $2}' | xargs kill
            ;;
        *)
            echo $"Usage: $0 {start|stop}"
            exit 1

esac
