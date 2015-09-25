package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.RestApiClient;

/**
 * Represents rest endpoint /campaigns
 */
public class CampaignsApi {
    private RestApiClient restApiClient;

    private BatchesApi batchesApi;
    private CccsApi cccsApi;
    private SoundsApi soundsApi;
    private TextAutoRepliesApi textAutoRepliesApi;
    private VoiceBroadcastsApi voiceBroadcastsApi;

    public CampaignsApi(RestApiClient restApiClient) {
        this.restApiClient = restApiClient;
    }

    /**
     * Get /campaigns/batches api endpoint
     *
     * @return endpoint object
     */
    public BatchesApi getBatchesApi() {
        if (batchesApi == null) {
            batchesApi = new BatchesApi(restApiClient);
        }
        return batchesApi;
    }

    /**
     * Get /campaigns/cccs api endpoint
     *
     * @return endpoint object
     */
    public CccsApi getCccsApi() {
        if (cccsApi == null) {
            cccsApi = new CccsApi(restApiClient);
        }
        return cccsApi;
    }

    /**
     * Get /campaigns/cccs/sounds api endpoint
     *
     * @return endpoint object
     * @see SoundsApi
     */
    public SoundsApi getSoundsApi() {
        if (soundsApi == null) {
            soundsApi = new SoundsApi(restApiClient);
        }
        return soundsApi;
    }

    /**
     * Get /campaigns/text-auto-replys api endpoint
     *
     * @return endpoint object
     */
    public TextAutoRepliesApi getTextAutoRepliesApi() {
        if (textAutoRepliesApi == null) {
            textAutoRepliesApi = new TextAutoRepliesApi(restApiClient);
        }
        return textAutoRepliesApi;
    }

    /**
     * Get /campaigns/voice-broadcasts api endpoint
     *
     * @return endpoint object
     */
    public VoiceBroadcastsApi getVoiceBroadcastsApi() {
        if (voiceBroadcastsApi == null) {
            voiceBroadcastsApi = new VoiceBroadcastsApi(restApiClient);
        }
        return voiceBroadcastsApi;
    }
}
