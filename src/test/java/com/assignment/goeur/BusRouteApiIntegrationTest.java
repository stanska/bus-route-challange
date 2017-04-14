package com.assignment.goeur;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.TestSuite;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.*;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class BusRouteApiIntegrationTest {

    public static final int HTTP_PORT = 8083;
    private static Vertx vertx;

    @BeforeClass
    public static void setUp(TestContext context) {
        vertx = Vertx.vertx();
        DeploymentOptions options = new DeploymentOptions()
                .setConfig(new JsonObject().put("http.port", HTTP_PORT).put("file.path", "./src/test/resources/testRoutes.txt")
                );
        vertx.deployVerticle(BusRouteApi.class.getName(), options,
                context.asyncAssertSuccess());
    }

    @AfterClass
    public static void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void badRequestForStringDepartureOrArrivalProduce(TestContext context) {
        final Async async = context.async();

        vertx.createHttpClient().getNow(HTTP_PORT, "localhost", "/api/direct?arr_sid=a&dep_sid=1",
                response -> {
                    response.handler(body -> {
                        context.assertEquals(body.toString(), "{\"message\":\"arr_sid and dep_sid can contain only letters\",\"status\":400}");
                        async.complete();
                    });
                });

        vertx.createHttpClient().getNow(HTTP_PORT, "localhost", "/api/direct?arr_sid=1&dep_sid=d",
                response -> {
                    response.handler(body -> {
                        context.assertEquals(body.toString(), "{\"message\":\"arr_sid and dep_sid can contain only letters\",\"status\":400}");
                        async.complete();
                    });
                });
    }


    @Test
    public void badRequestForNotProvidedDepartureOrArrivalProduce(TestContext context) {
        final Async async = context.async();

        vertx.createHttpClient().getNow(HTTP_PORT, "localhost", "/api/direct?&dep_sid=1",
                response -> {
                    response.handler(body -> {
                        context.assertEquals(body.toString(), "{\"message\":\"Please, provide arr_sid and dep_sid parameters\",\"status\":400}");
                        async.complete();
                    });
                });

        vertx.createHttpClient().getNow(HTTP_PORT, "localhost", "/api/direct?arr_sid=1",
                response -> {
                    response.handler(body -> {
                        context.assertEquals(body.toString(), "{\"message\":\"Please, provide arr_sid and dep_sid parameters\",\"status\":400}");
                        async.complete();
                    });
                });
    }


    @Test
    public void givenArrival3andDeparture6WhenGetDirectThenTrue(TestContext context) {
        final Async async = context.async();

        vertx.createHttpClient().getNow(HTTP_PORT, "localhost", "/api/direct?arr_sid=3&dep_sid=6",
                response -> {
                    response.handler(body -> {
                        context.assertEquals(body.toString(),"{\n" +
                                "  \"arr_sid\" : 3,\n" +
                                "  \"dep_sid\" : 6,\n" +
                                "  \"direct_bus_route\" : true\n" +
                                "}");
                        async.complete();
                    });
                });

    }
    @Test
    public void givenArrival4andDeparture5WhenGetDirectThenFalse(TestContext context) {
        final Async async = context.async();

        vertx.createHttpClient().getNow(HTTP_PORT, "localhost", "/api/direct?arr_sid=4&dep_sid=5",
                response -> {
                    response.handler(body -> {
                        context.assertEquals(body.toString(), "{\n" +
                                "  \"arr_sid\" : 4,\n" +
                                "  \"dep_sid\" : 5,\n" +
                                "  \"direct_bus_route\" : false\n" +
                                "}");
                        async.complete();
                    });
                });

    }


    @Test
    public void givenNonExistingArrivalorDepartureWhenGetDirectThenFalse(TestContext context) {
        final Async async = context.async();

        vertx.createHttpClient().getNow(HTTP_PORT, "localhost", "/api/direct?arr_sid=400&dep_sid=5",
                response -> {
                    response.handler(body -> {
                        context.assertEquals(body.toString(), "{\n" +
                                "  \"arr_sid\" : 400,\n" +
                                "  \"dep_sid\" : 5,\n" +
                                "  \"direct_bus_route\" : false\n" +
                                "}");
                        async.complete();
                    });
                });
        vertx.createHttpClient().getNow(HTTP_PORT, "localhost", "/api/direct?arr_sid=4&dep_sid=500",
                response -> {
                    response.handler(body -> {
                        context.assertEquals(body.toString(), "{\n" +
                                "  \"arr_sid\" : 4,\n" +
                                "  \"dep_sid\" : 500,\n" +
                                "  \"direct_bus_route\" : false\n" +
                                "}");
                        async.complete();
                    });
                });

    }
}