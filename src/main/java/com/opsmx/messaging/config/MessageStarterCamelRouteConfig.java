package com.opsmx.messaging.config;

public interface MessageStarterCamelRouteConfig {

    String configure(String exchange, String queueName);
}
