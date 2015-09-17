package com.callfire.api.client.model.request;

import com.callfire.api.client.ConvertToString;
import com.callfire.api.client.model.Text.State;
import com.callfire.api.client.model.TextRecord.TextResult;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Request object for GET /texts which incapsulates
 * different query pairs
 */
public class FindTextsRequest extends FindRequest {
    private Long broadcastId;
    private String fromNumber;
    private String toNumber;
    private String label;
    private Boolean inbound;
    private Date intervalBegin;
    private Date intervalEnd;
    private List<Long> id;
    @ConvertToString
    private List<State> states = new ArrayList<>();
    @ConvertToString
    private List<TextResult> results = new ArrayList<>();

    private FindTextsRequest() {
    }

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
     * Get text statuses
     *
     * @return text statuses
     */
    public List<State> getStates() {
        return states;
    }

    /**
     * Get text results
     *
     * @return list of text results
     */
    public List<TextResult> getResults() {
        return results;
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
            .append("states", states)
            .append("results", results)
            .append("inbound", inbound)
            .append("intervalBegin", intervalBegin)
            .append("intervalEnd", intervalEnd)
            .append("id", id)
            .toString();
    }

    /**
     * Builder class
     */
    public static class FindTextsRequestBuilder extends FindRequestBuilder<FindTextsRequestBuilder> {
        private FindTextsRequest request;

        private FindTextsRequestBuilder() {
            request = new FindTextsRequest();
        }

        public static FindTextsRequestBuilder create() {
            return new FindTextsRequestBuilder();
        }

        /**
         * Set phone number text was sent from
         *
         * @param fromNumber phone number text was sent from
         */
        public FindTextsRequestBuilder setFromNumber(String fromNumber) {
            request.fromNumber = fromNumber;
            return this;
        }

        /**
         * Set label assigned with text
         *
         * @param label label assigned with text
         */
        public FindTextsRequestBuilder setLabel(String label) {
            request.label = label;
            return this;
        }

        /**
         * Set id of broadcast
         *
         * @param broadcastId id of broadcast
         */
        public FindTextsRequestBuilder setBroadcastId(Long broadcastId) {
            request.broadcastId = broadcastId;
            return this;
        }

        /**
         * Set text statuses to filter
         *
         * @param states list of states to filter
         */
        public FindTextsRequestBuilder setStates(List<State> states) {
            request.states = states;
            return this;
        }

        /**
         * Set phone number text was sent to
         *
         * @param toNumber phone number text was sent to
         */
        public FindTextsRequestBuilder setToNumber(String toNumber) {
            request.toNumber = toNumber;
            return this;
        }

        /**
         * Set text results
         *
         * @param results text results to set
         */
        public FindTextsRequestBuilder setResults(List<TextResult> results) {
            request.results = results;
            return this;
        }

        /**
         * Set inbound text
         *
         * @param inbound true if text inbound
         */
        public FindTextsRequestBuilder setInbound(Boolean inbound) {
            request.inbound = inbound;
            return this;
        }

        /**
         * Set beginning of time interval
         *
         * @param intervalBegin beginning of time interval
         */
        public FindTextsRequestBuilder setIntervalBegin(Date intervalBegin) {
            request.intervalBegin = intervalBegin;
            return this;
        }

        /**
         * Set end of time interval
         *
         * @param intervalEnd end of time interval
         */
        public FindTextsRequestBuilder setIntervalEnd(Date intervalEnd) {
            request.intervalEnd = intervalEnd;
            return this;
        }

        /**
         * Set particular text ids to filter
         *
         * @param id text ids to filter
         */
        public FindTextsRequestBuilder setId(List<Long> id) {
            request.id = id;
            return this;
        }

        @Override
        public FindTextsRequest build() {
            return request;
        }

        @Override
        protected FindTextsRequestBuilder getChild() {
            return this;
        }

        @Override
        protected FindTextsRequest getRequest() {
            return request;
        }
    }
}
