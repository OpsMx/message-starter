package com.opsmx.messageservice.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
//@EnableAutoConfiguration
@ConditionalOnExpression("${message-broker.enabled:true}")
//@ComponentScan(com.opsmx.messageservice.config.AuditRouteConfiguration.class)
@Import(AuditRouteConfiguration.class)
public class MessageServiceCamelConfig {

    @Autowired
    List<RouteBuilder> routeBuilders;

//    @Autowired
//    AuditRouteConfiguration auditRouteConfiguration;

    @Bean
    public CamelContext camelContext() throws Exception {

//        AuditRouteConfiguration auditRouteConfiguration = new AuditRouteConfiguration();
        CamelContext camelContext = new DefaultCamelContext();
//        routeBuilders = new ArrayList<>();
//        routeBuilders.add(auditRouteConfiguration);
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
