package com.callfire.api.client.api.contacts.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class DoNotContact extends CallfireModel {
    private String number;
    private Boolean call;
    private Boolean text;
    private String source;
    private Long campaignId;
    private Date created;

    public String getNumber() {
        return number;
    }

    public Boolean getCall() {
        return call;
    }

    public Boolean getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public Date getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("number", number)
            .append("call", call)
            .append("text", text)
            .append("source", source)
            .append("campaignId", campaignId)
            .append("created", created)
            .toString();
    }
}
