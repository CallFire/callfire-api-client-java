package com.callfire.api.client.integration.campaigns;

import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.campaigns.VoiceBroadcastsApi;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.Broadcast;
import com.callfire.api.client.api.campaigns.model.VoiceBroadcast;
import com.callfire.api.client.api.campaigns.model.request.AddBatchRequest;
import com.callfire.api.client.api.campaigns.model.request.FindVoiceBroadcastsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.integration.AbstractIntegrationTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * integration tests for /campaigns/voice-broadcasts api endpoint
 */
public class VoiceBroadcastsApiTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testCrudOperations() throws Exception {
        VoiceBroadcastsApi api = getCallfireClient().voiceBroadcastsApi();
        VoiceBroadcast broadcast = new VoiceBroadcast();
        broadcast.setName("voice_broadcast");
        broadcast.setRecipients(makeRecipients());
        broadcast.setLiveSoundId(getLiveSoundId());
        broadcast.setMachineSoundId(getLiveSoundId());
        broadcast.setResumeNextDay(true);
        ResourceId id = api.create(broadcast, true);

        VoiceBroadcast savedBroadcast = api.get(id.getId());
        assertEquals(broadcast.getName(), savedBroadcast.getName());
        assertEquals(broadcast.getResumeNextDay(), true);
        // TODO vmikhailov there is no back mapping for recipients
        //        assertEquals(2, savedBroadcast.getRecipients().size());
        //        assertThat(savedBroadcast.getRecipients(),
        //            hasItem(Matchers.<Recipient>hasProperty("phoneNumber", startsWith("1213123456"))));

        savedBroadcast.setName("updated_name");
        api.update(savedBroadcast);

        VoiceBroadcast updatedBroadcast = api.get(id.getId(), "id,name");
        assertNull(updatedBroadcast.getStatus());
        assertNotNull(updatedBroadcast.getId());
        assertEquals(savedBroadcast.getName(), updatedBroadcast.getName());
    }

    @Test
    public void testStartStopArchiveCampaign() throws Exception {
        VoiceBroadcastsApi api = getCallfireClient().voiceBroadcastsApi();

        VoiceBroadcast broadcast = new VoiceBroadcast();
        broadcast.setName("voice_broadcast");
        broadcast.setRecipients(makeRecipients());
        broadcast.setLiveSoundId(getLiveSoundId());
        broadcast.setMachineSoundId(getLiveSoundId());
        ResourceId id = api.create(broadcast, false);

        VoiceBroadcast campaign = api.get(id.getId());
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
        VoiceBroadcastsApi api = getCallfireClient().voiceBroadcastsApi();

        VoiceBroadcast broadcast = new VoiceBroadcast();
        broadcast.setName("voice_broadcast");
        broadcast.setRecipients(makeRecipients());
        broadcast.setLiveSoundId(getLiveSoundId());
        broadcast.setMachineSoundId(getLiveSoundId());
        ResourceId id = api.create(broadcast, false);

        GetByIdRequest getCallsRequest = GetByIdRequest.create()
            .id(id.getId())
            .build();
        Page<Call> calls = api.getCalls(getCallsRequest);
        System.out.println(calls);
        assertThat(calls.getItems(), not(empty()));
    }

    @Test
    public void testAddRecipientsAndAddRemoveBatches() throws Exception {
        VoiceBroadcastsApi api = getCallfireClient().voiceBroadcastsApi();

        FindVoiceBroadcastsRequest findRequest = FindVoiceBroadcastsRequest.create()
            .name("updated_name")
            .limit(1L)
            .build();
        Page<VoiceBroadcast> broadcasts = api.find(findRequest);
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
        ResourceId batch = api.addBatch(addBatchRequest);
        assertNotNull(batch.getId());

        // get batches
        GetByIdRequest getBatchesRequest = GetByIdRequest.create()
            .id(id)
            .limit(100L)
            .build();
        Page<Batch> batches = api.getBatches(getBatchesRequest);
        System.out.println(batches);
        assertEquals(batches.getItems().size(), 100);
    }
}
