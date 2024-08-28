package com.opsmx.messaging.config;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("${message-broker.enabled:true}")
public class TestRouteConfiguration extends RouteBuilder {
    @Override
    public void configure() throws Exception{
        from("direct:test").log("Test Route Configuration");
    }

}