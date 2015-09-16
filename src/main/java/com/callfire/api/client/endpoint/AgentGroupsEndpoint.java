package com.callfire.api.client.endpoint;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.model.AgentGroup;
import com.callfire.api.client.model.Page;
import com.callfire.api.client.model.ResourceId;
import com.callfire.api.client.model.request.FindAgentGroupsRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.callfire.api.client.ApiEndpoints.AGENT_GROUPS_ITEM_PATH;
import static com.callfire.api.client.ApiEndpoints.AGENT_GROUPS_PATH;
import static com.callfire.api.client.ApiEndpoints.Type.RESOURCE_ID_TYPE;
import static com.callfire.api.client.ApiEndpoints.Type.STRING_TYPE;
import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /agent-groups
 */
public class AgentGroupsEndpoint {

    private RestApiClient client;

    public AgentGroupsEndpoint(RestApiClient client) {
        this.client = client;
    }

    /**
     * Query for agent groups using optional filters
     * (Note: agentId and agentEmail are mutually exclusive, please only provide one)
     * <p>
     * GET /agent-groups
     *
     * @return Page object with matched entities
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<AgentGroup> find(FindAgentGroupsRequest request)
        throws CallfireApiException, CallfireClientException {
        return client.get(AGENT_GROUPS_PATH, new TypeReference<Page<AgentGroup>>() {
        }, request);
    }

    /**
     * Create agent group using either list of agent ids or list of agent emails but not both
     * <p>
     * POST /agent-groups
     *
     * @param agentGroup AgentGroup object to create
     * @return ResourceId holder object with agent group id
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId create(AgentGroup agentGroup) throws CallfireApiException, CallfireClientException {
        return client.post(AGENT_GROUPS_PATH, RESOURCE_ID_TYPE, agentGroup);
    }

    /**
     * Get agent group by id. Returns AgentGroup
     * <p>
     * GET /agent-groups/{id}
     *
     * @param id Id of agent group
     * @return AgentGroup pojo
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public AgentGroup get(Long id) throws CallfireApiException, CallfireClientException {
        return get(id, null);
    }

    /**
     * Get agent group by id. Returns AgentGroup
     * <p>
     * GET /agent-groups/{id}
     *
     * @param id     Id of agent group
     * @param fields Limit fields returned. Example fields=id,name,agents(id)
     * @return AgentGroup pojo
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public AgentGroup get(Long id, String fields) throws CallfireApiException, CallfireClientException {
        List<NameValuePair> queryParams = new ArrayList<>();
        addQueryParamIfSet("fields", fields, queryParams);

        String path = AGENT_GROUPS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, new TypeReference<AgentGroup>() {
        }, queryParams);
    }

    /**
     * Update existing agent group by id
     * <p>
     * PUT /agent-groups/{id}
     *
     * @param agentGroup AgentGroup to update
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void update(AgentGroup agentGroup) throws CallfireApiException, CallfireClientException {
        client.put(AGENT_GROUPS_ITEM_PATH.replaceFirst(PLACEHOLDER, Objects.toString(agentGroup.getId())),
            STRING_TYPE, agentGroup);
    }

    /**
     * Delete agent group by id
     * <p>
     * DELETE /agent-groups/{id}
     *
     * @param id Id of agent group to delete
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void delete(Long id) throws CallfireApiException, CallfireClientException {
        // TODO vmikhailov validate input
        client.delete(AGENT_GROUPS_ITEM_PATH.replaceFirst(PLACEHOLDER, Objects.toString(id)));
    }

}
