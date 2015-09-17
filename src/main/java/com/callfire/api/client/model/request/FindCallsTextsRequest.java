package com.callfire.api.client.model.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

/**
 * Request object for GET /calls or /texts
 */
public abstract class FindCallsTextsRequest extends FindRequest {
    private Long broadcastId;
    private String fromNumber;
    private String toNumber;
    private String label;
    private Boolean inbound;
    private Date intervalBegin;
    private Date intervalEnd;
    private List<Long> id;

    /**
     * Get id of broadcast
     *
     * @return id of broadcast
     */
    public Long getBroadcastId() {
        return broadcastId;
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
            .append("broadcastId", broadcastId)
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
    public static abstract class FindCallsTextsRequestBuilder<T extends FindCallsTextsRequestBuilder>
        extends FindRequestBuilder<T> {

        /**
         * Set phone number text was sent from
         *
         * @param fromNumber phone number text was sent from
         */
        public T setFromNumber(String fromNumber) {
            getRequest().fromNumber = fromNumber;
            return getChild();
        }

        /**
         * Set label assigned with text
         *
         * @param label label assigned with text
         */
        public T setLabel(String label) {
            getRequest().label = label;
            return getChild();
        }

        /**
         * Set id of broadcast
         *
         * @param broadcastId id of broadcast
         */
        public T setBroadcastId(Long broadcastId) {
            getRequest().broadcastId = broadcastId;
            return getChild();
        }

        /**
         * Set phone number text was sent to
         *
         * @param toNumber phone number text was sent to
         */
        public T setToNumber(String toNumber) {
            getRequest().toNumber = toNumber;
            return getChild();
        }

        /**
         * Set inbound text
         *
         * @param inbound true if text inbound
         */
        public T setInbound(Boolean inbound) {
            getRequest().inbound = inbound;
            return getChild();
        }

        /**
         * Set beginning of time interval
         *
         * @param intervalBegin beginning of time interval
         */
        public T setIntervalBegin(Date intervalBegin) {
            getRequest().intervalBegin = intervalBegin;
            return getChild();
        }

        /**
         * Set end of time interval
         *
         * @param intervalEnd end of time interval
         */
        public T setIntervalEnd(Date intervalEnd) {
            getRequest().intervalEnd = intervalEnd;
            return getChild();
        }

        /**
         * Set particular text ids to filter
         *
         * @param id text ids to filter
         */
        public T setId(List<Long> id) {
            getRequest().id = id;
            return getChild();
        }

        protected abstract FindCallsTextsRequest getRequest();
    }
}
