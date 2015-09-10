package com.callfire.api.client;

import com.callfire.api.client.endpoint.AdminEndpoint;
import com.callfire.api.client.endpoint.AgentGroupsEndpoint;
import com.callfire.api.client.endpoint.MeEndpoint;

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

    /**
     * Constructs callfire client
     *
     * @param username api login
     * @param password api password
     */
    public CallfireClient(String username, String password) {
        restApiClient = new RestApiClient(username, password);
        adminEndpoint = new AdminEndpoint(restApiClient);
        agentGroupsEndpoint = new AgentGroupsEndpoint(restApiClient);
        meEndpoint = new MeEndpoint(restApiClient);
    }

    /**
     * Get /admin endpoint
     *
     * @return endpoint object
     */
    public AdminEndpoint getAdminEndpoint() {
        return adminEndpoint;
    }

    /**
     * Get /agent-groups endpoint
     *
     * @return endpoint object
     */
    public AgentGroupsEndpoint getAgentGroupsEndpoint() {
        return agentGroupsEndpoint;
    }

    /**
     * Get /me api endpoint
     *
     * @return endpoint object
     */
    public MeEndpoint getMeEndpoint() {
        return meEndpoint;
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
