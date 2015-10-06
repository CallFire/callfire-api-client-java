package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.campaigns.model.Agent;
import com.callfire.api.client.api.campaigns.model.AgentSession;
import com.callfire.api.client.api.campaigns.model.request.FindAgentSessionsRequest;
import com.callfire.api.client.api.campaigns.model.request.FindAgentsRequest;
import com.callfire.api.client.api.common.model.Page;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

// TODO vmikhailov uncomment and fix when backend will be ready
@Ignore
public class AgentsApiTest extends AbstractApiTest {
    private static final String JSON_PATH = BASE_PATH + "/campaigns/agentsApi";

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findWebhooks.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        FindAgentsRequest request = FindAgentsRequest.create()
            .limit(5L)
            .offset(0L)
            .agentGroupName("name")
            .agentEmail("email")
            .build();
        Page<Agent> agents = client.agentsApi().find(request);
        assertThat(jsonConverter.serialize(agents), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "limit=5", "offset=0", "agentGroupName=name", "agentEmail=email");
    }

    @Test
    public void testGet() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getWebhook.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        Agent agent = client.agentsApi().get(11L, FIELDS);
        assertThat(jsonConverter.serialize(agent), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));

        client.agentGroupsApi().get(11L);
        assertEquals(2, captor.getAllValues().size());
        assertThat(captor.getAllValues().get(1).getURI().toString(), not(containsString("fields")));
    }

    @Test
    public void testGetNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.agentsApi().get(null);
    }

    @Test
    public void testFindSessions() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findWebhooks.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        FindAgentSessionsRequest request = FindAgentSessionsRequest.create()
            .limit(5L)
            .offset(0L)
            .agentId(1L)
            .agentEmail("email")
            .build();
        Page<AgentSession> agentSessions = client.agentsApi().findSessions(request);
        assertThat(jsonConverter.serialize(agentSessions), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "limit=5", "offset=0", "agentId=1", "agentEmail=email");
    }

    @Test
    public void testGetSession() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getWebhook.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        AgentSession agentSession = client.agentsApi().getSession(11L);
        assertThat(jsonConverter.serialize(agentSession), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), not(containsString("fields")));
    }

    @Test
    public void testGetSessionNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.agentsApi().getSession(null);
    }
}
