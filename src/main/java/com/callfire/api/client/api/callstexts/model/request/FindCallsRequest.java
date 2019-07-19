package com.callfire.api.client.api.callstexts.model.request;

import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.List;

import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.common.model.request.ConvertToString;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Request object used to find all calls sent or received by the user.
 * Use "campaignId=0" parameter to query for all calls sent through the POST /calls API.
 * </p>
 *
 * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class FindCallsRequest extends FindCallsTextsRequest {

    /**
     * Call statuses
     *
     * @return call statuses
     * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
     */
    @ConvertToString private List<Call.State> states = new ArrayList<>();

    /**
     * Get call results.
     *
     * @return list of call results
     * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
     */
    @ConvertToString private List<Call.CallResult> results = new ArrayList<>();

    /**
     * Create request builder
     *
     * @return request build
     */
    public static Builder create() {
        return new Builder();
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
