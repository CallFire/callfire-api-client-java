package com.callfire.api.client.api.campaigns.model.request;

import com.callfire.api.client.api.common.model.request.FindRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for GET /campaigns/cccs/agents which incapsulates
 * different query pairs
 */
public class FindAgentsRequest extends FindRequest {
    private Long campaignId;
    private String agentGroupName;
    private String agentEmail;
    private Boolean enabled;

    private FindAgentsRequest() {
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
     * Get id of campaign
     *
     * @return Id of campaign
     */
    public Long getCampaignId() {
        return campaignId;
    }

    /**
     * Get agent group name
     *
     * @return group name
     */
    public String getAgentGroupName() {
        return agentGroupName;
    }

    /**
     * Is agent enabled
     *
     * @return true if agent enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * Get agent email
     * Email of agent
     *
     * @return email
     */
    public String getAgentEmail() {
        return agentEmail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("campaignId", campaignId)
            .append("enabled", enabled)
            .append("agentGroupName", agentGroupName)
            .append("agentEmail", agentEmail)
            .toString();
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
