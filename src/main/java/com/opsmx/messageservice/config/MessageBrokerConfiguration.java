package com.opsmx.messageservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "message-broker")
@EnableConfigurationProperties({MessageBrokerConfiguration.class, MessageBrokerConfiguration.Endpoint.class})
public class MessageBrokerConfiguration {

    private String username;
    private String password;
    private String host;
    private String port;
    private Endpoint endpoint;
    private List<ConsumerRouteProperty> consumerRoutes;

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "message-broker.endpoint")
    public static class Endpoint {
        private String name;
    }

    @Data
    @Configuration
//    @ConfigurationProperties(prefix = "message-broker.endpoint")
    public static class ConsumerRouteProperty {
        private String queueName;
        private String queueId;
        private String handlerBean;
        private String exchange;
    }


}