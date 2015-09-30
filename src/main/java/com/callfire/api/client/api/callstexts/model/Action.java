package com.callfire.api.client.api.callstexts.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.contacts.model.Contact;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;

public class Action<T extends ActionRecord> extends CallfireModel {
    private Long id;
    private String fromNumber;
    private String toNumber;
    private Long campaignId;
    private Long batchId;
    private Contact contact;
    private Boolean inbound;
    private Date created;
    private Date modified;
    private State state;
    private List<String> labels = new ArrayList<>();
    private Map<String, String> attributes = new HashMap<>();
    private List<T> records = new ArrayList<>();

    public enum State {
        // non-terminal
        READY, SELECTED, CALLBACK,
        // terminal
        FINISHED, DISABLED, DNC, DUP, INVALID, TIMEOUT, PERIOD_LIMIT,
    }

    public State getState() {
        return state;
    }

    public List<T> getRecords() {
        return records;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

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

    public Long getBatchId() {
        return batchId;
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

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
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
            .append("state", state)
            .append("modified", modified)
            .append("labels", labels)
            .append("attributes", attributes)
            .append("records", records)
            .toString();
    }
}
