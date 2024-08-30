package com.opsmx.messaging.constants;

public interface CamelConstants {

    String rabbitmq = "rabbitmq";
    String auditQueue = "audit-queue";
    String auditQueueName = "audit-info";
    String auditExchange = "audit.events";
    String camelAuditEndpoint = "direct:auditInfo";

}
