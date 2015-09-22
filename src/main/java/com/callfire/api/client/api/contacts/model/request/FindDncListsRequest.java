package com.callfire.api.client.api.contacts.model.request;

import com.callfire.api.client.api.common.model.request.GetRequest;

/**
 * Request object for GET /contacts/do-not-calls/lists which incapsulates
 * different query pairs
 */
public class FindDncListsRequest extends GetRequest {
    private String name;
    private Long campaignId;

    private FindDncListsRequest() {
    }

    /**
     * Get name or partial name of contact list
     *
     * @return name or partial name of contact list
     */
    public String getName() {
        return name;
    }

    public Long getCampaignId() {
        return campaignId;
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
    public static class Builder extends GetRequestBuilder<Builder, FindDncListsRequest> {
        private Builder() {
            super(new FindDncListsRequest());
        }

        /**
         * Set name or partial name of contact list
         *
         * @param name name or partial name of contact list
         * @return builder self-reference
         */
        public Builder name(String name) {
            request.name = name;
            return this;
        }

        public Builder campaignId(Long campaignId) {
            request.campaignId = campaignId;
            return this;
        }
    }
}
