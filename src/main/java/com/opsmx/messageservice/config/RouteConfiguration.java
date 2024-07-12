package com.opsmx.messageservice.config;

import com.opsmx.messageservice.consumer.MessageConsumer;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("${message-broker.enabled:true}")
public class RouteConfiguration extends RouteBuilder {


    @Autowired
    private CamelRouteConfig camelRouteConfig;

    @Autowired
    MessageBrokerConfiguration messageBrokerConfiguration;

    @Autowired
    ApplicationContext context;

    @Override
    public void configure() throws Exception{

        messageBrokerConfiguration.getConsumerRoutes()
                .forEach(e ->
                    from(camelRouteConfig.configure(e.getExchange(), e.getQueueName()))
                            .id(e.getQueueId())
                            .bean(context.getBean(e.getHandlerBean()), "handleEvent")
                            .end()
                );

    }

    public void configureConsumer(String queueName, String queueId, String exchange, MessageConsumer consumerBean) throws Exception{
        from(camelRouteConfig.configure(exchange, queueName))
                .id(queueId)
//                .unmarshal().json(CDRouteInfo.class)  //No need of deserialization as message will be directly passed to handler
                .bean(consumerBean, "handleEvent")
                .end();
    }

    public void configureProducer(String camelEndpoint, String queueName, String queueId, String exchange){
        from(camelEndpoint)
                .id(queueId)
                .to(camelRouteConfig.configure(exchange, queueName))
                .end();
    }
}
