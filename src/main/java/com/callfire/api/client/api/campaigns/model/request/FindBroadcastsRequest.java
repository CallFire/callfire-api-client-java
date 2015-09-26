package com.callfire.api.client.api.campaigns.model.request;

import com.callfire.api.client.api.common.model.request.FindRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for searching voice, text, ivr broadcasts
 */
public class FindBroadcastsRequest extends FindRequest {
    private String label;
    private String name;
    private Boolean running;

    private FindBroadcastsRequest() {
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
     * Get label of voice broadcast
     *
     * @return label of voice broadcast
     */
    public String getLabel() {
        return label;
    }

    /**
     * Get name of voice broadcast
     *
     * @return name of voice broadcast
     */
    public String getName() {
        return name;
    }

    public Boolean getRunning() {
        return running;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("label", label)
            .append("name", name)
            .append("running", running)
            .toString();
    }

    /**
     * Builder class for request
     */
    public static class Builder extends FindRequestBuilder<Builder, FindBroadcastsRequest> {
        private Builder() {
            super(new FindBroadcastsRequest());
        }

        /**
         * Set name of voice broadcast
         *
         * @param name name of voice broadcast
         * @return builder self reference
         */
        public Builder name(String name) {
            request.name = name;
            return this;
        }

        /**
         * Set label of voice broadcast
         *
         * @param label label of voice broadcast
         * @return builder self reference
         */
        public Builder label(String label) {
            request.label = label;
            return this;
        }

        public Builder running(Boolean running) {
            request.running = running;
            return this;
        }
    }
}
