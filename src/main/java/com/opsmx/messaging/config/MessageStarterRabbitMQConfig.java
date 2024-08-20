package com.opsmx.messaging.config;

import com.opsmx.messaging.constants.CamelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("${message-broker.enabled:true}")
@ConditionalOnProperty(value = "message-broker.endpoint.name", havingValue = CamelConstants.rabbitmq)
public class MessageStarterRabbitMQConfig implements MessageStarterCamelRouteConfig {

    @Autowired
    private MessageStarterMessageBrokerProperties messageStarterMessageBrokerProperties;

    @Override
    public String configure(String exchange, String queueName){
        return messageStarterMessageBrokerProperties.getEndpoint().getName()+":"+exchange+"?queue="
                +queueName+"&autoDelete=false&routingKey="
                +queueName+"&declare=true&durable=true&exchangeType=direct&hostname="
                + messageStarterMessageBrokerProperties.getHost()+"&portNumber="+ messageStarterMessageBrokerProperties.getPort()
                +"&username="+ messageStarterMessageBrokerProperties.getUsername()+"&password="+ messageStarterMessageBrokerProperties.getPassword();

    }
}
