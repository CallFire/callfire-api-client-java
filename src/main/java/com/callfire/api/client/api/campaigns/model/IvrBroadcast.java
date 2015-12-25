package com.callfire.api.client.api.campaigns.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @deprecated use com.callfire.api.client.api.campaigns.model.CallBroadcast instead. This class will be removed soon.
 */
@Deprecated
public class IvrBroadcast extends RetriableBroadcast {
    private Boolean inbound;
    private String dialplanXml;
    private List<Recipient> recipients = new ArrayList<>();

    public Boolean getInbound() {
        return inbound;
    }

    public void setInbound(Boolean inbound) {
        this.inbound = inbound;
    }

    public String getDialplanXml() {
        return dialplanXml;
    }

    public void setDialplanXml(String dialplanXml) {
        this.dialplanXml = dialplanXml;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("inbound", inbound)
            .append("dialplanXml", dialplanXml)
            .append("recipients", recipients)
            .toString();
    }
}
