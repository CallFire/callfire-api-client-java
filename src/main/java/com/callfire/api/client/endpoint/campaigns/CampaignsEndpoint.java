package com.callfire.api.client.endpoint.campaigns;

import com.callfire.api.client.RestApiClient;

/**
 * Represents rest endpoint /campaigns
 */
public class CampaignsEndpoint {
    private RestApiClient restApiClient;
    private CccsEndpoint cccsEndpoint;
    private SoundsEndpoint soundsEndpoint;
    private TextAutoRepliesEndpoint textAutoRepliesEndpoint;

    public CampaignsEndpoint(RestApiClient restApiClient) {
        this.restApiClient = restApiClient;
    }

    /**
     * Get /campaigns/cccs api endpoint
     *
     * @return endpoint object
     */
    public CccsEndpoint getCccsEndpoint() {
        if (cccsEndpoint == null) {
            cccsEndpoint = new CccsEndpoint(restApiClient);
        }
        return cccsEndpoint;
    }

    /**
     * Get /campaigns/cccs/sounds api endpoint
     *
     * @return endpoint object
     * @see SoundsEndpoint
     */
    public SoundsEndpoint getSoundsEndpoint() {
        if (soundsEndpoint == null) {
            soundsEndpoint = new SoundsEndpoint(restApiClient);
        }
        return soundsEndpoint;
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
