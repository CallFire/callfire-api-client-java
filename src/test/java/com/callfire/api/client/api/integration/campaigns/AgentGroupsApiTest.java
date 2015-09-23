package com.callfire.api.client.api.integration.campaigns;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.campaigns.AgentGroupsApi;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import com.callfire.api.client.api.campaigns.model.AgentGroup;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.campaigns.model.request.FindAgentGroupsRequest;
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
public class AgentGroupsApiTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testCrudOperations() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        AgentGroupsApi agentGroupsApi = callfireClient.getCampaignsApi().getCccsApi()
            .getAgentGroupsApi();

        // test createSubscription & getSubscription
        AgentGroup agentGroup1 = new AgentGroup();
        agentGroup1.setId(2L);
        agentGroup1.setName("test-group-1");
        ResourceId groupId1 = agentGroupsApi.createAgentGroup(agentGroup1);
        AgentGroup savedGroup1 = agentGroupsApi.getAgentGroup(groupId1.getId());
        assertEquals(groupId1.getId(), savedGroup1.getId());
        assertEquals(agentGroup1.getName(), savedGroup1.getName());
        assertThat(agentGroup1.getAgents(), is(empty()));

        AgentGroup agentGroup2 = new AgentGroup();
        agentGroup2.setName("test-group-2");
        ResourceId groupId2 = agentGroupsApi.createAgentGroup(agentGroup2);
        AgentGroup savedGroup2 = agentGroupsApi.getAgentGroup(groupId2.getId(), "name,campaignIds");
        assertNull(savedGroup2.getId());
        assertEquals(agentGroup2.getName(), savedGroup2.getName());
        assertThat(agentGroup2.getAgents(), is(empty()));

        // test QBE
        FindAgentGroupsRequest request = FindAgentGroupsRequest.create()
            .limit(100L)
            .offset(0L)
            .fields("items(id)")
            .build();
        Page<AgentGroup> agentGroupPage = agentGroupsApi.findAgentGroups(request);
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

        // test updateSubscription
        savedGroup1.setName("updated_name");
        agentGroupsApi.update(savedGroup1);
        AgentGroup updatedGroup = agentGroupsApi.getAgentGroup(groupId1.getId());
        assertEquals(savedGroup1.getName(), updatedGroup.getName());

        // test deleteSubscription
        agentGroupsApi.delete(groupId1.getId());
        agentGroupsApi.delete(groupId2.getId());

        expect404NotFoundCallfireApiException(ex);
        agentGroupsApi.getAgentGroup(groupId1.getId());
    }
}