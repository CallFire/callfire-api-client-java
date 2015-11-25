package com.callfire.api.client.api.webhooks.model.request;

import com.callfire.api.client.api.common.model.request.FindRequest;
import com.callfire.api.client.api.webhooks.model.ResourceType;
import com.callfire.api.client.api.webhooks.model.ResourceType.ResourceEvent;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for GET /webhooks which incapsulates
 * different query pairs
 *
 * @since 1.0
 */
public class FindWebhooksRequest extends FindRequest {
    private String name;
    private ResourceType resource;
    private ResourceEvent event;
    private String callback;
    private Boolean enabled;

    private FindWebhooksRequest() {
    }

    /**
     * Create request builder
     *
     * @return request build
     */
    public static Builder create() {
        return new Builder();
    }

    /**
     * Get name of webhook
     *
     * @return name of webhook
     */
    public String getName() {
        return name;
    }

    /**
     * Get name of resource
     *
     * @return name of resource
     */
    public ResourceType getResource() {
        return resource;
    }

    /**
     * Get name of event
     *
     * @return name of event
     */
    public ResourceEvent getEvent() {
        return event;
    }

    /**
     * Get callback URL
     *
     * @return callback URL
     */
    public String getCallback() {
        return callback;
    }

    /**
     * Is webhook enabled
     *
     * @return true if webhook enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("name", name)
            .append("resource", resource)
            .append("event", event)
            .append("callback", callback)
            .append("enabled", enabled)
            .toString();
    }

    /**
     * Builder class for request
     */
    public static class Builder extends FindRequestBuilder<Builder, FindWebhooksRequest> {
        private Builder() {
            super(new FindWebhooksRequest());
        }

        /**
         * Set name of webhook
         *
         * @param name name of webhook
         * @return builder self reference
         */
        public Builder name(String name) {
            request.name = name;
            return this;
        }

        /**
         * Set name of resource
         *
         * @param resource name of resource
         * @return builder self reference
         */
        public Builder resource(ResourceType resource) {
            request.resource = resource;
            return this;
        }

        /**
         * Set name of event
         *
         * @param event name of event
         * @return builder self reference
         */
        public Builder event(ResourceEvent event) {
            request.event = event;
            return this;
        }

        /**
         * Set callback URL of webhook
         *
         * @param callback callback URL of webhook
         * @return builder self reference
         */
        public Builder callback(String callback) {
            request.callback = callback;
            return this;
        }

        /**
         * Set webhook enabled
         *
         * @param enabled set true to search for enabled webhooks
         * @return builder self reference
         */
        public Builder enabled(Boolean enabled) {
            request.enabled = enabled;
            return this;
        }
    }
}
