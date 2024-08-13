package com.opsmx.messaging.config;

import com.opsmx.messaging.util.AuditPublisher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("${message-broker.enabled:true}")
public class AuditPublisherConfig {

    @Bean(name = "auditPublisher")
    public AuditPublisher getAuditPublisher(){
        return new AuditPublisher();
    }
}
