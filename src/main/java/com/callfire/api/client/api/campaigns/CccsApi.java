package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.*;
import com.callfire.api.client.api.campaigns.model.Agent;
import com.callfire.api.client.api.campaigns.model.AgentGroup;
import com.callfire.api.client.api.campaigns.model.CccCampaign;
import com.callfire.api.client.api.campaigns.model.request.AgentInviteRequest;
import com.callfire.api.client.api.campaigns.model.request.FindCccBroadcastsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.*;

/**
 * Represents rest endpoint /campaigns/cccs
 *
 * @since 1.0
 */
public class CccsApi {
    private static final String CCC_PATH = "/campaigns/cccs";
    private static final String CCC_ITEM_PATH = "/campaigns/cccs/{}";
    private static final String CCC_ITEM_INVITE_URI_PATH = "/campaigns/cccs/{}/agent-invite-uri";
    private static final String CCC_ITEM_INVITES_PATH = "/campaigns/cccs/{}/agent-invites";
    private static final String CCC_ITEM_AGENTS_PATH = "/campaigns/cccs/{}/agents";
    private static final String CCC_ITEM_AGENTS_ITEM_PATH = "/campaigns/cccs/{}/agents/{}";
    private static final String CCC_ITEM_AGENT_GROUPS_PATH = "/campaigns/cccs/{}/agent-groups";
    private static final String CCC_ITEM_AGENT_GROUPS_ITEM_PATH = "/campaigns/cccs/{}/agent-groups/{}";
    private static final String CCC_ITEM_START_PATH = "/campaigns/cccs/{}/start";
    private static final String CCC_ITEM_STOP_PATH = "/campaigns/cccs/{}/stop";
    private static final String CCC_ITEM_ARCHIVE_PATH = "/campaigns/cccs/{}/archive";

    private RestApiClient client;

    public CccsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find ccc campaigns by name, label, etc...
     *
     * @param request finder request with properties to search by
     * @return {@link Page} with {@link CccCampaign} objects
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<CccCampaign> find(FindCccBroadcastsRequest request) {
        return client.get(CCC_PATH, pageOf(CccCampaign.class), request);
    }

