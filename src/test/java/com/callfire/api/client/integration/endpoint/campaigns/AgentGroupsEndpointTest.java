package com.callfire.api.client.integration.endpoint.campaigns;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.endpoint.campaigns.AgentGroupsEndpoint;
import com.callfire.api.client.integration.endpoint.AbstractIntegrationTest;
import com.callfire.api.client.model.AgentGroup;
import com.callfire.api.client.model.Page;
import com.callfire.api.client.model.ResourceId;
import com.callfire.api.client.model.request.FindAgentGroupsRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * integration tests for /campaigns/cccs/agent-groups api endpoint
 */
public class AgentGroupsEndpointTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testCrudOperations() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        AgentGroupsEndpoint agentGroupsEndpoint = callfireClient.getCampaignsEndpoint().getCccsEndpoint()
            .getAgentGroupsEndpoint();

        // test create & get
        AgentGroup agentGroup1 = new AgentGroup();
        agentGroup1.setId(2L);
        agentGroup1.setName("test-group-1");
        ResourceId groupId1 = agentGroupsEndpoint.create(agentGroup1);
        AgentGroup savedGroup1 = agentGroupsEndpoint.get(groupId1.getId());
        assertEquals(groupId1.getId(), savedGroup1.getId());
        assertEquals(agentGroup1.getName(), savedGroup1.getName());
        assertThat(agentGroup1.getAgents(), is(empty()));

        AgentGroup agentGroup2 = new AgentGroup();
        agentGroup2.setName("test-group-2");
        ResourceId groupId2 = agentGroupsEndpoint.create(agentGroup2);
        AgentGroup savedGroup2 = agentGroupsEndpoint.get(groupId2.getId(), "name,campaignIds");
        assertNull(savedGroup2.getId());
        assertEquals(agentGroup2.getName(), savedGroup2.getName());
        assertThat(agentGroup2.getAgents(), is(empty()));

        // test QBE
        FindAgentGroupsRequest request = FindAgentGroupsRequest.create()
            .setLimit(100L)
            .setOffset(0L)
            .setFields("items(id)")
            .build();
        Page<AgentGroup> agentGroupPage = agentGroupsEndpoint.find(request);
        List<AgentGroup> items = agentGroupPage.getItems();
        assertEquals(2, items.size());
        assertNull(items.get(0).getName());
        assertNull(items.get(1).getName());

        List<Long> itemsIds = new ArrayList<>(items.size());
        for (AgentGroup item : items) {
            itemsIds.add(item.getId());
        }

        assertThat(itemsIds, hasItem(groupId1.getId()));
        assertThat(itemsIds, hasItem(groupId2.getId()));

        // test update
        savedGroup1.setName("updated_name");
        agentGroupsEndpoint.update(savedGroup1);
        AgentGroup updatedGroup = agentGroupsEndpoint.get(groupId1.getId());
        assertEquals(savedGroup1.getName(), updatedGroup.getName());

        // test delete
        agentGroupsEndpoint.delete(groupId1.getId());
        agentGroupsEndpoint.delete(groupId2.getId());

        expect404NotFoundCallfireApiException(ex);
        agentGroupsEndpoint.get(groupId1.getId());
    }
}
