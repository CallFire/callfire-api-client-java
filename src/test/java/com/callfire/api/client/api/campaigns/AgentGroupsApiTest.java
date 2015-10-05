package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.campaigns.model.AgentGroup;
import com.callfire.api.client.api.campaigns.model.request.FindAgentGroupsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import org.apache.http.client.methods.*;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

// TODO vmikhailov uncomment and fix when backend will be ready
@Ignore
public class AgentGroupsApiTest extends AbstractApiTest {
    private static final String JSON_PATH = BASE_PATH + "/campaigns/agentGroupsApi";

    @Test
    public void testCreate() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/createWebhook.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/createWebhook.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, responseJson);

        AgentGroup group = new AgentGroup();
        group.setName("API hook");
        ResourceId id = client.agentGroupsApi().create(group);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findWebhooks.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        FindAgentGroupsRequest request = FindAgentGroupsRequest.create()
            .limit(5L)
            .offset(0L)
            .name("name")
            .agentEmail("email")
            .build();
        Page<AgentGroup> groups = client.agentGroupsApi().find(request);
        assertThat(jsonConverter.serialize(groups), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertUriContainsQueryParams(arg.getURI(), "limit=5", "offset=0", "resource=resource", "enabled=false");
    }

    @Test
    public void testGet() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getWebhook.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        AgentGroup group = client.agentGroupsApi().get(11L, FIELDS);
        assertThat(jsonConverter.serialize(group), equalToIgnoringWhiteSpace(expectedJson));

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
        client.agentGroupsApi().get(null);
    }

    @Test
    public void testUpdate() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/request/updateWebhook.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        AgentGroup group = new AgentGroup();
        group.setId(11L);
        group.setName("API hook");
        client.agentGroupsApi().update(group);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPut.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(expectedJson));
        assertThat(arg.getURI().toString(), containsString("/11"));
    }

    @Test
    public void testUpdateNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        AgentGroup group = new AgentGroup();
        client.agentGroupsApi().update(group);
    }

    @Test
    public void testDelete() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        client.agentGroupsApi().delete(11L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/11"));
    }

    @Test
    public void testDeleteNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.agentGroupsApi().delete(null);
    }
}
