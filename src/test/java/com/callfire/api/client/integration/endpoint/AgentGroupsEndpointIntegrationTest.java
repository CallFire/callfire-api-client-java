package com.callfire.api.client.integration.endpoint;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.model.AgentGroup;
import com.callfire.api.client.model.Page;
import com.callfire.api.client.model.ResourceId;
import com.callfire.api.client.model.request.FindAgentGroupsRequest;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static com.callfire.api.client.model.request.FindAgentGroupsRequest.FindAgentGroupsRequestBuilder;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * integration tests for /agent-groups api endpoint
 */
public class AgentGroupsEndpointIntegrationTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testAgentGroupCrudOperations() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        // test create & get
        AgentGroup agentGroup1 = new AgentGroup();
        agentGroup1.setId(2L);
        agentGroup1.setName("test-group-1");
        ResourceId groupId1 = callfireClient.getAgentGroupsEndpoint().createAgentGroup(agentGroup1);
        AgentGroup savedGroup1 = callfireClient.getAgentGroupsEndpoint().getAgentGroup(groupId1.getId());
        assertEquals(groupId1.getId(), savedGroup1.getId());
        assertEquals(agentGroup1.getName(), savedGroup1.getName());
        assertThat(agentGroup1.getAgents(), is(empty()));

        AgentGroup agentGroup2 = new AgentGroup();
        agentGroup2.setName("test-group-2");
        ResourceId groupId2 = callfireClient.getAgentGroupsEndpoint().createAgentGroup(agentGroup2);
        AgentGroup savedGroup2 = callfireClient.getAgentGroupsEndpoint()
            .getAgentGroup(groupId2.getId(), "name,campaignIds");
        assertNull(savedGroup2.getId());
        assertEquals(agentGroup2.getName(), savedGroup2.getName());
        assertThat(agentGroup2.getAgents(), is(empty()));

        // test QBE
        FindAgentGroupsRequest request = FindAgentGroupsRequestBuilder.create()
            .setLimit(100L)
            .setOffset(0L)
            .setFields("items(id)")
            .build();
        Page<AgentGroup> agentGroupPage = callfireClient.getAgentGroupsEndpoint().findAgentGroups(request);
        List<AgentGroup> items = agentGroupPage.getItems();
        assertEquals(2, items.size());
        assertNull(items.get(0).getName());
        assertNull(items.get(1).getName());
        List<Long> itemsIds = items.stream().mapToLong(AgentGroup::getId).boxed().collect(toList());
        assertThat(itemsIds, hasItem(groupId1.getId()));
        assertThat(itemsIds, hasItem(groupId2.getId()));

        // test update
        savedGroup1.setName("updated_name");
        callfireClient.getAgentGroupsEndpoint().updateAgentGroup(savedGroup1);
        AgentGroup updatedGroup = callfireClient.getAgentGroupsEndpoint().getAgentGroup(groupId1.getId());
        assertEquals(savedGroup1.getName(), updatedGroup.getName());

        // test delete
        callfireClient.getAgentGroupsEndpoint().deleteAgentGroup(groupId1.getId());
        callfireClient.getAgentGroupsEndpoint().deleteAgentGroup(groupId2.getId());

        ex.expect(CallfireApiException.class);
        ex.expect(hasProperty("apiErrorMessage", hasProperty("httpStatusCode", Matchers.is(404))));
        callfireClient.getAgentGroupsEndpoint().getAgentGroup(groupId1.getId());
    }
}
