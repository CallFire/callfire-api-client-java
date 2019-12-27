package com.callfire.api.client.integration.campaigns;

import static com.callfire.api.client.api.callstexts.model.Action.State.DISABLED;
import static com.callfire.api.client.api.callstexts.model.Action.State.READY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.callstexts.model.CallRecipient;
import com.callfire.api.client.api.campaigns.CallBroadcastsApi;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.Broadcast.Status;
import com.callfire.api.client.api.campaigns.model.CallBroadcast;
import com.callfire.api.client.api.campaigns.model.CallBroadcastSounds;
import com.callfire.api.client.api.campaigns.model.request.AddBatchRequest;
import com.callfire.api.client.api.campaigns.model.request.AddRecipientsRequest;
import com.callfire.api.client.api.campaigns.model.request.CreateBroadcastRequest;
import com.callfire.api.client.api.campaigns.model.request.FindBroadcastCallsRequest;
import com.callfire.api.client.api.campaigns.model.request.FindCallBroadcastsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.integration.AbstractIntegrationTest;

/**
 * integration tests for /campaigns/voice-broadcasts api endpoint
 */
public class CallBroadcastsApiTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testCrudOperations() throws Exception {
        CallBroadcastsApi api = getCallfireClient().callBroadcastsApi();
        CallBroadcast broadcast = new CallBroadcast();
        broadcast.setName("call_broadcast");
        broadcast.setRecipients(makeRecipients());
        CallBroadcastSounds sounds = new CallBroadcastSounds();
        sounds.setLiveSoundText(getSoundText());
        sounds.setMachineSoundText(getSoundText());
        broadcast.setSounds(sounds);
        broadcast.setResumeNextDay(true);
        ResourceId id = api.create(broadcast, true);

        CallBroadcast savedBroadcast = api.get(id.getId());
        assertEquals(broadcast.getName(), savedBroadcast.getName());
        assertEquals(broadcast.getResumeNextDay(), true);
        // TODO vmikhailov there is no back mapping for recipients
        //        assertEquals(2, savedBroadcast.getRecipients().size());
        //        assertThat(savedBroadcast.getRecipients(),
        //            hasItem(Matchers.<Recipient>hasProperty("phoneNumber", startsWith("1213123456"))));

        savedBroadcast.setName("updated_name");
        savedBroadcast.setSounds(null);
        api.update(savedBroadcast);

