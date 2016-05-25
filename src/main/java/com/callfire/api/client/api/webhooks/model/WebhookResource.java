package com.callfire.api.client.api.webhooks.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.webhooks.model.ResourceType.ResourceEvent;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.SortedSet;
import java.util.TreeSet;

public class WebhookResource extends CallfireModel {
    private String resource;
    private SortedSet<ResourceEvent> supportedEvents = new TreeSet<>();

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public SortedSet<ResourceEvent> getSupportedEvents() {
        return supportedEvents;
    }

    public void setSupportedEvents(SortedSet<ResourceEvent> supportedEvents) {
        this.supportedEvents = supportedEvents;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("resource", resource)
            .append("supportedEvents", supportedEvents)
            .toString();
    }
}
