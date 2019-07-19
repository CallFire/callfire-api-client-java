package com.callfire.api.client.integration.campaigns;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.callfire.api.client.api.callstexts.model.Text;
import com.callfire.api.client.api.campaigns.TextBroadcastsApi;
import com.callfire.api.client.api.campaigns.model.Batch;
import com.callfire.api.client.api.campaigns.model.Broadcast;
import com.callfire.api.client.api.campaigns.model.TextBroadcast;
import com.callfire.api.client.api.campaigns.model.TextBroadcastStats;
import com.callfire.api.client.api.campaigns.model.TextRecipient;
import com.callfire.api.client.api.campaigns.model.request.AddBatchRequest;
import com.callfire.api.client.api.campaigns.model.request.AddRecipientsRequest;
import com.callfire.api.client.api.campaigns.model.request.CreateBroadcastRequest;
import com.callfire.api.client.api.campaigns.model.request.FindBroadcastTextsRequest;
import com.callfire.api.client.api.campaigns.model.request.FindTextBroadcastsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.integration.AbstractIntegrationTest;


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
        broadcast.setName("text_broadcast");
        broadcast.setRecipients(makeTextRecipients());
        broadcast.setMessage("test_msg");
        broadcast.setResumeNextDay(true);
        ResourceId id = api.create(broadcast, true);

        TextBroadcast savedBroadcast = api.get(id.getId());
        assertEquals(broadcast.getName(), savedBroadcast.getName());
        assertEquals(broadcast.getResumeNextDay(), true);
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
    public void testCrudOperationsWithRequest() throws Exception {
        TextBroadcastsApi api = getCallfireClient().textBroadcastsApi();
        TextBroadcast broadcast = new TextBroadcast();
        broadcast.setName("text_broadcast");
        broadcast.setRecipients(makeTextRecipients());
        broadcast.setMessage("test_msg");
        broadcast.setResumeNextDay(true);

        CreateBroadcastRequest<TextBroadcast> request = CreateBroadcastRequest.<TextBroadcast>create()
            .broadcast(broadcast)
            .start(true)
            .strictValidation(true)
            .build();

        ResourceId id = api.create(request);

        TextBroadcast savedBroadcast = api.get(id.getId());
        assertEquals(broadcast.getName(), savedBroadcast.getName());
        assertEquals(broadcast.getResumeNextDay(), true);
        // TODO vmikhailov there is no back mapping for recipients
        //        assertEquals(2, savedBroadcast.getRecipients().size());
        //        assertThat(savedBroadcast.getRecipients(),
        //            hasItem(Matchers.<Recipient>hasProperty("phoneNumber", startsWith("1213123456"))));

        savedBroadcast.setName("updated_name");
        api.update(savedBroadcast, true);

        TextBroadcast updatedBroadcast = api.get(id.getId(), "id,name");
        assertNull(updatedBroadcast.getStatus());
        assertNotNull(updatedBroadcast.getId());
        assertEquals(savedBroadcast.getName(), updatedBroadcast.getName());
    }

    @Test
    public void testStartStopArchiveCampaign() throws Exception {
        TextBroadcastsApi api = getCallfireClient().textBroadcastsApi();

        TextBroadcast broadcast = new TextBroadcast();
        broadcast.setName("text_broadcast");
        broadcast.setRecipients(makeTextRecipients());
        broadcast.setMessage("test_msg");
        ResourceId id = api.create(broadcast, false);

        TextBroadcast campaign = api.get(id.getId());
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

        TextBroadcast broadcast = new TextBroadcast();
        broadcast.setName("text_broadcast_1");
        broadcast.setRecipients(makeTextRecipients());
        broadcast.setMessage("test_msg");
        ResourceId id = api.create(broadcast, false);

        GetByIdRequest getCallsRequest = GetByIdRequest.create()
            .id(id.getId())
            .build();
        Page<Text> texts = api.getTexts(getCallsRequest);
        System.out.println(texts);
        assertThat(texts.getItems(), not(empty()));

        Long testBatchId = texts.getItems().get(0).getBatchId();

        FindBroadcastTextsRequest getCallsRequest2 = FindBroadcastTextsRequest.create()
            .id(id.getId())
            .batchId(testBatchId)
            .build();
        texts = api.findTexts(getCallsRequest2);
        System.out.println(texts);
        assertThat(texts.getItems(), not(empty()));
        assertEquals(texts.getItems().get(0).getBatchId(), testBatchId);
    }

    @Test
    public void testBroadcastStats() throws Exception {
        TextBroadcastsApi api = getCallfireClient().textBroadcastsApi();

        TextBroadcast broadcast = new TextBroadcast();
        broadcast.setName("text_broadcast");
        broadcast.setRecipients(makeTextRecipients());
        broadcast.setMessage("test_msg");
        ResourceId id = api.create(broadcast, false);

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.add(Calendar.DATE, -5);
        TextBroadcastStats stats = api.getStats(id.getId(), "TotalOutboundCount,remainingOutboundCount", start.getTime(), end.getTime());
        System.out.println(stats);
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

        // add batch
        AddBatchRequest addBatchRequest = AddBatchRequest.create()
            .campaignId(id)
            .name("new_batch" + System.currentTimeMillis())
            .recipients(makeRecipients())
            .build();
        ResourceId resourceId = api.addBatch(addBatchRequest);
        assertNotNull(resourceId.getId());
        // get batches
        GetByIdRequest getBatchesRequest = GetByIdRequest.create()
            .id(id)
            .limit(100L)
            .build();
        Page<Batch> batches = api.getBatches(getBatchesRequest);
        System.out.println(batches);
        assertEquals(batches.getItems().size(), 100);

        Batch savedBatch = getCallfireClient().batchesApi().get(resourceId.getId());
        assertTrue(savedBatch.getEnabled());
        assertEquals(addBatchRequest.getName(), savedBatch.getName());

        savedBatch.setEnabled(false);
        getCallfireClient().batchesApi().update(savedBatch);
        Batch updatedBatch = getCallfireClient().batchesApi().get(resourceId.getId());
        assertFalse(updatedBatch.getEnabled());
    }

    @Test
    public void testAddRecipientsAndAddRemoveBatchesWithRequest() throws Exception {
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
        AddRecipientsRequest<TextRecipient> request = AddRecipientsRequest.create()
            .campaignId(id)
            .recipients(makeTextRecipients())
            .strictValidation(true)
            .build();

        List<Text> texts = api.addRecipients(request);
        System.out.println(texts);
        assertEquals(2, texts.size());
        assertThat(texts.get(0).getMessage(), startsWith("msg"));

        // add batch
        AddBatchRequest addBatchRequest = AddBatchRequest.create()
            .campaignId(id)
            .name("new_batch" + System.currentTimeMillis())
            .recipients(makeRecipients())
            .strictValidation(true)
            .build();
        ResourceId resourceId = api.addBatch(addBatchRequest);
        assertNotNull(resourceId.getId());
        // get batches
        GetByIdRequest getBatchesRequest = GetByIdRequest.create()
            .id(id)
            .limit(100L)
            .build();
        Page<Batch> batches = api.getBatches(getBatchesRequest);
        System.out.println(batches);
        assertEquals(batches.getItems().size(), 100);

        Batch savedBatch = getCallfireClient().batchesApi().get(resourceId.getId());
        assertTrue(savedBatch.getEnabled());
        assertEquals(addBatchRequest.getName(), savedBatch.getName());

        savedBatch.setEnabled(false);
        getCallfireClient().batchesApi().update(savedBatch);
        Batch updatedBatch = getCallfireClient().batchesApi().get(resourceId.getId());
        assertFalse(updatedBatch.getEnabled());
    }
}
