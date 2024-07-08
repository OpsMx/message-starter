package com.opsmx.messageservice.config;

import com.opsmx.messageservice.constants.CamelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("${message-broker.enabled:true}")
@ConditionalOnProperty(value = "message-broker.endpoint.name", havingValue = CamelConstants.rabbitmq)
public class RabbitMQConfig implements CamelRouteConfig{

    @Autowired
    private MessageBrokerProperties messageBrokerProperties;

    @Override
    public String configure(String exchange, String queueName){
        return messageBrokerProperties.getEndpoint().getName()+":"+exchange+"?queue="
                +queueName+"-audit"+"&autoDelete=false&routingKey="
                +queueName+"-audit"+"&declare=false&durable=true&exchangeType=direct&hostname="
                +messageBrokerProperties.getHost()+"&portNumber="+messageBrokerProperties.getPort()
                +"&username="+messageBrokerProperties.getUsername()+"&password="+messageBrokerProperties.getPassword();

    }
}
