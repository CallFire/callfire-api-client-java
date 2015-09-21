package com.callfire.api.client.api.campaigns.model.request;

import com.callfire.api.client.api.common.model.request.GetRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for GET /agent-groups which incapsulates
 * different query pairs
 */
public class FindAgentGroupsRequest extends GetRequest {
    private Long campaignId;
    private String name;
    private Long agentId;
    private String agentEmail;

    private FindAgentGroupsRequest() {
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
    public String getName() {
        return name;
    }

    /**
     * Get agent id
     * Id of agent (agentId and agentEmail are mutually exclusive, please only provide one)
     *
     * @return id
     */
    public Long getAgentId() {
        return agentId;
    }

    /**
     * Get agent email
     * Email of agent (agentId and agentEmail are mutually exclusive, please only provide one)
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
            .append("name", name)
            .append("agentId", agentId)
            .append("agentEmail", agentEmail)
            .toString();
    }

    /**
     * Builder class for find method
     */
    public static class Builder extends AbstractBuilder<Builder, FindAgentGroupsRequest> {
        private Builder() {
            super(new FindAgentGroupsRequest());
        }

        public Builder setCampaignId(Long campaignId) {
            request.campaignId = campaignId;
            return this;
        }

        public Builder setName(String name) {
            request.name = name;
            return this;
        }

        public Builder setAgentId(Long agentId) {
            request.agentId = agentId;
            return this;
        }

        public Builder setAgentEmail(String agentEmail) {
            request.agentEmail = agentEmail;
            return this;
        }
    }
}
