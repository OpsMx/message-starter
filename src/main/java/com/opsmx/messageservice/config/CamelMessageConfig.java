package com.opsmx.messageservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@ConditionalOnExpression("${message-broker.enabled:true}")
public class CamelMessageConfig {

    @Autowired
    RouteConfiguration routeConfiguration;

    @Bean
    public CamelContext camelContext() throws Exception {

        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(routeConfiguration);
        camelContext.getShutdownStrategy().setShutdownNowOnTimeout(true);
        camelContext.getShutdownStrategy().setTimeout(5);
        camelContext.getShutdownStrategy().setTimeUnit(TimeUnit.SECONDS);
        camelContext.start();
        return camelContext;
    }

    @Bean
    public ProducerTemplate producerTemplate(CamelContext camelContext){
        return camelContext.createProducerTemplate();
    }
}
