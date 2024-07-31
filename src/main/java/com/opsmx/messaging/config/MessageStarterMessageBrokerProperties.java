package com.opsmx.messaging.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Data
@Configuration
@ConfigurationProperties(prefix = "message-broker")
@EnableConfigurationProperties({MessageStarterMessageBrokerProperties.class, MessageStarterMessageBrokerProperties.Endpoint.class})
@Primary
public class MessageStarterMessageBrokerProperties {

    private String username;
    private String password;
    private String host;
    private String port;
    private Endpoint endpoint;

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "message-broker.endpoint")
    public static class Endpoint {
        private String name;
    }
}