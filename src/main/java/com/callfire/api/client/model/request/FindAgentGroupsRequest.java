package com.callfire.api.client.model.request;

import com.callfire.api.client.model.BaseModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for GET /agent-groups which incapsulates
 * different query pairs
 */
public class FindAgentGroupsRequest extends BaseModel {
    private Long campaignId;
    private String name;
    private Long agentId;
    private String agentEmail;
    private Long limit;
    private Long offset;
    private String fields;

    public FindAgentGroupsRequest() {
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

    /**
     * Get max number of records per page to return
     *
     * @return limit number
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * Get offset to start of page
     *
     * @return offset
     */
    public Long getOffset() {
        return offset;
    }

    /**
     * Get limit fields returned. Example fields=id,items(name,agents(id))
     *
     * @return field to return
     */
    public String getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("campaignId", campaignId)
            .append("name", name)
            .append("agentId", agentId)
            .append("agentEmail", agentEmail)
            .append("limit", limit)
            .append("offset", offset)
            .append("fields", fields)
            .toString();
    }

    public static class FindAgentGroupsRequestBuilder {
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

        public FindAgentGroupsRequestBuilder setLimit(Long limit) {
            request.limit = limit;
            return this;
        }

        public FindAgentGroupsRequestBuilder setOffset(Long offset) {
            request.offset = offset;
            return this;
        }

        public FindAgentGroupsRequestBuilder setFields(String fields) {
            request.fields = fields;
            return this;
        }

        public FindAgentGroupsRequest build() {
            return request;
        }
    }
}
