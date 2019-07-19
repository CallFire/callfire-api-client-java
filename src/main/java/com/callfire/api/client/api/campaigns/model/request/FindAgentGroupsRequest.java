package com.callfire.api.client.api.campaigns.model.request;

import static lombok.AccessLevel.PRIVATE;

import com.callfire.api.client.api.common.model.request.FindRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for GET /agent-groups which incapsulates
 * different query pairs
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class FindAgentGroupsRequest extends FindRequest {

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
    private String name;

    /**
     * Agent id
     * <p>
     * Id of agent (agentId and agentEmail are mutually exclusive, please only provide one)
     *
     * @return id
     */
    private Long agentId;

    /**
     * Email of agent (agentId and agentEmail are mutually exclusive, please only provide one)
     *
     * @return email
     */
    private String agentEmail;

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
    public static class Builder extends FindRequestBuilder<Builder, FindAgentGroupsRequest> {
        private Builder() {
            super(new FindAgentGroupsRequest());
        }

        public Builder campaignId(Long campaignId) {
            request.campaignId = campaignId;
            return this;
        }

        public Builder name(String name) {
            request.name = name;
            return this;
        }

        public Builder agentId(Long agentId) {
            request.agentId = agentId;
            return this;
        }

        public Builder agentEmail(String agentEmail) {
            request.agentEmail = agentEmail;
            return this;
        }
    }
}
