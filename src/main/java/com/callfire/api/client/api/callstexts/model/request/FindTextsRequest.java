package com.callfire.api.client.api.callstexts.model.request;

import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.List;

import com.callfire.api.client.api.callstexts.model.Text;
import com.callfire.api.client.api.callstexts.model.TextRecord;
import com.callfire.api.client.api.common.model.request.ConvertToString;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for GET /texts which incapsulates
 * different query pairs
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class FindTextsRequest extends FindCallsTextsRequest {

    /**
     * Filter text statuses
     *
     * @return text statuses
     */
    @ConvertToString private List<Text.State> states = new ArrayList<>();

    /**
     * Filter text results
     *
     * @return list of text results
     */
    @ConvertToString private List<TextRecord.TextResult> results = new ArrayList<>();

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
