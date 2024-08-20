package com.opsmx.messaging.config;


import com.opsmx.messaging.constants.CamelConstants;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnExpression("${message-broker.enabled:true}")
@ComponentScan
@Import(MessageStarterMessageBrokerProperties.class)
public class AuditRouteConfiguration extends RouteBuilder {

    @Autowired
    private MessageStarterRabbitMQConfig messageStarterRabbitMQConfig;

    @Override
    public void configure() throws Exception{

        from(CamelConstants.camelAuditEndpoint)
                .id(CamelConstants.auditQueue)
                .to(messageStarterRabbitMQConfig.configure(CamelConstants.auditExchange, CamelConstants.auditQueueName))
                .end();
    }

}