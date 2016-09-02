package io.vertx.example.handler;

import info.archinnov.achilles.generated.ManagerFactory;


import io.vertx.core.Handler;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.example.model.UserModel;
import io.vertx.rxjava.core.eventbus.Message;
import io.vertx.rxjava.core.Vertx;

public class GetUserHandler implements Handler<Message<String>> {
    private final static Logger LOGGER = LoggerFactory.getLogger(GetUserHandler.class);
    private Vertx vertx;
    private ManagerFactory managerFactory;

    public GetUserHandler(Vertx vertx, ManagerFactory managerFactory) {
        this.vertx = vertx;
        this.managerFactory = managerFactory;
    }

    @Override
    public void handle(Message<String> stringMessage) {
        UserModel user = managerFactory.forUserModel().crud().findById("user1").get();
        stringMessage.reply(stringMessage.body().toString() + ". User: " + user.getUserFname() + " " + user.getUserLname());
    }
}