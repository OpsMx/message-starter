package com.opsmx.messaging.config;

import com.opsmx.messaging.constants.CamelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnExpression("${message-broker.enabled:true}")
@ConditionalOnProperty(value = "message-broker.endpoint.name", havingValue = CamelConstants.rabbitmq)
//@Import(MessageStarterMessageBrokerProperties.class)
@ComponentScan
public class MessageStarterRabbitMQConfig implements MessageStarterCamelRouteConfig {

    @Autowired
    private MessageStarterMessageBrokerProperties messageStarterMessageBrokerProperties;

    @Override
    public String configure(String exchange, String queueName){
        return messageStarterMessageBrokerProperties.getEndpoint().getName()+":"+exchange+"?queue="
                +queueName+"-audit"+"&autoDelete=false&routingKey="
                +queueName+"-audit"+"&declare=false&durable=true&exchangeType=direct&hostname="
                + messageStarterMessageBrokerProperties.getHost()+"&portNumber="+ messageStarterMessageBrokerProperties.getPort()
                +"&username="+ messageStarterMessageBrokerProperties.getUsername()+"&password="+ messageStarterMessageBrokerProperties.getPassword();

    }
}
