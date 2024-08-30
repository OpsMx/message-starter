package com.opsmx.messageservice.config;

import com.opsmx.messageservice.constants.CamelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("${message-broker.enabled:true}")
@ConditionalOnProperty(value = "message-broker.endpoint.name", havingValue = CamelConstants.rabbitmq)
public class RabbitMQMessageConfig implements CamelRouteConfig{

    @Autowired
    private MessageBrokerConfiguration messageBrokerConfiguration;

    @Override
    public String configure(String exchange, String queueName){
        return messageBrokerConfiguration.getEndpoint().getName()+":"+exchange+"?queue="
                +queueName+"-audit"+"&autoDelete=false&routingKey="
                +queueName+"-audit"+"&declare=false&durable=true&exchangeType=direct&hostname="
                + messageBrokerConfiguration.getHost()+"&portNumber="+ messageBrokerConfiguration.getPort()
                +"&username="+ messageBrokerConfiguration.getUsername()+"&password="+ messageBrokerConfiguration.getPassword();

    }
}
