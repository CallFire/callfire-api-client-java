package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.*;
import com.callfire.api.client.api.campaigns.model.AgentGroup;
import com.callfire.api.client.api.campaigns.model.request.FindAgentGroupsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.of;
import static com.callfire.api.client.ModelType.pageOf;

/**
 * Represents rest endpoint /campaigns/cccs/agent-groups
 *
 * @since 1.0
 */
public class AgentGroupsApi {
    private static final String AGENT_GROUPS_PATH = "/campaigns/cccs/agent-groups";
    private static final String AGENT_GROUPS_ITEM_PATH = "/campaigns/cccs/agent-groups/{}";

    private RestApiClient client;

    public AgentGroupsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Query for agent groups using optional filters
     * (Note: agentId and agentEmail are mutually exclusive, please only provide one)
     * GET /campaigns/cccs/agent-groups
     *
     * @param request request object to query agent groups
     * @return Page object with matched entities
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<AgentGroup> find(FindAgentGroupsRequest request) {
        return client.get(AGENT_GROUPS_PATH, pageOf(AgentGroup.class), request);
    }

    /**
     * Create agent group using either list of agent ids or list of agent emails but not both
     * POST /campaigns/cccs/agent-groups
     *
     * @param agentGroup AgentGroup object to create
     * @return ResourceId holder object with agent group id
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId create(AgentGroup agentGroup) {
        return client.post(AGENT_GROUPS_PATH, of(ResourceId.class), agentGroup);
    }

    /**
     * Get agent group by id. Returns AgentGroup
     * GET /campaigns/cccs/agent-groups/{id}
     *
     * @param id Id of agent group
     * @return AgentGroup pojo
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public AgentGroup get(Long id) {
        return get(id, null);
    }

    /**
     * Get agent group by id. Returns AgentGroup
     * GET /campaigns/cccs/agent-groups/{id}
     *
     * @param id     Id of agent group
     * @param fields Limit fields returned. Example fields=id,name,agents(id)
     * @return AgentGroup pojo
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public AgentGroup get(Long id, String fields) throws CallfireApiException, CallfireClientException {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>();
        addQueryParamIfSet("fields", fields, queryParams);
        String path = AGENT_GROUPS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, of(AgentGroup.class), queryParams);
    }

    /**
     * Update existing agent group by id
     * PUT /campaigns/cccs/agent-groups/{id}
     *
     * @param agentGroup AgentGroup to update
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void update(AgentGroup agentGroup) {
        Validate.notNull(agentGroup.getId(), "id cannot be null");
        String path = AGENT_GROUPS_ITEM_PATH.replaceFirst(PLACEHOLDER, Objects.toString(agentGroup.getId()));
        client.put(path, null, agentGroup);
    }

    /**
     * Delete agent group by id
     * DELETE /campaigns/cccs/agent-groups/{id}
     *
     * @param id Id of agent group to delete
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void delete(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.delete(AGENT_GROUPS_ITEM_PATH.replaceFirst(PLACEHOLDER, Objects.toString(id)));
    }

}
