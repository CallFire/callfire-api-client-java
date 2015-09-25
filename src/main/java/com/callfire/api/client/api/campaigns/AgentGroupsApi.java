package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.campaigns.model.AgentGroup;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.campaigns.model.request.FindAgentGroupsRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.callfire.api.client.ClientConstants.Type.RESOURCE_ID_TYPE;
import static com.callfire.api.client.ClientConstants.Type.STRING_TYPE;
import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /campaigns/cccs/agent-groups
 */
public class AgentGroupsApi {
    private static final String AGENT_GROUPS_PATH = "/campaigns/cccs/agent-groups";
    private static final String AGENT_GROUPS_ITEM_PATH = "/campaigns/cccs/agent-groups/{}";
    private static final TypeReference<Page<AgentGroup>> PAGE_OF_AGENT_GROUPS_TYPE = new TypeReference<Page<AgentGroup>>() {
    };
    public static final TypeReference<AgentGroup> AGENT_GROUP_TYPE = new TypeReference<AgentGroup>() {
    };
    public static final TypeReference<List<AgentGroup>> LIST_OF_AGENT_GROUP_TYPE = new TypeReference<List<AgentGroup>>() {
    };

    private RestApiClient client;

    public AgentGroupsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Query for agent groups using optional filters
     * (Note: agentId and agentEmail are mutually exclusive, please only provide one)
     * <p/>
     * GET /agent-groups
     *
     * @return Page object with matched entities
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<AgentGroup> findAgentGroups(FindAgentGroupsRequest request) {
        return client.get(AGENT_GROUPS_PATH, PAGE_OF_AGENT_GROUPS_TYPE, request);
    }

    /**
     * Create agent group using either list of agent ids or list of agent emails but not both
     * <p/>
     * POST /agent-groups
     *
     * @param agentGroup AgentGroup object to create
     * @return ResourceId holder object with agent group id
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId createAgentGroup(AgentGroup agentGroup) {
        return client.post(AGENT_GROUPS_PATH, RESOURCE_ID_TYPE, agentGroup);
    }

    /**
     * Get agent group by id. Returns AgentGroup
     * <p/>
     * GET /agent-groups/{id}
     *
     * @param id Id of agent group
     * @return AgentGroup pojo
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public AgentGroup getAgentGroup(Long id) {
        return getAgentGroup(id, null);
    }

    /**
     * Get agent group by id. Returns AgentGroup
     * <p/>
     * GET /agent-groups/{id}
     *
     * @param id     Id of agent group
     * @param fields Limit fields returned. Example fields=id,name,agents(id)
     * @return AgentGroup pojo
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public AgentGroup getAgentGroup(Long id, String fields) throws CallfireApiException, CallfireClientException {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>();
        addQueryParamIfSet("fields", fields, queryParams);
        String path = AGENT_GROUPS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, AGENT_GROUP_TYPE, queryParams);
    }

    /**
     * Update existing agent group by id
     * <p/>
     * PUT /agent-groups/{id}
     *
     * @param agentGroup AgentGroup to update
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void update(AgentGroup agentGroup) {
        Validate.notNull(agentGroup.getId(), "id cannot be null");
        client.put(AGENT_GROUPS_ITEM_PATH.replaceFirst(PLACEHOLDER, Objects.toString(agentGroup.getId())),
            STRING_TYPE, agentGroup);
    }

    /**
     * Delete agent group by id
     * <p/>
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
