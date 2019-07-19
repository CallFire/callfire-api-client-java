package com.callfire.api.client.api.campaigns.model.request;

import static lombok.AccessLevel.PROTECTED;

import com.callfire.api.client.api.common.model.request.FindRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for searching voice, text, ivr broadcasts
 */
@Getter
@NoArgsConstructor(access = PROTECTED)
public abstract class FindBroadcastsRequest extends FindRequest {

    /**
     * Label of voice broadcast
     *
     * @return label of voice broadcast
     */
    protected String label;

    /**
     * Name of voice broadcast
     *
     * @return name of voice broadcast
     */
    protected String name;

    /**
     * State of the broadcast
     *
     * @return if broadcast is in Running state
     */
    protected Boolean running;

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
