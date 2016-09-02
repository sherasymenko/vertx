package io.vertx.example.verticle;

import com.datastax.driver.core.Cluster;
import info.archinnov.achilles.generated.ManagerFactory;
import info.archinnov.achilles.generated.ManagerFactoryBuilder;
import io.vertx.example.handler.GetUserHandler;
import io.vertx.rxjava.core.AbstractVerticle;

public class UserVerticle extends AbstractVerticle {
    @Override
    public void start() {
        ManagerFactory managerFactory = getManagerFactory();
        vertx.eventBus().consumer("userHandler.getUser", new GetUserHandler(vertx,  managerFactory));
    }

    private ManagerFactory getManagerFactory() {
        Cluster cluster = Cluster.builder().addContactPoint("localhost").withPort(9042).build();
        ManagerFactory managerFactory = ManagerFactoryBuilder.builder(cluster).withDefaultKeyspaceName("vertx_project").build();
        return managerFactory;
    }
}
