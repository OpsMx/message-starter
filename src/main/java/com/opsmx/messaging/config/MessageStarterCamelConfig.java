package com.opsmx.messaging.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@ConditionalOnExpression("${message-broker.enabled:true}")
//@Import(AuditRouteConfiguration.class)
@ComponentScan
public class MessageStarterCamelConfig {

    @Autowired
    List<RouteBuilder> routeBuilders;

    @Bean
    public CamelContext camelContext() throws Exception {

        CamelContext camelContext = new DefaultCamelContext();
        routeBuilders.stream().forEach(route -> {
            log.info("Using route configuration : {},{}",route.getClass().getName(), route.getClass().getCanonicalName());
            try {
                camelContext.addRoutes(route);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        camelContext.getShutdownStrategy().setShutdownNowOnTimeout(true);
        camelContext.getShutdownStrategy().setTimeout(5);
        camelContext.getShutdownStrategy().setTimeUnit(TimeUnit.SECONDS);
        camelContext.start();

        return camelContext;
    }

    @Bean(name ="producerTemplate")
    public ProducerTemplate producerTemplate(CamelContext camelContext){
        return camelContext.createProducerTemplate();
    }
}
