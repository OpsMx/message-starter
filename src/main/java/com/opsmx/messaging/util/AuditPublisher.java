package com.opsmx.messaging.util;

import com.opsmx.messaging.config.MessageStarterCamelConfig;
import com.opsmx.messaging.constants.CamelConstants;
import com.opsmx.messaging.dto.AuditMessageDTO;
import org.apache.camel.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Slf4j
@Import(MessageStarterCamelConfig.class)
@Component
public class AuditPublisher {

    @Autowired
    private ProducerTemplate producerTemplate;

    private Gson gson = new Gson();

    public void sendToAudit(String resourceName, String resourceType, String action, String status, String entityName, String entityType, String message, String source, String cdTool){

        try{
            AuditMessageDTO auditMessageDTO = new AuditMessageDTO(resourceName,resourceType, action, status, entityName, entityType, message, source, cdTool);
            String serializedAuditMessage = gson.toJson(auditMessageDTO, AuditMessageDTO.class);
            log.info("Publishing audit event to endpoint : {} the message: {}", CamelConstants.camelAuditEndpoint, serializedAuditMessage);
            producerTemplate.sendBody(CamelConstants.camelAuditEndpoint, serializedAuditMessage);
        }
        catch (Exception e){
            log.error("Error publishing audit event: {}", e);
        }
    }
}
