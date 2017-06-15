package com.callfire.api.client.api.callstexts.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for POST /calls or /texts
 */
public abstract class SendCallsTextsRequest extends CallfireModel {
    protected Long campaignId;
    protected String fields;
    protected Boolean strictValidation;

    /**
     * Get id of broadcast
     *
     * @return id of broadcast
     */
    public Long getCampaignId() {
        return campaignId;
    }

    /**
     * Get strict validation flag
     *
     * @return strict validation turned on Boolean
     */
    public Boolean getStrictValidation() {
        return strictValidation;
    }

    /**
     * Get limit fields returned. Example fields=id,items(name,agents(id))
     *
     * @return field to return
     */
    public String getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("campaignId", campaignId)
            .append("strictValidation", strictValidation)
            .append("fields", fields)
            .toString();
    }

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
