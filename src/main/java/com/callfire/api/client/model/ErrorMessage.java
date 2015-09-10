package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Object represents error message sent by server in case of error
 */
public class ErrorMessage extends BaseModel {
    private Integer httpStatusCode;
    private Integer internalCode;
    private String message;
    private String developerMessage;
    private String helpLink;

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public Integer getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(Integer internalCode) {
        this.internalCode = internalCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getHelpLink() {
        return helpLink;
    }

    public void setHelpLink(String helpLink) {
        this.helpLink = helpLink;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("httpStatusCode", httpStatusCode)
            .append("internalCode", internalCode)
            .append("message", message)
            .append("developerMessage", developerMessage)
            .append("helpLink", helpLink)
            .toString();
    }
}
