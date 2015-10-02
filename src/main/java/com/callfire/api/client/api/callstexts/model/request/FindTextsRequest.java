package com.callfire.api.client.api.callstexts.model.request;

import com.callfire.api.client.api.callstexts.model.Text;
import com.callfire.api.client.api.callstexts.model.TextRecord;
import com.callfire.api.client.api.common.model.request.ConvertToString;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Request object for GET /texts which incapsulates
 * different query pairs
 */
public class FindTextsRequest extends FindCallsTextsRequest {
    @ConvertToString
    private List<Text.State> states = new ArrayList<>();
    @ConvertToString
    private List<TextRecord.TextResult> results = new ArrayList<>();

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
    public List<Text.State> getStates() {
        return states;
    }

    /**
     * Get text results
     *
     * @return list of text results
     */
    public List<TextRecord.TextResult> getResults() {
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
         * @return builder self reference
         */
        public Builder states(List<Text.State> states) {
            request.states = states;
            return this;
        }

        /**
         * Set text results
         *
         * @param results text results to set
         * @return builder self reference
         */
        public Builder results(List<TextRecord.TextResult> results) {
            request.results = results;
            return this;
        }
    }
}
