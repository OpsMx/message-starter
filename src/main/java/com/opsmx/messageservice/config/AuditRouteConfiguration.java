package com.opsmx.messageservice.config;

import com.opsmx.messageservice.constants.CamelConstants;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("${message-broker.enabled:true}")
public class AuditRouteConfiguration extends RouteBuilder {


    @Autowired
    private MessageServiceCamelRouteConfig messageServiceCamelRouteConfig;

    @Override
    public void configure() throws Exception{

                from(CamelConstants.camelAuditEndpoint)
                .id(CamelConstants.auditQueue)
                .to(messageServiceCamelRouteConfig.configure(CamelConstants.auditExchange, CamelConstants.auditQueueName))
                .end();


    }

// no generalization of an indivdual route builder
//    public void configureConsumer(String queueName, String queueId, String exchange, MessageConsumer consumerBean) throws Exception{
//        from(camelRouteConfig.configure(exchange, queueName))
//                .id(queueId)
////                .unmarshal().json(CDRouteInfo.class)  //No need of deserialization as message will be directly passed to handler
//                .bean(consumerBean, "handleEvent")
//                .end();
//    }
//
//    public void configureProducer(String camelEndpoint, String queueName, String queueId, String exchange){
//        from(camelEndpoint)
//                .id(queueId)
//                .to(camelRouteConfig.configure(exchange, queueName))
//                .end();
//    }
}
