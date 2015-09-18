package com.callfire.api.client.endpoint.campaigns;

import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.model.Page;
import com.callfire.api.client.model.campaigns.TextAutoReply;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Represents rest endpoint /campaigns/text-auto-replys
 */
public class CccsEndpoint {
    private static final String TEXT_AUTO_REPLIES_PATH = "/campaigns/cccs";
    private static final String TEXT_AUTO_REPLIES_ITEM_PATH = "/campaigns/cccs/{}";
    private static final TypeReference<TextAutoReply> TEXT_AUTO_REPLY_TYPE = new TypeReference<TextAutoReply>() {
    };
    private static final TypeReference<Page<TextAutoReply>> PAGE_OF_TEXT_AUTO_REPLY_TYPE = new TypeReference<Page<TextAutoReply>>() {
    };

    private RestApiClient client;
    private AgentGroupsEndpoint agentGroupsEndpoint;

    public CccsEndpoint(RestApiClient client) {
        this.client = client;
    }

    /**
     * Get /campaigns/cccs/agent-groups endpoint
     *
     * @return endpoint object
     */
    public AgentGroupsEndpoint getAgentGroupsEndpoint() {
        if (agentGroupsEndpoint == null) {
            agentGroupsEndpoint = new AgentGroupsEndpoint(client);
        }
        return agentGroupsEndpoint;
    }
}
