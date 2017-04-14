package com.assignment.goeur;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;

import java.io.IOException;

public class BusRouteApi extends AbstractVerticle {
    BusRoutesForStations busRoutesForStations;
    @Override
    public void start(Future<Void> future) throws IOException {
        String filePath = config().getString("file.path");
        String envFilePath = System.getenv("BUS_SERVICE_PATH");
        if ( envFilePath != null ) {
            filePath = envFilePath;
        }
        FileProcessor fileProcessor = new FileProcessor(filePath);
        busRoutesForStations = fileProcessor.load();

        Router router = Router.router(vertx);

        configureStaticContent(router);

        configureAppContent(future, router);
    }

    private void configureStaticContent(Router router) {
        router.route("/assets/*").handler(StaticHandler.create("assets"));
        router.route("/api-console/*").handler(StaticHandler.create("api-console"));
    }

    private void configureAppContent(Future<Void> future, Router router) {
        router.get("/api/direct").handler(this::hasDirectRoute);
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                future.complete();
                            } else {
                                future.fail(result.cause());
                            }
                        }
                );
    }

    private void hasDirectRoute(RoutingContext routingContext) {
        String arrivalStationId = routingContext.request().getParam("arr_sid");
        String departureStationId = routingContext.request().getParam("dep_sid");
        int badRequestStatusCode = HttpResponseStatus.BAD_REQUEST.code();
        if (arrivalStationId == null || departureStationId == null) {
            routingContext.response().
                    setStatusCode(badRequestStatusCode).
                    end(Json.encode(new ErrorResponse("Please, provide arr_sid and dep_sid parameters", badRequestStatusCode)));
        } else {
            try {
                Integer arrivalStationIdInteger = Integer.valueOf(arrivalStationId);
                Integer departureStationIdInteger = Integer.valueOf(departureStationId);
                routingContext.response()
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(new DirectRouteResponse(arrivalStationIdInteger,
                                                                     departureStationIdInteger,
                            busRoutesForStations.direct(arrivalStationIdInteger, departureStationIdInteger))));
            } catch (NumberFormatException e) {
                routingContext.response().
                        setStatusCode(badRequestStatusCode).
                        end(Json.encode(new ErrorResponse("arr_sid and dep_sid can contain only letters", badRequestStatusCode)));
            }
        }
    }
}