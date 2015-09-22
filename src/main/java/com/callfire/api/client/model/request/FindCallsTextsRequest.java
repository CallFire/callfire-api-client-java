package com.callfire.api.client.model.request;

import com.callfire.api.client.api.common.model.request.GetRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

/**
 * Request object for GET /calls or /texts
 */
public abstract class FindCallsTextsRequest extends GetRequest {
    protected Long campaignId;
    protected String fromNumber;
    protected String toNumber;
    protected String label;
    protected Boolean inbound;
    //  @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    protected Date intervalBegin;
    protected Date intervalEnd;
    protected List<Long> id;

    /**
     * Get id of broadcast
     *
     * @return id of broadcast
     */
    public Long getCampaignId() {
        return campaignId;
    }

    /**
     * Get phone number text was sent from
     *
     * @return phone number text was sent from
     */
    public String getFromNumber() {
        return fromNumber;
    }

    /**
     * Get phone number text was sent to
     *
     * @return phone number text was sent to
     */
    public String getToNumber() {
        return toNumber;
    }

    /**
     * Get label assigned with text
     *
     * @return label assigned with text
     */
    public String getLabel() {
        return label;
    }

    /**
     * Is inbound text
     *
     * @return true if text inbound, otherwise false
     */
    public Boolean getInbound() {
        return inbound;
    }

    /**
     * Get beginning of time interval
     *
     * @return beginning of time interval
     */
    public Date getIntervalBegin() {
        return intervalBegin;
    }

    /**
     * Get end of time interval
     *
     * @return end of time interval
     */
    public Date getIntervalEnd() {
        return intervalEnd;
    }

    /**
     * Get particular text ids
     *
     * @return list of text ids
     */
    public List<Long> getId() {
        return id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("campaignId", campaignId)
            .append("fromNumber", fromNumber)
            .append("toNumber", toNumber)
            .append("label", label)
            .append("inbound", inbound)
            .append("intervalBegin", intervalBegin)
            .append("intervalEnd", intervalEnd)
            .append("id", id)
            .toString();
    }

    /**
     * Builder class
     */
    @SuppressWarnings("unchecked")
    public static abstract class CallsTextsBuilder<B extends CallsTextsBuilder, R extends FindCallsTextsRequest>
        extends GetRequestBuilder<B, R> {

        public CallsTextsBuilder(R request) {
            super(request);
        }

        /**
         * Set phone number text was sent from
         *
         * @param fromNumber phone number text was sent from
         */
        public B fromNumber(String fromNumber) {
            request.fromNumber = fromNumber;
            return (B) this;
        }

        /**
         * Set label assigned with text
         *
         * @param label label assigned with text
         */
        public B label(String label) {
            request.label = label;
            return (B) this;
        }

        /**
         * Set id of campaign
         *
         * @param campaignId id of campaign
         */
        public B campaignId(Long campaignId) {
            request.campaignId = campaignId;
            return (B) this;
        }

        /**
         * Set phone number text was sent to
         *
         * @param toNumber phone number text was sent to
         */
        public B toNumber(String toNumber) {
            request.toNumber = toNumber;
            return (B) this;
        }

        /**
         * Set inbound text
         *
         * @param inbound true if text inbound
         */
        public B inbound(Boolean inbound) {
            request.inbound = inbound;
            return (B) this;
        }

        /**
         * Set beginning of time interval
         *
         * @param intervalBegin beginning of time interval
         */
        public B intervalBegin(Date intervalBegin) {
            request.intervalBegin = intervalBegin;
            return (B) this;
        }

        /**
         * Set end of time interval
         *
         * @param intervalEnd end of time interval
         */
        public B intervalEnd(Date intervalEnd) {
            request.intervalEnd = intervalEnd;
            return (B) this;
        }

        /**
         * Set particular text ids to filter
         *
         * @param id text ids to filter
         */
        public B id(List<Long> id) {
            request.id = id;
            return (B) this;
        }
    }
}
