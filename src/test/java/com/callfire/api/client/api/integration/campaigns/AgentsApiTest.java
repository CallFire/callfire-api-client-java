package com.callfire.api.client.api.integration.campaigns;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.campaigns.model.Agent;
import com.callfire.api.client.api.campaigns.model.AgentSession;
import com.callfire.api.client.api.campaigns.model.request.FindAgentSessionsRequest;
import com.callfire.api.client.api.campaigns.model.request.FindAgentsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;

/**
 * integration tests for /campaigns/cccs/agents api endpoint
 */
public class AgentsApiTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testFindAndGet() throws Exception {
        CallfireClient client = getCallfireClient();

        FindAgentsRequest request = FindAgentsRequest.create()
            .agentGroupName("")
            .build();
        Page<Agent> agentPage = client.agentsApi().find(request);
        System.out.println(agentPage);

        assertFalse(agentPage.getItems().isEmpty());

        Agent agent = client.agentsApi().get(agentPage.getItems().get(0).getId());
        System.out.println(agent);
    }

    @Test
    public void testFindSessionsAndGet() throws Exception {
        CallfireClient client = getCallfireClient();

        FindAgentSessionsRequest request = FindAgentSessionsRequest.create()
            .agentEmail("dev+agent@callfire.com")
            .build();
        Page<AgentSession> sessionPage = client.agentsApi().findSessions(request);
        System.out.println(sessionPage);

        assertFalse(sessionPage.getItems().isEmpty());

        AgentSession session = client.agentsApi().getSession(sessionPage.getItems().get(0).getId());
        System.out.println(session);
    }

    @Test
    public void testjoinCampaign() throws Exception {
        // TODO vmikhailov implement when backend will be ready
    }

    @Test
    public void testHangUp() throws Exception {
        // TODO vmikhailov implement when backend will be ready
    }

    @Test
    public void testNextCall() throws Exception {
        // TODO vmikhailov implement when backend will be ready
    }
}
