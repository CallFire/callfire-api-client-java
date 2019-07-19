package com.callfire.api.client.api.campaigns.model.request;

import static lombok.AccessLevel.PRIVATE;

import com.callfire.api.client.api.common.model.request.FindRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for GET /campaigns/cccs/agents which incapsulates
 * different query pairs
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class FindAgentsRequest extends FindRequest {

    /**
     * ID of campaign
     *
     * @return Id of campaign
     */
    private Long campaignId;

    /**
     * Agent group name
     *
     * @return group name
     */
    private String agentGroupName;

    /**
     * Email of agent
     *
     * @return email
     */
    private String agentEmail;

    /**
     * Is agent enabled
     *
     * @return true if agent enabled
     */
    private Boolean enabled;

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
    public static class Builder extends FindRequestBuilder<Builder, FindAgentsRequest> {
        private Builder() {
            super(new FindAgentsRequest());
        }

        /**
         * Set campaign id
         *
         * @param campaignId campaign id
         * @return builder self reference
         */
        public Builder campaignId(Long campaignId) {
            request.campaignId = campaignId;
            return this;
        }

        /**
         * Is agent enabled
         *
         * @param enabled set true if agent enabled
         * @return build self reference
         */
        public Builder enabled(Boolean enabled) {
            request.enabled = enabled;
            return this;
        }

        /**
         * Set agent group name
         *
         * @param agentGroupName agent group name
         * @return builder self reference
         */
        public Builder agentGroupName(String agentGroupName) {
            request.agentGroupName = agentGroupName;
            return this;
        }

        /**
         * Set agent email
         *
         * @param agentEmail agent group name
         * @return builder self reference
         */
        public Builder agentEmail(String agentEmail) {
            request.agentEmail = agentEmail;
            return this;
        }
    }
}
