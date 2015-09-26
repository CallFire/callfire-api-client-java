package com.callfire.api.client.api.campaigns.model.request;

import com.callfire.api.client.api.common.model.request.FindRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for GET /campaigns/cccs/agents which incapsulates
 * different query pairs
 */
public class FindAgentSessionsRequest extends FindRequest {
    private Long campaignId;
    private Long agentId;
    private String agentEmail;
    private Boolean active;

    private FindAgentSessionsRequest() {
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
     * Get agent id
     *
     * @return id of particular agent
     */
    public Long getAgetntId() {
        return agentId;
    }

    /**
     * Is agent session active
     *
     * @return true if agent session is active
     */
    public Boolean getActive() {
        return active;
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
            .append("active", active)
            .append("agentId", agentId)
            .append("agentEmail", agentEmail)
            .toString();
    }

    /**
     * Builder class for find method
     */
    public static class Builder extends FindRequestBuilder<Builder, FindAgentSessionsRequest> {
        private Builder() {
            super(new FindAgentSessionsRequest());
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
         * Is agent session active
         *
         * @param active set true if agent session active
         * @return build self reference
         */
        public Builder active(Boolean active) {
            request.active = active;
            return this;
        }

        /**
         * Set agent id
         *
         * @param agentId agent id
         * @return builder self reference
         */
        public Builder agentId(Long agentId) {
            request.agentId = agentId;
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
