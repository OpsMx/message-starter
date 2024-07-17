package com.opsmx.messageservice.util;

import com.opsmx.messageservice.config.MessageServiceCamelConfig;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Import;

@Import(MessageServiceCamelConfig.class)
public class Publisher {

    @Autowired
    private static ProducerTemplate producerTemplate;

    private static final Logger log;

    static {
        log = LoggerFactory.getLogger(Publisher.class);
    }


    public static void publish(String endpoint, String message){
        log.debug("publishing to endpoint : {} the message: {}", endpoint, message);
        producerTemplate.sendBody(endpoint, message);
    }

}
