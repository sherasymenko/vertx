package io.vertx.example.verticle;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.AbstractVerticle;

public class MainVerticle extends AbstractVerticle {
    private final static Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);
    @Override
    public void start() {
        DeploymentOptions options = new DeploymentOptions();
        options.setConfig(config());
        options.setInstances(1);
        vertx.deployVerticle(UserVerticle.class.getName(), options);
        vertx.deployVerticle(UserVerticle.class.getName());
    }
    @Override
    public void stop() {
        LOGGER.info("MainVerticle Stop completed");
    }

}
