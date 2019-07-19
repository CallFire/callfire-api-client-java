package com.callfire.api.client.api.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Object represents error message sent by server in case of error
 */
@Getter
@NoArgsConstructor
public class ErrorMessage extends CallfireModel {
    private Integer httpStatusCode;
    private Integer internalCode;
    private String message;
    private String developerMessage;
    private String helpLink;

    public ErrorMessage(Integer httpStatusCode, String message, String helpLink) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
        this.helpLink = helpLink;
    }
}
