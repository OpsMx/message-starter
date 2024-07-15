package com.opsmx.messageservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Data
@Configuration
@ConfigurationProperties(prefix = "message-broker")
@EnableConfigurationProperties({MessageServiceMessageBrokerProperties.class, MessageServiceMessageBrokerProperties.Endpoint.class})
@Primary
public class MessageServiceMessageBrokerProperties {

    private String username;
    private String password;
    private String host;
    private String port;
    private Endpoint endpoint;
//    private List<ConsumerRouteProperty> consumerRoutes;

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "message-broker.endpoint")
    public static class Endpoint {
        private String name;
    }
//
//    @Data
//    @Configuration
////    @ConfigurationProperties(prefix = "message-broker.endpoint")
//    public static class ConsumerRouteProperty {
//        private String queueName;
//        private String queueId;
//        private String handlerBean;
//        private String exchange;
//    }


}