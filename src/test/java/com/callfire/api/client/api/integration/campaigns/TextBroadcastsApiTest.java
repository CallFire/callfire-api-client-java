package com.callfire.api.client.api.integration.campaigns;

import com.callfire.api.client.api.callstexts.model.Text;
import com.callfire.api.client.api.campaigns.TextBroadcastsApi;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.Broadcast;
import com.callfire.api.client.api.campaigns.model.TextBroadcast;
import com.callfire.api.client.api.campaigns.model.request.AddBatchRequest;
import com.callfire.api.client.api.campaigns.model.request.FindTextBroadcastsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.*;

/**
 * integration tests for /campaigns/text-broadcasts api endpoint
 */
public class TextBroadcastsApiTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testCrudOperations() throws Exception {
        TextBroadcastsApi api = getCallfireClient().textBroadcastsApi();
        TextBroadcast broadcast = new TextBroadcast();
        broadcast.setName("voice_broadcast");
        broadcast.setRecipients(makeTextRecipients());
        broadcast.setMessage("test_msg");
        ResourceId id = api.create(broadcast, true);

        TextBroadcast savedBroadcast = api.get(id.getId());
        assertEquals(broadcast.getName(), savedBroadcast.getName());
        // TODO vmikhailov there is no back mapping for recipients
        //        assertEquals(2, savedBroadcast.getRecipients().size());
        //        assertThat(savedBroadcast.getRecipients(),
        //            hasItem(Matchers.<Recipient>hasProperty("phoneNumber", startsWith("1213123456"))));

        savedBroadcast.setName("updated_name");
        api.update(savedBroadcast);

        TextBroadcast updatedBroadcast = api.get(id.getId(), "id,name");
        assertNull(updatedBroadcast.getStatus());
        assertNotNull(updatedBroadcast.getId());
        assertEquals(savedBroadcast.getName(), updatedBroadcast.getName());
    }

    @Test
    public void testStartStopArchiveCampaign() throws Exception {
        TextBroadcastsApi api = getCallfireClient().textBroadcastsApi();
        TextBroadcast campaign = api.get(getTextBroadcastId());
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
    public void testGetBroadcastTexts() throws Exception {
        TextBroadcastsApi api = getCallfireClient().textBroadcastsApi();

        GetByIdRequest getCallsRequest = GetByIdRequest.create()
            .id(getTextBroadcastId())
            .build();
        Page<Text> texts = api.getTexts(getCallsRequest);
        System.out.println(texts);
        assertThat(texts.getItems(), not(empty()));
    }

    @Test
    public void testAddRecipientsAndAddRemoveBatches() throws Exception {
        TextBroadcastsApi api = getCallfireClient().textBroadcastsApi();

        FindTextBroadcastsRequest findRequest = FindTextBroadcastsRequest.create()
            .name("updated_name")
            .limit(1L)
            .build();
        Page<TextBroadcast> broadcasts = api.find(findRequest);
        System.out.println(broadcasts);
        assertThat(broadcasts.getItems(), not(empty()));
        Long id = broadcasts.getItems().get(0).getId();

        // add recipients
        List<Text> texts = api.addRecipients(id, makeTextRecipients());
        System.out.println(texts);
        assertEquals(2, texts.size());
        assertThat(texts.get(0).getMessage(), startsWith("msg"));

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
        ResourceId resourceId = api.addBatch(addBatchRequest);

        Page<Batch> updatedBatches = api.getBatches(getBatchesRequest);
        System.out.println(batches);
        assertEquals(batches.getItems().size() + 1, updatedBatches.getItems().size());

        GetByIdRequest getBatchRequest = GetByIdRequest.create().id(resourceId.getId()).build();
        Batch savedBatch = api.getBatch(getBatchRequest);
        assertTrue(savedBatch.getEnabled());
        assertEquals(addBatchRequest.getName(), savedBatch.getName());

        savedBatch.setEnabled(false);
        api.updateBatch(savedBatch);
        Batch updatedBatch = api.getBatch(getBatchRequest);
        assertFalse(updatedBatch.getEnabled());
    }
}
