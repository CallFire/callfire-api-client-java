package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.campaigns.model.Agent;
import com.callfire.api.client.api.campaigns.model.AgentSession;
import com.callfire.api.client.api.campaigns.model.IvrBroadcast;
import com.callfire.api.client.api.campaigns.model.request.FindAgentSessionsRequest;
import com.callfire.api.client.api.campaigns.model.request.FindAgentsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /campaigns/cccs/agents
 */
public class AgentsApi {
    private static final String AGENTS_PATH = "/campaigns/cccs/agents";
    private static final String AGENTS_ITEM_PATH = "/campaigns/cccs/agents/{}";
    private static final String AGENTS_ITEM_JOIN_PATH = "/campaigns/cccs/agents/{}/join";
    private static final String AGENTS_ITEM_NEXTCALL_PATH = "/campaigns/cccs/agents/{}/next-call";
    private static final String AGENTS_ITEM_HANGUP_PATH = "/campaigns/cccs/agents/{}/hang-up";
    private static final String AGENTS_SESSIONS_PATH = "/campaigns/cccs/agents/sessions";
    private static final String AGENTS_SESSIONS_ITEM_PATH = "/campaigns/cccs/agents/sessions/{}";
    private static final TypeReference<Page<Agent>> PAGE_OF_AGENTS_TYPE = new TypeReference<Page<Agent>>() {
    };
    private static final TypeReference<Page<AgentSession>> PAGE_OF_AGENT_SESSIONS_TYPE = new TypeReference<Page<AgentSession>>() {
    };
    private static final TypeReference<Agent> AGENT_TYPE = new TypeReference<Agent>() {
    };
    public static final TypeReference<List<Agent>> LIST_OF_AGENT_TYPE = new TypeReference<List<Agent>>() {
    };

    private RestApiClient client;

    public AgentsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find agents by email, id, campaign id, etc.
     *
     * @param request finder request with properties to search by
     * @return {@link Page} with {@link Agent} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Agent> find(FindAgentsRequest request) {
        return client.get(AGENTS_PATH, PAGE_OF_AGENTS_TYPE, request);
    }

    /**
     * Get agent by id
     *
     * @param id id of agent
     * @return {@link Agent} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
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
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Agent get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(AGENTS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()), AGENT_TYPE, queryParams);
    }

    /**
     * Find agents sessions by email, id, campaign id, etc.
     *
     * @param request finder request with properties to search by
     * @return {@link Page} with {@link Agent} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<AgentSession> findSessions(FindAgentSessionsRequest request) {
        return client.get(AGENTS_PATH, PAGE_OF_AGENT_SESSIONS_TYPE, request);
    }

    /**
     * Get agent session by id
     *
     * @param id id of agent session
     * @return {@link AgentSession} object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<AgentSession> getSession(Long id) {
        Validate.notNull(id, "id cannot be null");
        String path = AGENTS_SESSIONS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, PAGE_OF_AGENT_SESSIONS_TYPE);
    }

    private void joinCampaign(Long id) {
        // TODO vmikhailov implement when backend will be ready
        throw new NotImplementedException("not implemented yet");
    }

    private void nextCall(Long id) {
        throw new NotImplementedException("not implemented yet");
    }

    private void hangUp(Long id) {
        throw new NotImplementedException("not implemented yet");
    }
}
