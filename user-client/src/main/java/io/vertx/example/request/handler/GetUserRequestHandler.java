package io.vertx.example.request.handler;

import io.vertx.core.Handler;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.ext.web.RoutingContext;

public class GetUserRequestHandler implements Handler<RoutingContext> {
    private final static Logger LOGGER = LoggerFactory.getLogger(GetUserRequestHandler.class);

    @Override
    public void handle(RoutingContext routingContext) {
        routingContext.vertx().eventBus().send("userHandler.getUser", "Test message", res -> {
            if (res.succeeded()) {
                routingContext.response().end(res.result().body().toString());
            } else {
                LOGGER.error(res.cause());
                routingContext.response().setStatusCode(404).end();
            }
        });
    }
}
