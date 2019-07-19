package com.callfire.api.client.api.account.model.request;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for
 * POST /admin/callerids/{callerid}/verification-code
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class CallerIdVerificationRequest extends CallfireModel {
    private String verificationCode;
    @JsonIgnore
    private String callerId;

    public static Builder create() {
        return new Builder();
    }

    /**
     * Builder class for request
     */
    public static class Builder extends AbstractBuilder<CallerIdVerificationRequest> {

        public Builder verificationCode(String verificationCode) {
            request.verificationCode = verificationCode;
            return this;
        }

        public Builder callerId(String callerId) {
            request.callerId = callerId;
            return this;
        }

        private Builder() {
            super(new CallerIdVerificationRequest());
        }
    }
}
