package com.callfire.api.client.api.account.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Request object for
 * <p/>
 * POST /admin/callerids/{callerid}/verification-code
 */
public class CallerIdVerificationRequest extends CallfireModel {
    private String verificationCode;
    @JsonIgnore
    private String callerId;

    private CallerIdVerificationRequest() {
    }

    public String getCallerId() {
        return callerId;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

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
