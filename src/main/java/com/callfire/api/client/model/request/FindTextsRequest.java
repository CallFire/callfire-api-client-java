package com.callfire.api.client.model.request;

import com.callfire.api.client.api.common.model.request.ConvertToString;
import com.callfire.api.client.model.Text.State;
import com.callfire.api.client.model.TextRecord.TextResult;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Request object for GET /texts which incapsulates
 * different query pairs
 */
public class FindTextsRequest extends FindCallsTextsRequest {
    @ConvertToString
    private List<State> states = new ArrayList<>();
    @ConvertToString
    private List<TextResult> results = new ArrayList<>();

    private FindTextsRequest() {
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
    public static class Builder extends CallsTextsBuilder<Builder, FindTextsRequest> {
        private Builder() {
            super(new FindTextsRequest());
        }

        /**
         * Set text statuses to filter
         *
         * @param states list of states to filter
         */
        public Builder setStates(List<State> states) {
            request.states = states;
            return this;
        }

        /**
         * Set text results
         *
         * @param results text results to set
         */
        public Builder setResults(List<TextResult> results) {
            request.results = results;
            return this;
        }
    }
}
