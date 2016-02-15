package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.*;
import com.callfire.api.client.api.campaigns.model.Agent;
import com.callfire.api.client.api.campaigns.model.AgentAction;
import com.callfire.api.client.api.campaigns.model.AgentCallDetails;
import com.callfire.api.client.api.campaigns.model.AgentSession;
import com.callfire.api.client.api.campaigns.model.IvrBroadcast;
import com.callfire.api.client.api.campaigns.model.request.FindAgentSessionsRequest;
import com.callfire.api.client.api.campaigns.model.request.FindAgentsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.of;
import static com.callfire.api.client.ModelType.pageOf;

/**
 * Represents rest endpoint /campaigns/cccs/agents
 *
 * @since 1.0
 */
public class AgentsApi {
    private static final String AGENTS_PATH = "/campaigns/cccs/agents";
    private static final String AGENTS_ITEM_PATH = "/campaigns/cccs/agents/{}";
    private static final String AGENTS_ITEM_JOIN_PATH = "/campaigns/cccs/agents/{}/{}/join";
    private static final String AGENTS_ITEM_CONTROL_PATH = "/campaigns/cccs/agents/{}/control";
    private static final String AGENTS_SESSIONS_PATH = "/campaigns/cccs/agents/sessions";
    private static final String AGENTS_SESSIONS_ITEM_PATH = "/campaigns/cccs/agents/sessions/{}";
    private static final String AGENTS_CALL_DETAILS_PATH = "/campaigns/cccs/agents/{}/callDetails";

    private RestApiClient client;

    public AgentsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find agents by email, id, campaign id, etc.
     *
     * @param request finder request with properties to search by
     * @return {@link Page} with {@link Agent} objects
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<Agent> find(FindAgentsRequest request) {
        return client.get(AGENTS_PATH, pageOf(Agent.class), request);
    }

    /**
     * Get agent by id
     *
     * @param id id of agent
     * @return {@link Agent} object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Agent get(Long id) {
        return get(id, null);
    }

    /**
     * Get agent by id
     *
     * @param id     id of agent
     * @param fields limit fields returned. Example fields=id,message
     * @return {@link IvrBroadcast} object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Agent get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(AGENTS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()), of(Agent.class), queryParams);
    }

    /**
     * Find agents sessions by email, id, campaign id, etc.
     *
     * @param request finder request with properties to search by
     * @return {@link Page} with {@link Agent} objects
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<AgentSession> findSessions(FindAgentSessionsRequest request) {
        return client.get(AGENTS_SESSIONS_PATH, pageOf(AgentSession.class), request);
    }

    /**
     * Get agent session by id
     *
     * @param id id of agent session
     * @return {@link AgentSession} object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public AgentSession getSession(Long id) {
        Validate.notNull(id, "id cannot be null");
        String path = AGENTS_SESSIONS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, of(AgentSession.class));
    }

    /**
     * Agent can control CCC campaign and do the next actions: "nextCall", "skipCall", "hangUp", "stop" and other actions
     *
     * @param agentSessionId unique agent session identifier
     * @param agentAction "nextCall", "skipCall", "hangUp", "stop" and other actions to control ccc campaign by agent
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void control(Long agentSessionId, AgentAction agentAction) {
        Validate.notNull(agentSessionId, "agentSessionId cannot be null");
        String path = AGENTS_ITEM_CONTROL_PATH.replaceFirst(PLACEHOLDER, agentSessionId.toString());
        client.post(path, null, agentAction);
    }

    /**
     * Connects agent to specified CCC campaign by calling him to a specified phone number
     *
     * @param campaignId ccc campaign id agent will join to
     * @param numberToCall the number CallFire will call agent to connect him to a specific campaign
     * @return agentSessionId the id of newly created agent session
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId joinCampaign(Long campaignId, String numberToCall) {
        Validate.notNull(campaignId, "campaignId cannot be null");
        Validate.notNull(numberToCall, "numberToCall cannot be null");
        String path = AGENTS_ITEM_JOIN_PATH.replaceFirst(PLACEHOLDER, campaignId.toString());
        path = path.replaceFirst(PLACEHOLDER, numberToCall);
        return client.post(path, of(ResourceId.class));
    }
    /**
     * Getting the details about current or last agent call
     *
     * @param agentSessionId unique agent session identifier
     * @return agentCallDetails call details like First name, Last name and etc of the current agent call
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public AgentCallDetails getAgentCallDetails(Long agentSessionId) {
        Validate.notNull(agentSessionId, "agentSessionId cannot be null");
        String path = AGENTS_CALL_DETAILS_PATH .replaceFirst(PLACEHOLDER, String.valueOf(agentSessionId));
        return client.get(path, of(AgentCallDetails.class));
    }
}
