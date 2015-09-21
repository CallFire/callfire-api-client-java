package com.callfire.api.client.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;
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
    public static class Builder {
        private CallerIdVerificationRequest request;

        public Builder setVerificationCode(String verificationCode) {
            request.verificationCode = verificationCode;
            return this;
        }

        public Builder setCallerId(String callerId) {
            request.callerId = callerId;
            return this;
        }

        private Builder() {
            request = new CallerIdVerificationRequest();
        }

        public CallerIdVerificationRequest build() {
            return request;
        }
    }
}
