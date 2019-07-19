package com.callfire.api.client.api.campaigns.model.request;

import static lombok.AccessLevel.PROTECTED;

import com.callfire.api.client.api.common.model.request.FindByIdRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Common request object for GET /calls/broadcasts/{id}/calls or /texts/broadcasts/{id}/texts
 */
@Getter
@NoArgsConstructor(access = PROTECTED)
public abstract class FindBroadcastCallsTextsRequest extends FindByIdRequest {

    /**
     * Filter by batchId
     *
     * @return batchId
     */
    protected Long batchId;

    @SuppressWarnings("unchecked")
    public static abstract class FindBroadcastCallsTextsBuilder
            <B extends FindBroadcastCallsTextsBuilder, R extends FindBroadcastCallsTextsRequest> extends FindByIdBuilder<B, R> {

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
