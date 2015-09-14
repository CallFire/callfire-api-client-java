package com.callfire.api.client.model.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for GET /agent-groups which incapsulates
 * different query pairs
 */
public class FindAgentGroupsRequest extends FindRequest {
    private Long campaignId;
    private String name;
    private Long agentId;
    private String agentEmail;

    private FindAgentGroupsRequest() {
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
            .append("campaignId", campaignId)
            .append("name", name)
            .append("agentId", agentId)
            .append("agentEmail", agentEmail)
            .append("baseRequest", super.toString())
            .toString();
    }

    /**
     * Builder class for find method
     */
    public static class FindAgentGroupsRequestBuilder extends FindRequestBuilder<FindAgentGroupsRequestBuilder> {
        private FindAgentGroupsRequest request;

        private FindAgentGroupsRequestBuilder() {
            request = new FindAgentGroupsRequest();
        }

        public static FindAgentGroupsRequestBuilder create() {
            return new FindAgentGroupsRequestBuilder();
        }

        public FindAgentGroupsRequestBuilder setCampaignId(Long campaignId) {
            request.campaignId = campaignId;
            return this;
        }

        public FindAgentGroupsRequestBuilder setName(String name) {
            request.name = name;
            return this;
        }

        public FindAgentGroupsRequestBuilder setAgentId(Long agentId) {
            request.agentId = agentId;
            return this;
        }

        public FindAgentGroupsRequestBuilder setAgentEmail(String agentEmail) {
            request.agentEmail = agentEmail;
            return this;
        }

        @Override
        public FindAgentGroupsRequest build() {
            return request;
        }

        @Override
        protected FindAgentGroupsRequestBuilder getChild() {
            return this;
        }

        @Override
        protected FindRequest getRequest() {
            return request;
        }
    }
}
