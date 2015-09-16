package com.callfire.api.client.endpoint.campaigns;

import com.callfire.api.client.RestApiClient;

/**
 * Represents rest endpoint /campaigns
 */
public class CampaignsEndpoint {
    private RestApiClient restApiClient;
    private TextAutoRepliesEndpoint textAutoRepliesEndpoint;

    public CampaignsEndpoint(RestApiClient restApiClient) {
        this.restApiClient = restApiClient;
    }

    /**
     * Get /campaigns/text-auto-replys api endpoint
     *
     * @return endpoint object
     */
    public TextAutoRepliesEndpoint getTextAutoRepliesEndpoint() {
        if (textAutoRepliesEndpoint == null) {
            textAutoRepliesEndpoint = new TextAutoRepliesEndpoint(restApiClient);
        }
        return textAutoRepliesEndpoint;
    }
}
