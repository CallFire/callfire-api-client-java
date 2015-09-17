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

    private AdminEndpoint adminEndpoint;
    private AgentGroupsEndpoint agentGroupsEndpoint;
    private MeEndpoint meEndpoint;
    private SubscriptionsEndpoint subscriptionsEndpoint;
    private CallSoundsEndpoint callSoundsEndpoint;

    private CampaignsEndpoint campaignsEndpoint;
    private TextsEndpoint textsEndpoint;
    private KeywordsEndpoint keywordsEndpoint;

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
     * Get /admin endpoint
     *
     * @return endpoint object
     */
    public AdminEndpoint getAdminEndpoint() {
        if (adminEndpoint == null) {
            adminEndpoint = new AdminEndpoint(restApiClient);
        }
        return adminEndpoint;
    }

    /**
     * Get /agent-groups endpoint
     *
     * @return endpoint object
     */
    public AgentGroupsEndpoint getAgentGroupsEndpoint() {
        if (agentGroupsEndpoint == null) {
            agentGroupsEndpoint = new AgentGroupsEndpoint(restApiClient);
        }
        return agentGroupsEndpoint;
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
     * Get /call-sounds api endpoint
     *
     * @return endpoint object
     * @see CallSoundsEndpoint
     */
    public CallSoundsEndpoint getCallSoundsEndpoint() {
        if (callSoundsEndpoint == null) {
            callSoundsEndpoint = new CallSoundsEndpoint(restApiClient);
        }
        return callSoundsEndpoint;
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
     * Get REST api client which uses Apache httpclient inside
     *
     * @return rest client
     */
    public RestApiClient getRestApiClient() {
        return restApiClient;
    }

}
