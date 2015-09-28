package com.callfire.api.client.api.integration.campaigns;

import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.campaigns.IvrBroadcastsApi;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.Broadcast;
import com.callfire.api.client.api.campaigns.model.IvrBroadcast;
import com.callfire.api.client.api.campaigns.model.Recipient;
import com.callfire.api.client.api.campaigns.model.request.AddBatchRequest;
import com.callfire.api.client.api.campaigns.model.request.FindIvrBroadcastsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * integration tests for /campaigns/ivrs api endpoint
 */
public class IvrBroadcastsApiTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testCrudOperations() throws Exception {
        IvrBroadcastsApi api = getCallfireClient().ivrBroadcastsApi();
        IvrBroadcast broadcast = new IvrBroadcast();
        broadcast.setName("ivr_broadcast");
        broadcast.setInbound(false);
        broadcast.setDialplanXml("<dialplan name=\"Root\"></dialplan>");

        broadcast.setRecipients(makeRecipients());
        ResourceId id = api.create(broadcast, true);

        IvrBroadcast savedBroadcast = api.get(id.getId());
        assertEquals(broadcast.getName(), savedBroadcast.getName());
        // TODO vmikhailov there is no back mapping for recipients
        //        assertEquals(2, savedBroadcast.getRecipients().size());
        //        assertThat(savedBroadcast.getRecipients(),
        //            hasItem(Matchers.<Recipient>hasProperty("phoneNumber", startsWith("1213123456"))));

        savedBroadcast.setName("updated_name");
        api.update(savedBroadcast);

        IvrBroadcast updatedBroadcast = api.get(id.getId(), "id,name");
        assertNull(updatedBroadcast.getStatus());
        assertNotNull(updatedBroadcast.getId());
        assertEquals(savedBroadcast.getName(), updatedBroadcast.getName());
    }

    @Test
    public void testStartStopArchiveCampaign() throws Exception {
        IvrBroadcastsApi api = getCallfireClient().ivrBroadcastsApi();
        IvrBroadcast campaign = api.get(7L);
        System.out.println(campaign);
        assertNotNull(campaign);
        // start
        api.start(campaign.getId());
        campaign = api.get(campaign.getId(), "id,status");
        assertEquals(Broadcast.Status.RUNNING, campaign.getStatus());
        // stop
        api.stop(campaign.getId());
        campaign = api.get(campaign.getId(), "id,status");
        assertEquals(Broadcast.Status.STOPPED, campaign.getStatus());
        // archive
        api.archive(campaign.getId());
        campaign = api.get(campaign.getId(), "id,status");
        assertEquals(Broadcast.Status.ARCHIVED, campaign.getStatus());
    }

    @Test
    public void testGetBroadcastCalls() throws Exception {
        IvrBroadcastsApi api = getCallfireClient().ivrBroadcastsApi();
        FindIvrBroadcastsRequest findRequest = FindIvrBroadcastsRequest.create()
            .name("ivr_broadcast")
            .build();
        Page<IvrBroadcast> ivrs = api.find(findRequest);
        System.out.println(ivrs);
        assertThat(ivrs.getItems(), not(empty()));

        GetByIdRequest getCallsRequest = GetByIdRequest.create()
            .id(ivrs.getItems().get(0).getId())
            .build();
        Page<Call> calls = api.getCalls(getCallsRequest);
        System.out.println(calls);
        assertThat(calls.getItems(), not(empty()));
    }

    @Test
    public void testAddRecipientsAndAddRemoveBatches() throws Exception {
        IvrBroadcastsApi api = getCallfireClient().ivrBroadcastsApi();

        FindIvrBroadcastsRequest findRequest = FindIvrBroadcastsRequest.create()
            .name("ivr_broadcast")
            .limit(1L)
            .build();
        Page<IvrBroadcast> ivrs = api.find(findRequest);
        System.out.println(ivrs);
        assertThat(ivrs.getItems(), not(empty()));
        Long id = ivrs.getItems().get(0).getId();

        // add recipients
        Recipient recipient1 = new Recipient();
        recipient1.setPhoneNumber("12131234567");
        Recipient recipient2 = new Recipient();
        recipient2.setPhoneNumber("12131234568");
        List<Call> calls = api.addRecipients(id, asList(recipient1, recipient2));
        System.out.println(calls);
        assertEquals(2, calls.size());

        // get batches
        GetByIdRequest getBatchesRequest = GetByIdRequest.create()
            .id(id)
            .build();
        Page<Batch> batches = api.getBatches(getBatchesRequest);
        System.out.println(batches);

        // add batch
        AddBatchRequest addBatchRequest = AddBatchRequest.create()
            .campaignId(id)
            .name("new_batch")
            .recipients(makeRecipients())
            .build();
        api.addBatch(addBatchRequest);

        Page<Batch> updatedBatches = api.getBatches(getBatchesRequest);
        System.out.println(batches);
        assertEquals(batches.getItems().size() + 1, updatedBatches.getItems().size());
    }
}
