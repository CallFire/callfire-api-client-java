package com.callfire.api.client.api.webhooks.model;

import com.callfire.api.client.ModelValidationException;
import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.webhooks.model.ResourceType.ResourceEvent;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Webhook extends CallfireModel {
    private Long id;
    private Boolean enabled;
    private String name;
    private ResourceType resource;
    private Boolean nonStrictSsl;
    private String fields;
    private String callback;
    private String secret;
    private Date createdAt;
    private Date updatedAt;
    private Set<ResourceEvent> events = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceType getResource() {
        return resource;
    }

    public void setResource(ResourceType resource) {
        this.resource = resource;
    }

    public Boolean getNonStrictSsl() {
        return nonStrictSsl;
    }

    public void setNonStrictSsl(Boolean nonStrictSsl) {
        this.nonStrictSsl = nonStrictSsl;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<ResourceEvent> getEvents() {
        return events;
    }

    public void setEvents(Set<ResourceEvent> events) {
        this.events = events;
    }

    @Override
    public void validate() {
        if (resource != null) {
            for (ResourceEvent event : events) {
                if (!resource.getSupportedEvents().contains(event)) {
                    throw new ModelValidationException("Event [" + event + "] is unsupported for " +
                        resource + " resource, supported events are: " + resource.getSupportedEvents());
                }
            }
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("enabled", enabled)
            .append("name", name)
            .append("resource", resource)
            .append("nonStrictSsl", nonStrictSsl)
            .append("fields", fields)
            .append("callback", callback)
            .append("secret", secret)
            .append("createdAt", createdAt)
            .append("updatedAt", updatedAt)
            .append("events", events)
            .toString();
    }
}
