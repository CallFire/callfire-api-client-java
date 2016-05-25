package com.callfire.api.client.api.campaigns.model.request;

import com.callfire.api.client.api.common.model.request.FindByIdRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Common request object for GET /calls/broadcasts/{id}/calls or /texts/broadcasts/{id}/texts
 */
public abstract class FindBroadcastCallsTextsRequest extends FindByIdRequest {
    protected Long batchId;

    /**
     * Filter by batchId
     *
     * @return batchId
     */
    public Long getBatchId() {
        return batchId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("batchId", batchId)
            .toString();
    }

    @SuppressWarnings("unchecked")
    public static abstract class FindBroadcastCallsTextsBuilder
        <B extends FindBroadcastCallsTextsBuilder, R extends FindBroadcastCallsTextsRequest>
        extends FindByIdBuilder<B, R> {

        public FindBroadcastCallsTextsBuilder(R request) {
            super(request);
        }

        /**
         * Filter by batchId
         *
         * @param batchId id of contact batch uploaded to broadcast
         * @return builder self reference
         */
        public B batchId(Long batchId) {
            request.batchId = batchId;
            return (B) this;
        }
    }
}
