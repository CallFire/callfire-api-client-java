package com.callfire.api.client.api.callstexts.model.request;

import static lombok.AccessLevel.PROTECTED;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for POST /calls or /texts
 */
@Getter
@NoArgsConstructor(access = PROTECTED)
public abstract class SendCallsTextsRequest extends CallfireModel {

    /**
     * Id of broadcast
     *
     * @return id of broadcast
     */
    protected Long campaignId;

    /**
     * Strict validation flag
     *
     * @return strict validation turned on Boolean
     */
    protected String fields;

    /**
     * Get limit fields returned. Example fields=id,items(name,agents(id))
     *
     * @return field to return
     */
    protected Boolean strictValidation;

    /**
     * Builder class
     */
    @SuppressWarnings("unchecked")
    public static abstract class SendCallsTextsBuilder<B extends SendCallsTextsBuilder, R extends SendCallsTextsRequest>
            extends AbstractBuilder<R> {

        public SendCallsTextsBuilder(R request) {
            super(request);
        }

        /**
         * Sending calls/texts inside of a particular campaign.
         *
         * @param campaignId id of campaign
         * @return builder self reference
         */
        public B campaignId(Long campaignId) {
            request.campaignId = campaignId;
            return (B) this;
        }

        /**
         * Setting strict validation flag for sending calls/texts
         *
         * @param strictValidation strict validation flag
         * @return builder self reference
         */
        public B strictValidation(Boolean strictValidation) {
            request.strictValidation = strictValidation;
            return (B) this;
        }

        /**
         * Set limit fields returned. Example fields=id,items(name,agents(id))
         *
         * @param fields fields to return
         * @return builder object
         */
        public B fields(String fields) {
            request.fields = fields;
            return (B) this;
        }
    }
}
