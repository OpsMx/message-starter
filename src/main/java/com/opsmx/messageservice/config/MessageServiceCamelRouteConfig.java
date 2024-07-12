package com.opsmx.messageservice.config;

public interface MessageServiceCamelRouteConfig {

    String configure(String exchange, String queueName);
}
