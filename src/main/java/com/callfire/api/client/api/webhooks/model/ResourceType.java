package com.callfire.api.client.api.webhooks.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.*;

import static com.callfire.api.client.api.webhooks.model.ResourceType.ResourceEvent.FINISHED;
import static com.callfire.api.client.api.webhooks.model.ResourceType.ResourceEvent.STARTED;
import static com.callfire.api.client.api.webhooks.model.ResourceType.ResourceEvent.STOPPED;

public enum ResourceType {
    VOICE_BROADCAST("voiceCampaign", STARTED, STOPPED, FINISHED),
    TEXT_BROADCAST("textCampaign", STARTED, STOPPED, FINISHED),
    IVR_BROADCAST("ivrCampaign", STARTED, STOPPED, FINISHED),
    CCC_CAMPAIGN("cccCampaign", STARTED, STOPPED, FINISHED),
    UNKNOWN("unknown");

    private static final Map<String, ResourceType> RESOURCE_MAP = createResourceMap();
    private String resourceName;
    // supported events
    private Set<ResourceEvent> supportedEvents;

    ResourceType(String resourceName, ResourceEvent... supportedEvents) {
        this.resourceName = resourceName;
        this.supportedEvents = new HashSet<>(Arrays.asList(supportedEvents));
    }

    @JsonValue
    @Override
    public String toString() {
        return resourceName;
    }

    public Set<ResourceEvent> getSupportedEvents() {
        return supportedEvents;
    }

    @JsonCreator
    public static ResourceType of(String string) {
        ResourceType resource = RESOURCE_MAP.get(string);
        return resource != null ? resource : UNKNOWN;
    }

    private static Map<String, ResourceType> createResourceMap() {
        Map<String, ResourceType> map = new HashMap<>();
        for (ResourceType resource : values()) {
            map.put(resource.resourceName, resource);
        }
        return map;
    }

    /**
     * Event types
     */
    public enum ResourceEvent {
        STARTED("start"),
        STOPPED("stop"),
        FINISHED("finish"),

        UNKNOWN("unknown");

        private static final Map<String, ResourceEvent> EVENT_MAP = createEventMap();

        private String event;

        ResourceEvent(String event) {
            this.event = event;
        }

        @JsonValue
        @Override
        public String toString() {
            return event;
        }

        @JsonCreator
        public static ResourceEvent of(String string) {
            ResourceEvent event = EVENT_MAP.get(string);
            return event != null ? event : UNKNOWN;
        }

        private static Map<String, ResourceEvent> createEventMap() {
            Map<String, ResourceEvent> map = new HashMap<>();
            for (ResourceEvent event : values()) {
                map.put(event.event, event);
            }
            return map;
        }
    }
}
