package com.opsmx.messageservice.config;

public interface CamelRouteConfig {

    String configure(String exchange, String queueName);
}
