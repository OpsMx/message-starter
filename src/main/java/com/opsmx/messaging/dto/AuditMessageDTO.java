package com.opsmx.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class AuditMessageDTO {

    @NotBlank
    String resourceName;

    @NotBlank
    String resourceType;

    @NotBlank
    String action;

    @NotBlank
    String status;

    @NotBlank
    String entityName;

    @NotBlank
    String entityType;

    String message;
    String source;
    String cdTool;

    public AuditMessageDTO() {
    }
}
