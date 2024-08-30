package com.opsmx.messageservice.config;

import com.opsmx.messageservice.constants.CamelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnExpression("${message-broker.enabled:true}")
@ConditionalOnProperty(value = "message-broker.endpoint.name", havingValue = CamelConstants.rabbitmq)
@Import(MessageServiceMessageBrokerProperties.class)
public class MessageServiceRabbitMQConfig implements MessageServiceCamelRouteConfig {

    @Autowired
    private MessageServiceMessageBrokerProperties messageServiceMessageBrokerProperties;

    @Override
    public String configure(String exchange, String queueName){
        return messageServiceMessageBrokerProperties.getEndpoint().getName()+":"+exchange+"?queue="
                +queueName+"-audit"+"&autoDelete=false&routingKey="
                +queueName+"-audit"+"&declare=false&durable=true&exchangeType=direct&hostname="
                + messageServiceMessageBrokerProperties.getHost()+"&portNumber="+ messageServiceMessageBrokerProperties.getPort()
                +"&username="+ messageServiceMessageBrokerProperties.getUsername()+"&password="+ messageServiceMessageBrokerProperties.getPassword();

    }
}
