package io.vertx.example.route;

import io.vertx.example.request.handler.GetUserRequestHandler;
import io.vertx.rxjava.ext.web.Router;

public enum UserRoutes {
    GET_USER("/vertx/users/:username") {
        @Override
        public void addRoute(Router router) {
            router.get(getPath()).handler(new GetUserRequestHandler());
        }
    };
    private String path;

    private UserRoutes(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public abstract void addRoute(Router router);
}