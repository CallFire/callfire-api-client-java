package com.callfire.api.client.api.webhooks.model.request;

import com.callfire.api.client.api.common.model.request.FindRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for GET /webhooks which incapsulates
 * different query pairs
 *
 * @since 1.0
 */
public class FindWebhooksRequest extends FindRequest {
    private String name;
    private String resource;
    private String event;
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
     * Get search field for fileName/name
     *
     * @return search field for fileName/name
     */
    public String getName() {
        return name;
    }

    public String getResource() {
        return resource;
    }

    public String getEvent() {
        return event;
    }

    public String getCallback() {
        return callback;
    }

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

        public Builder name(String name) {
            request.name = name;
            return this;
        }

        public Builder resource(String resource) {
            request.resource = resource;
            return this;
        }

        public Builder event(String event) {
            request.event = event;
            return this;
        }

        public Builder callback(String callback) {
            request.callback = callback;
            return this;
        }

        public Builder enabled(Boolean enabled) {
            request.enabled = enabled;
            return this;
        }
    }
}
