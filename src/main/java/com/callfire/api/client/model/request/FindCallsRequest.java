package com.callfire.api.client.model.request;

import com.callfire.api.client.ConvertToString;
import com.callfire.api.client.model.Call;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Request object for GET /calls which incapsulates
 * different query pairs
 */
public class FindCallsRequest extends FindCallsTextsRequest {
    @ConvertToString
    private List<Call.State> states = new ArrayList<>();
    @ConvertToString
    private List<Call.FinalCallResult> results = new ArrayList<>();

    private FindCallsRequest() {
    }

    /**
     * Create request builder
     *
     * @return request build
     */
    public static Builder create() {
        return new Builder();
    }

    /**
     * Get text statuses
     *
     * @return text statuses
     */
    public List<Call.State> getStates() {
        return states;
    }

    /**
     * Get text results
     *
     * @return list of text results
     */
    public List<Call.FinalCallResult> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("states", states)
            .append("results", results)
            .toString();
    }

    /**
     * Builder class
     */
    public static class Builder extends CallsTextsBuilder<Builder, FindCallsRequest> {
        private Builder() {
            super(new FindCallsRequest());
        }

        /**
         * Set text statuses to filter
         *
         * @param states list of states to filter
         */
        public Builder setStates(List<Call.State> states) {
            request.states = states;
            return this;
        }

        /**
         * Set text results
         *
         * @param results text results to set
         */
        public Builder setResults(List<Call.FinalCallResult> results) {
            request.results = results;
            return this;
        }
    }
}