        CallBroadcast updatedBroadcast = api.get(id.getId(), "id,name");
        assertNull(updatedBroadcast.getStatus());
        assertNotNull(updatedBroadcast.getId());
        assertEquals(savedBroadcast.getName(), updatedBroadcast.getName());
    }

    @Test
    public void testCrudOperationsWithRequest() throws Exception {
        CallBroadcastsApi api = getCallfireClient().callBroadcastsApi();
        CallBroadcast broadcast = new CallBroadcast();
        broadcast.setName("call_broadcast");
        broadcast.setRecipients(makeRecipients());
        CallBroadcastSounds sounds = new CallBroadcastSounds();
        sounds.setLiveSoundText(getSoundText());
        sounds.setMachineSoundText(getSoundText());
        broadcast.setSounds(sounds);
        broadcast.setResumeNextDay(true);

        CreateBroadcastRequest<CallBroadcast> request = CreateBroadcastRequest.<CallBroadcast>create()
            .broadcast(broadcast)
            .start(true)
            .strictValidation(true)
            .build();

        ResourceId id = api.create(request);
        assertNotNull(id.getId());

        CallBroadcast savedBroadcast = api.get(id.getId());
        assertEquals(broadcast.getName(), savedBroadcast.getName());
        assertEquals(broadcast.getResumeNextDay(), true);
        // TODO vmikhailov there is no back mapping for recipients
        //        assertEquals(2, savedBroadcast.getRecipients().size());
        //        assertThat(savedBroadcast.getRecipients(),
        //            hasItem(Matchers.<Recipient>hasProperty("phoneNumber", startsWith("1213123456"))));

        savedBroadcast.setName("updated_name");
        savedBroadcast.setSounds(null);
        api.update(savedBroadcast, true);

        CallBroadcast updatedBroadcast = api.get(id.getId(), "id,name");
        assertNull(updatedBroadcast.getStatus());
        assertNotNull(updatedBroadcast.getId());
        assertEquals(savedBroadcast.getName(), updatedBroadcast.getName());
    }

    @Test
    public void testStartStopArchiveCampaign() {
        CallBroadcastsApi api = getCallfireClient().callBroadcastsApi();

        CallBroadcast broadcast = new CallBroadcast();
        broadcast.setName("call_broadcast");
        broadcast.setRecipients(makeRecipients());
        CallBroadcastSounds sounds = new CallBroadcastSounds();
        sounds.setLiveSoundText(getSoundText());
        sounds.setMachineSoundText(getSoundText());
        broadcast.setSounds(sounds);
        ResourceId id = api.create(broadcast, false);

        CallBroadcast campaign = api.get(id.getId());
        System.out.println(campaign);
        assertNotNull(campaign);
        // start
        api.start(campaign.getId());
        campaign = api.get(campaign.getId(), "id,status");
        assertEquals(Status.RUNNING, campaign.getStatus());
        // stop
        api.stop(campaign.getId());
        campaign = api.get(campaign.getId(), "id,status");
        assertEquals(Status.STOPPED, campaign.getStatus());
        // archive
        api.archive(campaign.getId());
        campaign = api.get(campaign.getId(), "id,status");
        assertEquals(Status.ARCHIVED, campaign.getStatus());
    }

    @Test
    public void testGetBroadcastCalls() throws Exception {
        CallBroadcastsApi api = getCallfireClient().callBroadcastsApi();

        CallBroadcast broadcast = new CallBroadcast();
        broadcast.setName("call_broadcast");
        broadcast.setRecipients(makeRecipients());
        CallBroadcastSounds sounds = new CallBroadcastSounds();
        sounds.setLiveSoundText(getSoundText());
        sounds.setMachineSoundText(getSoundText());
        broadcast.setSounds(sounds);
        ResourceId id = api.create(broadcast, false);

        GetByIdRequest getCallsRequest = GetByIdRequest.create()
            .id(id.getId())
            .build();
        Page<Call> calls = api.getCalls(getCallsRequest);
        System.out.println(calls);
        assertThat(calls.getItems(), not(empty()));

        Long testBatchId = calls.getItems().get(0).getBatchId();

        FindBroadcastCallsRequest getCallsRequest2 = FindBroadcastCallsRequest.create()
            .id(id.getId())
            .batchId(testBatchId)
            .build();
        calls = api.findCalls(getCallsRequest2);
        System.out.println(calls);
        assertThat(calls.getItems(), not(empty()));
        assertEquals(calls.getItems().get(0).getBatchId(), testBatchId);
    }

    @Test
    public void testAddRecipientsAndAddRemoveBatches() throws Exception {
        CallBroadcastsApi api = getCallfireClient().callBroadcastsApi();

        CallBroadcast broadcast = new CallBroadcast();
        broadcast.setName("testing add recipients");
        CallBroadcastSounds sounds = new CallBroadcastSounds();
        sounds.setLiveSoundText(getSoundText());
        sounds.setMachineSoundText(getSoundText());
        broadcast.setSounds(sounds);
        api.create(broadcast, false);

        FindCallBroadcastsRequest findRequest = FindCallBroadcastsRequest.create()
            .name("testing add recipients")
            .limit(1L)
            .build();
        Page<CallBroadcast> broadcasts = api.find(findRequest);
        System.out.println(broadcasts);
        assertThat(broadcasts.getItems(), not(empty()));
        Long id = broadcasts.getItems().get(0).getId();

        // add recipients
        List<Call> calls = api.addRecipients(id, makeRecipients());
        System.out.println(calls);
        assertEquals(2, calls.size());

        // add batch
        AddBatchRequest addBatchRequest = AddBatchRequest.create()
            .campaignId(id)
            .name("new_batch" + System.currentTimeMillis())
            .recipients(makeRecipients())
            .build();
        ResourceId newBatchId = api.addBatch(addBatchRequest);
        assertNotNull(newBatchId.getId());

        // get batches
        GetByIdRequest getBatchesRequest = GetByIdRequest.create()
            .id(id)
            .limit(1L)
            .build();
        Page<Batch> batches = api.getBatches(getBatchesRequest);
        System.out.println(batches);
        assertTrue(batches.getItems().size() == 1);
    }

    @Test
    public void testAddRecipientsAndAddBatchesWithRequest() throws Exception {
        CallBroadcastsApi api = getCallfireClient().callBroadcastsApi();

        FindCallBroadcastsRequest findRequest = FindCallBroadcastsRequest.create()
            .name("updated_name")
            .limit(1L)
            .build();
        Page<CallBroadcast> broadcasts = api.find(findRequest);
        System.out.println(broadcasts);
        assertThat(broadcasts.getItems(), not(empty()));
        Long id = broadcasts.getItems().get(0).getId();

        // add recipients
        AddRecipientsRequest<CallRecipient> request = AddRecipientsRequest.create()
            .campaignId(id)
            .recipients(makeRecipients())
            .strictValidation(true)
            .build();
        List<Call> calls = api.addRecipients(request);
        System.out.println(calls);
        assertEquals(2, calls.size());

        // add batch
        AddBatchRequest addBatchRequest = AddBatchRequest.create()
            .campaignId(id)
            .name("new_batch" + System.currentTimeMillis())
            .recipients(makeRecipients())
            .strictValidation(true)
            .build();
        ResourceId newBatchId = api.addBatch(addBatchRequest);
        assertNotNull(newBatchId.getId());

        // get batches
        GetByIdRequest getBatchesRequest = GetByIdRequest.create()
            .id(id)
            .limit(100L)
            .build();
        Page<Batch> batches = api.getBatches(getBatchesRequest);
        System.out.println(batches);
        assertTrue(batches.getItems().size() == 100);
    }

    @Test
    public void testToggleRecipientsStatus() throws Exception {
        CallBroadcastsApi api = getCallfireClient().callBroadcastsApi();
        CallBroadcast broadcast = new CallBroadcast();
        broadcast.setName("call_broadcast");
        broadcast.setRecipients(makeRecipients());
        CallBroadcastSounds sounds = new CallBroadcastSounds();
        sounds.setLiveSoundText(getSoundText());
        sounds.setMachineSoundText(getSoundText());
        broadcast.setSounds(sounds);
        CreateBroadcastRequest<CallBroadcast> brRequest = CreateBroadcastRequest.<CallBroadcast>create()
            .broadcast(broadcast)
            .build();
        ResourceId campaign = api.create(brRequest);
        assertNotNull(campaign.getId());

        FindBroadcastCallsRequest getCallsRequest = FindBroadcastCallsRequest.create()
                .id(campaign.getId())
                .build();
        Page<Call> callPage = api.findCalls(getCallsRequest);
        callPage.getItems().forEach(c -> assertThat(c.getState().name(), is(READY.name())));

        api.toggleRecipientsStatus(campaign.getId(), makeRecipients(), false);

        callPage = api.findCalls(getCallsRequest);
        assertThat(callPage.getTotalCount(), is(2L));
        callPage.getItems().forEach(c -> assertThat(c.getState().name(), is(DISABLED.name())));

        api.toggleRecipientsStatus(campaign.getId(), makeRecipients(), true);

        callPage = api.findCalls(getCallsRequest);
        assertThat(callPage.getTotalCount(), is(2L));
        callPage.getItems().forEach(c -> assertThat(c.getState().name(), is(READY.name())));
    }
}
