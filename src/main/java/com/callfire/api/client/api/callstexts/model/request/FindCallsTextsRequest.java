package com.callfire.api.client.api.callstexts.model.request;

import static lombok.AccessLevel.PROTECTED;

import java.util.Date;
import java.util.List;

import com.callfire.api.client.api.common.model.request.FindRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for GET /calls or /texts
 */
@Getter
@NoArgsConstructor(access = PROTECTED)
public abstract class FindCallsTextsRequest extends FindRequest {

    /**
     * Filter id of broadcast
     *
     * @return id of broadcast
     */
    protected Long campaignId;

    /**
     * Filter id of contact batch
     *
     * @return id of contact batch
     */
    protected Long batchId;

    /**
     * Filter phone number call/text was sent to
     *
     * @return phone number call/text was sent to
     */
    protected String fromNumber;

    /**
     * Filter phone number call/text was sent from
     *
     * @return phone number call/text was sent from
     */
    protected String toNumber;

    /**
     * Filter label assigned with call/text
     *
     * @return label assigned with call/text
     */
    protected String label;

    /**
     * Filter inbound call/text
     *
     * @return true if call/text inbound, otherwise false
     */
    protected Boolean inbound;

    /**
     * Filter beginning of time interval
     *
     * @return beginning of time interval
     */
    protected Date intervalBegin;

    /**
     * Filter end of time interval
     *
     * @return end of time interval
     */
    protected Date intervalEnd;

    /**
     * Filter particular text ids
     *
     * @return list of text ids
     */
    protected List<Long> id;

    /**
     * Builder class
     */
    @SuppressWarnings("unchecked")
    public static abstract class CallsTextsBuilder<B extends CallsTextsBuilder, R extends FindCallsTextsRequest>
            extends FindRequestBuilder<B, R> {

        public CallsTextsBuilder(R request) {
            super(request);
        }

        /**
         * Set E.164 number that calls/texts are from
         *
         * @param fromNumber phone number text was sent from
         * @return builder self reference
         */
        public B fromNumber(String fromNumber) {
            request.fromNumber = fromNumber;
            return (B) this;
        }

        /**
         * Set label assigned with call/text
         *
         * @param label label assigned with call/text
         * @return builder self reference
         */
        public B label(String label) {
            request.label = label;
            return (B) this;
        }

        /**
         * Query for calls/texts inside of a particular campaign.
         *
         * @param campaignId id of campaign
         * @return builder self reference
         */
        public B campaignId(Long campaignId) {
            request.campaignId = campaignId;
            return (B) this;
        }

        /**
         * Query for calls/texts which were created with particular contact batch.
         *
         * @param batchId id of contact batch
         * @return builder self reference
         */
        public B batchId(Long batchId) {
            request.batchId = batchId;
            return (B) this;
        }

        /**
         * Set E.164 number that calls/texts are to
         *
         * @param toNumber phone number text was sent to
         * @return builder self reference
         */
        public B toNumber(String toNumber) {
            request.toNumber = toNumber;
            return (B) this;
        }

        /**
         * Set inbound call/text
         *
         * @param inbound true if call/text inbound
         * @return builder self reference
         */
        public B inbound(Boolean inbound) {
            request.inbound = inbound;
            return (B) this;
        }

        /**
         * Set beginning of time interval
         *
         * @param intervalBegin beginning of time interval
         * @return builder self reference
         */
        public B intervalBegin(Date intervalBegin) {
            request.intervalBegin = intervalBegin;
            return (B) this;
        }

        /**
         * Set end of time interval
         *
         * @param intervalEnd end of time interval
         * @return builder self reference
         */
        public B intervalEnd(Date intervalEnd) {
            request.intervalEnd = intervalEnd;
            return (B) this;
        }

        /**
         * Set particular call/text ids to filter
         *
         * @param id text ids to filter
         * @return builder self reference
         */
        public B id(List<Long> id) {
            request.id = id;
            return (B) this;
        }
    }
}
