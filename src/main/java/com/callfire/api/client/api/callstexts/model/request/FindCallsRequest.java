package com.callfire.api.client.api.callstexts.model.request;

import com.callfire.api.client.api.common.model.request.ConvertToString;
import com.callfire.api.client.api.callstexts.model.Call;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Request object used to find all calls sent or received by the user.
 * Use "campaignId=0" parameter to query for all calls sent through the POST /calls API.
 * </p>
 *
 * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
 */
public class FindCallsRequest extends FindCallsTextsRequest {
    @ConvertToString
    private List<Call.State> states = new ArrayList<>();
    @ConvertToString
    private List<Call.CallResult> results = new ArrayList<>();

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
     * Get call statuses
     *
     * @return call statuses
     * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
     */
    public List<Call.State> getStates() {
        return states;
    }

    /**
     * Get call results.
     *
     * @return list of call results
     * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
     */
    public List<Call.CallResult> getResults() {
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
         * Set call statuses to filter
         *
         * @param states list of states to filter
         * @return builder self reference
         * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
         */
        public Builder states(List<Call.State> states) {
            request.states = states;
            return this;
        }

        /**
         * Set call results
         *
         * @param results call results to set
         * @return builder self reference
         * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
         */
        public Builder results(List<Call.CallResult> results) {
            request.results = results;
            return this;
        }
    }
}
