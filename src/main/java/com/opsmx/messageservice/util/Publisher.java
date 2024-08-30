package com.opsmx.messageservice.util;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
