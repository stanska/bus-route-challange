# Bus Route Challenge

Related to https://github.com/goeuro/challenges/tree/master/bus_route_challenge

### Build

./build.sh

### Start

./service.sh start FILE

Verticle start on 8088 port, but can be changed by editing src/main/conf/bus-route-challenge-conf.json

```
  "http.port" : 8088,
```
### Log file
/tmp/bus-route-challenge.log

### Stop

./service.sh stop

### Documentation

There is an integrated api console for api test and contract validation and documentation

http://localhost:8088/api-console/dist/index.html?raml=http://localhost:8088/assets/bus.api.raml

