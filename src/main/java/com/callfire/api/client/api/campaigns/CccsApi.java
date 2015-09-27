package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.campaigns.model.Agent;
import com.callfire.api.client.api.campaigns.model.AgentGroup;
import com.callfire.api.client.api.campaigns.model.CccCampaign;
import com.callfire.api.client.api.campaigns.model.request.AgentInviteRequest;
import com.callfire.api.client.api.campaigns.model.request.FindBroadcastsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientConstants.Type.*;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.api.campaigns.AgentGroupsApi.LIST_OF_AGENT_GROUP_TYPE;
import static com.callfire.api.client.api.campaigns.AgentsApi.LIST_OF_AGENT_TYPE;

/**
 * Represents rest endpoint /campaigns/cccs
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
    private static final String CCC_ITEM_CONTROL_PATH = "/campaigns/cccs/{}/control";
    private static final TypeReference<CccCampaign> CCC_TYPE = new TypeReference<CccCampaign>() {
    };
    private static final TypeReference<Page<CccCampaign>> PAGE_OF_CCCS_TYPE = new TypeReference<Page<CccCampaign>>() {
    };

    private RestApiClient client;

    public CccsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find ccc campaigns by name, label, etc...
     *
     * @param request finder request with properties to search by
     * @return {@link Page} with {@link CccCampaign} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<CccCampaign> find(FindBroadcastsRequest request) {
        return client.get(CCC_PATH, PAGE_OF_CCCS_TYPE, request);
    }

    /**
     * Create ccc campaign
     *
     * @param campaign CCC campaign to create
     * @return {@link ResourceId} with {@link CccCampaign} id
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId create(CccCampaign campaign) {
        return client.post(CCC_PATH, RESOURCE_ID_TYPE, campaign);
    }

    /**
     * Get CCC campaign by id
     *
     * @param id id of campaign
     * @return {@link CccCampaign} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
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
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public CccCampaign get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(CCC_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()), CCC_TYPE, queryParams);
    }

    /**
     * Update CCC campaign
     *
     * @param campaign CCC campaign to update
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void update(CccCampaign campaign) {
        Validate.notNull(campaign.getId(), "campaign.id cannot be null");
        client.put(CCC_ITEM_PATH.replaceFirst(PLACEHOLDER, campaign.getId().toString()), VOID_TYPE, campaign);
    }

    /**
     * Archive CCC campaign
     *
     * @param id campaign id
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void delete(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.delete(CCC_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()));
    }

    /**
     * Control CCC campaign (START, STOP, ARCHIVE)
     *
     * @param id      id of campaign
     * @param command command for campaign
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void control(Long id) {
        Validate.notNull(id, "id cannot be null");
        Validate.notNull(null, "command cannot be null");
        client.post(CCC_ITEM_CONTROL_PATH.replaceFirst(PLACEHOLDER, id.toString()), VOID_TYPE, null);
    }

    /**
     * Get agents of particular campaign
     *
     * @param campaignId id of campaign
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<Agent> getAgents(Long campaignId) {
        return getAgents(campaignId, null);
    }

    /**
     * Get agents of particular campaign
     *
     * @param campaignId id of campaign
     * @param fields     limit fields returned. Example fields=id,message
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<Agent> getAgents(Long campaignId, String fields) {
        Validate.notNull(campaignId, "campaignId cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = CCC_ITEM_AGENTS_PATH.replaceFirst(PLACEHOLDER, campaignId.toString());
        return client.post(path, LIST_OF_AGENT_TYPE, queryParams);
    }

    /**
     * Get agent invite URI for CCC campaign
     *
     * @param campaignId id of campaign
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public String getAgentInviteUri(Long campaignId) {
        Validate.notNull(campaignId, "campaignId cannot be null");
        String path = CCC_ITEM_INVITE_URI_PATH.replaceFirst(PLACEHOLDER, campaignId.toString());
        return client.get(path, STRING_TYPE);
    }

    /**
     * Get agents of particular campaign
     *
     * @param campaignId id of campaign
     * @param agentId    id of assigned agent
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
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
     * @param fields     limit fields returned. Example fields=id,message
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<AgentGroup> getAgentGroups(Long campaignId, String fields) {
        Validate.notNull(campaignId, "campaignId cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = CCC_ITEM_AGENT_GROUPS_PATH.replaceFirst(PLACEHOLDER, campaignId.toString());
        return client.get(path, LIST_OF_AGENT_GROUP_TYPE, queryParams);
    }

    /**
     * Add agents to particular CCC campaign
     *
     * @param request request object with id of CCC campaign and agent information
     * @return
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Map<String, String> addAgents(AgentInviteRequest request) {
        Validate.notNull(request.getCampaignId(), "request.campaignId cannot be null");
        String path = CCC_ITEM_INVITES_PATH.replaceFirst(PLACEHOLDER, request.getCampaignId().toString());
        return client.post(path, MAP_OF_STRINGS_TYPE, request);
    }

    /**
     * Add agent groups to particular CCC campaign
     *
     * @param campaignId    id of campaign
     * @param agentGroupIds id of assigned group of agents
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void addAgentGroups(Long campaignId, List<Long> agentGroupIds) {
        Validate.notNull(campaignId, "campaignId cannot be null");
        String path = CCC_ITEM_AGENT_GROUPS_PATH.replaceFirst(PLACEHOLDER, campaignId.toString());
        client.post(path, VOID_TYPE, agentGroupIds);
    }

    /**
     * Remove agent group from particular CCC campaign
     *
     * @param campaignId   id of campaign
     * @param agentGroupId id of assigned group of agents
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void removeAgentGroup(Long campaignId, Long agentGroupId) {
        Validate.notNull(campaignId, "campaignId cannot be null");
        Validate.notNull(agentGroupId, "agentGroupId cannot be null");
        String path = CCC_ITEM_AGENT_GROUPS_ITEM_PATH.replaceFirst(PLACEHOLDER, campaignId.toString())
            .replaceFirst(PLACEHOLDER, agentGroupId.toString());
        client.delete(path);
    }
}