    /**
     * Create ccc campaign
     *
     * @param campaign CCC campaign to create
     * @return {@link ResourceId} with {@link CccCampaign} id
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId create(CccCampaign campaign) {
        return client.post(CCC_PATH, of(ResourceId.class), campaign);
    }

    /**
     * Get CCC campaign by id
     *
     * @param id id of campaign
     * @return {@link CccCampaign} object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CccCampaign get(Long id) {
        return get(id, null);
    }

    /**
     * Get CCC campaign by id
     *
     * @param id     id of campaign
     * @param fields limit fields returned. Example fields=id,message
     * @return {@link CccCampaign} object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CccCampaign get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(CCC_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()), of(CccCampaign.class), queryParams);
    }

    /**
     * Update CCC campaign
     *
     * @param campaign CCC campaign to update
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void update(CccCampaign campaign) {
        update(campaign, false);
    }

    /**
     * Update CCC campaign
     *
     * @param campaign  CCC campaign to update
     * @param sendEmail if true sends email to invited agents
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void update(CccCampaign campaign, Boolean sendEmail) {
        Validate.notNull(campaign.getId(), "campaign.id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("sendEmail", sendEmail, queryParams);
        String path = CCC_ITEM_PATH.replaceFirst(PLACEHOLDER, campaign.getId().toString());
        client.put(path, null, campaign, queryParams);
    }

    /**
     * Archive CCC campaign
     *
     * @param id campaign id
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
        client.delete(CCC_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()));
    }

    /**
     * Starts CCC campaign
     *
     * @param id id of campaign
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void start(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.post(CCC_ITEM_START_PATH.replaceFirst(PLACEHOLDER, id.toString()), null, null);
    }

    /**
     * Stops CCC campaign
     *
     * @param id id of campaign
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void stop(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.post(CCC_ITEM_STOP_PATH.replaceFirst(PLACEHOLDER, id.toString()), null, null);
    }

    /**
     * Archives CCC campaign
     *
     * @param id id of campaign
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void archive(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.post(CCC_ITEM_ARCHIVE_PATH.replaceFirst(PLACEHOLDER, id.toString()), null, null);
    }

    /**
     * Get agents of particular campaign
     *
     * @param campaignId id of campaign
     * @return list of assigned agents
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Agent> getAgents(Long campaignId) {
        return getAgents(campaignId, null);
    }

    /**
     * Get agents of particular campaign
     *
     * @param campaignId id of campaign
     * @param fields     limit fields returned. Example fields=id,message
     * @return list of assigned agents
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Agent> getAgents(Long campaignId, String fields) {
        Validate.notNull(campaignId, "campaignId cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = CCC_ITEM_AGENTS_PATH.replaceFirst(PLACEHOLDER, campaignId.toString());
        return client.get(path, listOf(Agent.class), queryParams);
    }

    /**
     * Get agent invite URI for CCC campaign
     *
     * @param campaignId id of campaign
     * @return URL for agent invitation
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public String getAgentInviteUri(Long campaignId) {
        Validate.notNull(campaignId, "campaignId cannot be null");
        String path = CCC_ITEM_INVITE_URI_PATH.replaceFirst(PLACEHOLDER, campaignId.toString());
        return client.get(path, of(String.class));
    }

    /**
     * Add agents to particular CCC campaign
     *
     * @param request request object with id of CCC campaign and agent information
     * @return map with agents email address as a key and invite URL as a value
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Map<String, String> addAgents(AgentInviteRequest request) {
        Validate.notNull(request.getCampaignId(), "request.campaignId cannot be null");
        String path = CCC_ITEM_INVITES_PATH.replaceFirst(PLACEHOLDER, request.getCampaignId().toString());
        return client.post(path, MAP_OF_STRINGS_TYPE, request);
    }

    /**
     * Remove agent from particular campaign
     *
     * @param campaignId id of campaign
     * @param agentId    id of assigned agent
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void removeAgent(Long campaignId, Long agentId) {
        Validate.notNull(campaignId, "campaignId cannot be null");
        Validate.notNull(agentId, "agentId cannot be null");
        String path = CCC_ITEM_AGENTS_ITEM_PATH.replaceFirst(PLACEHOLDER, campaignId.toString())
            .replaceFirst(PLACEHOLDER, agentId.toString());
        client.delete(path);
    }

    /**
     * Get agent groups of particular campaign
     *
     * @param campaignId id of campaign
     * @return list of agent groups assigned to particular campaign
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<AgentGroup> getAgentGroups(Long campaignId) {
        return getAgentGroups(campaignId, null);
    }

    /**
     * Get agent groups of particular campaign
     *
     * @param campaignId id of campaign
     * @param fields     limit fields returned. Example fields=id,message
     * @return list of agent groups assigned to particular campaign
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<AgentGroup> getAgentGroups(Long campaignId, String fields) {
        Validate.notNull(campaignId, "campaignId cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = CCC_ITEM_AGENT_GROUPS_PATH.replaceFirst(PLACEHOLDER, campaignId.toString());
        return client.get(path, listOf(AgentGroup.class), queryParams);
    }

    /**
     * Add agent groups to particular CCC campaign
     *
     * @param campaignId    id of campaign
     * @param agentGroupIds id of assigned group of agents
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void addAgentGroups(Long campaignId, List<Long> agentGroupIds) {
        Validate.notNull(campaignId, "campaignId cannot be null");
        String path = CCC_ITEM_AGENT_GROUPS_PATH.replaceFirst(PLACEHOLDER, campaignId.toString());
        client.post(path, null, agentGroupIds);
    }

    /**
     * Remove agent group from particular CCC campaign
     *
     * @param campaignId   id of campaign
     * @param agentGroupId id of assigned group of agents
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void removeAgentGroup(Long campaignId, Long agentGroupId) {
        Validate.notNull(campaignId, "campaignId cannot be null");
        Validate.notNull(agentGroupId, "agentGroupId cannot be null");
        String path = CCC_ITEM_AGENT_GROUPS_ITEM_PATH.replaceFirst(PLACEHOLDER, campaignId.toString())
            .replaceFirst(PLACEHOLDER, agentGroupId.toString());
        client.delete(path);
    }
}
