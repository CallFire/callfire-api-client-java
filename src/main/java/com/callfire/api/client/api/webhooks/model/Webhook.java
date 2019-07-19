package com.callfire.api.client.api.webhooks.model;

import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import com.callfire.api.client.ModelValidationException;
import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.webhooks.model.ResourceType.ResourceEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Builder.Default private SortedSet<ResourceEvent> events = new TreeSet<>();
    private Boolean singleUse;

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
}
