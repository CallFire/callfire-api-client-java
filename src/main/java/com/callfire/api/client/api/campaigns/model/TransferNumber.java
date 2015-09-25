package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TransferNumber extends CallfireModel {
    private String name;
    private Boolean allowAssistedTransfer;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAllowAssistedTransfer() {
        return allowAssistedTransfer;
    }

    public void setAllowAssistedTransfer(Boolean allowAssistedTransfer) {
        this.allowAssistedTransfer = allowAssistedTransfer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("name", name)
            .append("allowAssistedTransfer", allowAssistedTransfer)
            .append("phoneNumber", phoneNumber)
            .toString();
    }
}
