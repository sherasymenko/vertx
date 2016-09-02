package io.vertx.example.example.server;

import io.vertx.example.route.UserRoutes;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.CorsHandler;

public class HttpServer extends AbstractVerticle {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);


    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.route().handler(CorsHandler.create("*")
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.PUT));
        addRoutes(router);
        vertx.createHttpServer(
                new HttpServerOptions().setPort(8080).setHost("localhost")
        ).requestHandler((request) -> router.accept(request)).listenObservable().
                subscribe(
                        server -> {
                            LOGGER.info("Server is listening");
                        },
                        failure -> {
                            LOGGER.info("Server could not start");
                        }
                );
    }

    private void addRoutes(Router router) {
        UserRoutes.GET_USER.addRoute(router);
    }
}