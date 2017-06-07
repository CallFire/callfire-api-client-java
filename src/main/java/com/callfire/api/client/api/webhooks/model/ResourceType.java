package com.callfire.api.client.api.webhooks.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.*;

import static com.callfire.api.client.api.webhooks.model.ResourceType.ResourceEvent.*;

public enum ResourceType {
    MONTHLY_RENEWAL("MonthlyRenewal", FINISHED, FAILED),
    LOW_BALANCE("LowBalance", FINISHED, FAILED),
    CCC_CAMPAIGN("CccCampaign", STARTED, STOPPED, FINISHED),
    CALL_BROADCAST("CallBroadcast", STARTED, STOPPED, FINISHED),
    TEXT_BROADCAST("TextBroadcast", STARTED, STOPPED, FINISHED),
    OUTBOUND_CALL("OutboundCall", FINISHED),
    INBOUND_CALL("InboundCall", FINISHED),
    OUTBOUND_TEXT("OutboundText", FINISHED),
    INBOUND_TEXT("InboundText", FINISHED),
    CONTACT_LIST("ContactList", VALIDATION_FINISHED, VALIDATION_FAILED),
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
        STARTED("Started"),
        STOPPED("Stopped"),
        FINISHED("Finished"),
        FAILED("Failed"),
        VALIDATION_FINISHED("ValidationFinished"),
        VALIDATION_FAILED("ValidationFailed"),

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
