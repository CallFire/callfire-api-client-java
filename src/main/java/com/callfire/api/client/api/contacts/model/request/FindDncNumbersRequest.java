package com.callfire.api.client.api.contacts.model.request;

import com.callfire.api.client.api.common.model.request.FindRequest;

import java.util.List;

/**
 * Request object for GET /contacts/dncs which incapsulates
 * different query pairs
 */
public class FindDncNumbersRequest extends FindRequest {
    private String prefix;
    private Long campaignId;
    private String source;
    private Boolean call;
    private Boolean text;
    private List<String> numbers;


    private FindDncNumbersRequest() {
    }

    /**
     * Get prefix (1-10 digits) of numbers
     *
     * @return prefix (1-10 digits) of numbers
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Get filter by campaignId property
     *
     * @return Campaign Id filter
     */
    public Long getCampaignId() {
        return campaignId;
    }

    /**
     * Get filter by source property
     *
     * @return Source filter
     */
    public String getSource() {
        return source;
    }

    /**
     * Get filter by call property
     *
     * @return true if filter by call set
     */
    public Boolean getCall() {
        return call;
    }

    /**
     * Get filter by text property
     *
     * @return true if filter by text set
     */
    public Boolean getText() {
        return text;
    }

    /**
     * Get filter by numbers list property
     *
     * @return Numbers filter
     */
    public List<String> getNumbers() {
        return numbers;
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
     * Builder class for find method
     */
    public static class Builder extends FindRequestBuilder<Builder, FindDncNumbersRequest> {
        private Builder() {
            super(new FindDncNumbersRequest());
        }

        /**
         * Set prefix (1-10 digits) of numbers
         *
         * @param prefix prefix (1-10 digits) of numbers
         * @return builder self-reference
         */
        public Builder prefix(String prefix) {
            request.prefix = prefix;
            return this;
        }

        /**
         * Set Campaign Id to filter lists
         *
         * @param campaignId Campaign Id to filter lists
         * @return builder self-reference
         */
        public Builder campaignId(Long campaignId) {
            request.campaignId = campaignId;
            return this;
        }

        /**
         * Set Source to filter lists
         *
         * @param source Source to filter lists
         * @return builder self-reference
         */
        public Builder source(String source) {
            request.source = source;
            return this;
        }

        /**
         * Set filter by call
         *
         * @param call filter by call
         * @return builder self-reference
         */
        public Builder call(Boolean call) {
            request.call = call;
            return this;
        }

        /**
         * Set filter by text
         *
         * @param text filter by text
         * @return builder self-reference
         */
        public Builder text(Boolean text) {
            request.text = text;
            return this;
        }

        /**
         * Set Numbers to filter lists
         *
         * @param numbers Numbers filter
         * @return builder self-reference
         */
        public Builder numbers(List<String> numbers) {
            request.numbers = numbers;
            return this;
        }
    }
}
