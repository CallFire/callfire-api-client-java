package com.callfire.api.client.api.callstexts.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.contacts.model.Contact;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class Action extends CallfireModel {
    private Long id;
    private String fromNumber;
    private String toNumber;
    private Long campaignId;
    private Long batchId;
    private Contact contact;
    private Boolean inbound;
    private Long created;
    private Long modified;
    private List<String> labels = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public String getToNumber() {
        return toNumber;
    }

    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Boolean getInbound() {
        return inbound;
    }

    public void setInbound(Boolean inbound) {
        this.inbound = inbound;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getModified() {
        return modified;
    }

    public void setModified(Long modified) {
        this.modified = modified;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("fromNumber", fromNumber)
            .append("toNumber", toNumber)
            .append("campaignId", campaignId)
            .append("batchId", batchId)
            .append("contact", contact)
            .append("inbound", inbound)
            .append("created", created)
            .append("modified", modified)
            .append("labels", labels)
            .toString();
    }
}
