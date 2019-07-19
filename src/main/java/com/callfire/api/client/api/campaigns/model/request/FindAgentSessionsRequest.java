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
public class FindAgentSessionsRequest extends FindRequest {

    /**
     * ID of campaign
     *
     * @return Id of campaign
     */
    private Long campaignId;

    /**
     * Agent id
     *
     * @return id of particular agent
     */
    private Long agentId;

    /**
     * Email of agent
     *
     * @return email
     */
    private String agentEmail;

    /**
     * Is agent session active
     *
     * @return true if agent session is active
     */
    private Boolean active;

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
