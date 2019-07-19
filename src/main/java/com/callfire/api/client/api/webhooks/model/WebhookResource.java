package com.callfire.api.client.api.webhooks.model;

import java.util.SortedSet;
import java.util.TreeSet;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.webhooks.model.ResourceType.ResourceEvent;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WebhookResource extends CallfireModel {
    private String resource;
    private SortedSet<ResourceEvent> supportedEvents = new TreeSet<>();
}
