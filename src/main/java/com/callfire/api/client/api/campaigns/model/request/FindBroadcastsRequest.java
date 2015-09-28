package com.callfire.api.client.api.campaigns.model.request;

import com.callfire.api.client.api.common.model.request.FindRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for searching voice, text, ivr broadcasts
 */
public abstract class FindBroadcastsRequest extends FindRequest {
    protected String label;
    protected String name;
    protected Boolean running;

    protected FindBroadcastsRequest() {
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
    @SuppressWarnings("unchecked")
    public abstract static class FindBroadcastsBuilder<B extends FindBroadcastsBuilder, R extends FindBroadcastsRequest>
        extends FindRequestBuilder<B, R> {

        protected FindBroadcastsBuilder(R request) {
            super(request);
        }

        /**
         * Set name of voice broadcast
         *
         * @param name name of voice broadcast
         * @return builder self reference
         */
        public B name(String name) {
            request.name = name;
            return (B) this;
        }

        /**
         * Set label of voice broadcast
         *
         * @param label label of voice broadcast
         * @return builder self reference
         */
        public B label(String label) {
            request.label = label;
            return (B) this;
        }

        public B running(Boolean running) {
            request.running = running;
            return (B) this;
        }

    }
}
