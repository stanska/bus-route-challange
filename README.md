# Bus Route Challenge

Related to https://github.com/goeuro/challenges/tree/master/bus_route_challenge

### Build

./build.sh

### Start

./service.sh start FILE

Verticle start on 8080 port, but can be changed by editing src/main/conf/bus-route-challenge-conf.json

```
  "http.port" : 8080,
```

The tests in the project use the following test file src/test/resources/testRoutes.txt

### Stop

./service.sh stop

### Documentation

There is an integrated api console for api test

http://localhost:8080/api-console/dist/index.html?raml=http://localhost:8080/assets/bus.api.raml

