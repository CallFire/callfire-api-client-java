package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.campaigns.model.TextAutoReply;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Represents rest endpoint /campaigns/cccs
 */
public class CccsApi {
    private static final String TEXT_AUTO_REPLIES_PATH = "/campaigns/cccs";
    private static final String TEXT_AUTO_REPLIES_ITEM_PATH = "/campaigns/cccs/{}";
    private static final TypeReference<TextAutoReply> TEXT_AUTO_REPLY_TYPE = new TypeReference<TextAutoReply>() {
    };
    private static final TypeReference<Page<TextAutoReply>> PAGE_OF_TEXT_AUTO_REPLY_TYPE = new TypeReference<Page<TextAutoReply>>() {
    };

    private RestApiClient client;
    private AgentGroupsApi agentGroupsApi;

    public CccsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Get /campaigns/cccs/agent-groups endpoint
     *
     * @return endpoint object
     */
    public AgentGroupsApi getAgentGroupsApi() {
        if (agentGroupsApi == null) {
            agentGroupsApi = new AgentGroupsApi(client);
        }
        return agentGroupsApi;
    }
}
