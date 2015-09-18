package com.callfire.api.client;

import com.callfire.api.client.endpoint.*;
import com.callfire.api.client.endpoint.campaigns.CampaignsEndpoint;

/**
 * Callfire API v2 client
 *
 * @author Vladimir Mikhailov
 */
public class CallfireClient {
    private RestApiClient restApiClient;

    private SubscriptionsEndpoint subscriptionsEndpoint;

    private MeEndpoint meEndpoint;
    private CampaignsEndpoint campaignsEndpoint;
    private TextsEndpoint textsEndpoint;
    private KeywordsEndpoint keywordsEndpoint;
    private CallsEndpoint callsEndpoint;
    private WebhooksEndpoint webhooksEndpoint;

    /**
     * Constructs callfire client
     *
     * @param username api login
     * @param password api password
     */
    public CallfireClient(String username, String password) {
        restApiClient = new RestApiClient(username, password);
    }

    /**
     * Get /me api endpoint
     *
     * @return endpoint object
     */
    public MeEndpoint getMeEndpoint() {
        if (meEndpoint == null) {
            meEndpoint = new MeEndpoint(restApiClient);
        }
        return meEndpoint;
    }

    /**
     * Get /subscriptions api endpoint
     *
     * @return endpoint object
     */
    public SubscriptionsEndpoint getSubscriptionsEndpoint() {
        if (subscriptionsEndpoint == null) {
            subscriptionsEndpoint = new SubscriptionsEndpoint(restApiClient);
        }
        return subscriptionsEndpoint;
    }

    /**
     * Get /campaigns endpoint
     *
     * @return endpoint object
     * @see CampaignsEndpoint
     */
    public CampaignsEndpoint getCampaignsEndpoint() {
        if (campaignsEndpoint == null) {
            campaignsEndpoint = new CampaignsEndpoint(restApiClient);
        }
        return campaignsEndpoint;
    }

    /**
     * Get /texts endpoint
     *
     * @return endpoint object
     * @see TextsEndpoint
     */
    public TextsEndpoint getTextsEndpoint() {
        if (textsEndpoint == null) {
            textsEndpoint = new TextsEndpoint(restApiClient);
        }
        return textsEndpoint;
    }

    /**
     * Get /keywords endpoint
     *
     * @return endpoint object
     */
    public KeywordsEndpoint getKeywordsEndpoint() {
        if (keywordsEndpoint == null) {
            keywordsEndpoint = new KeywordsEndpoint(restApiClient);
        }
        return keywordsEndpoint;
    }

    /**
     * Get /calls endpoint
     *
     * @return endpoint object
     */
    public CallsEndpoint getCallsEndpoint() {
        if (callsEndpoint == null) {
            callsEndpoint = new CallsEndpoint(restApiClient);
        }
        return callsEndpoint;
    }

    /**
     * Get /webhooks endpoint
     *
     * @return endpoint object
     */
    public WebhooksEndpoint getWebhooksEndpoint() {
        if (webhooksEndpoint == null) {
            webhooksEndpoint = new WebhooksEndpoint(restApiClient);
        }
        return webhooksEndpoint;
    }

    /**
     * Get REST api client which uses Apache httpclient inside
     *
     * @return rest client
     */
    public RestApiClient getRestApiClient() {
        return restApiClient;
    }

}
