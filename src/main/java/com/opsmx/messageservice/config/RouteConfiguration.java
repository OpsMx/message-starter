package com.opsmx.messageservice.config;

import com.opsmx.messageservice.consumer.MessageConsumer;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("${message-broker.enabled:true}")
public class RouteConfiguration extends RouteBuilder {

//    @Autowired
//    MessageConfig messageConfig;


    @Autowired
    private CamelRouteConfig camelRouteConfig;

    @Override
    public void configure() throws Exception{

//        messageConfig.getConsumers().entrySet()
//                .stream().map((queueName, consumerBean) -> {});

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
