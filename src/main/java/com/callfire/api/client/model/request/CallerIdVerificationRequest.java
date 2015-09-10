package com.callfire.api.client.model.request;

import com.callfire.api.client.model.BaseModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for
 * <p>
 * POST /admin/callerids/{callerid}/verification-code
 */
public class CallerIdVerificationRequest extends BaseModel {
    private String verificationCode;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("verificationCode", verificationCode)
            .toString();
    }
}
