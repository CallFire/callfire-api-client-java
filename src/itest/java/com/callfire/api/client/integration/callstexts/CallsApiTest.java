package com.callfire.api.client.integration.callstexts;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.callstexts.CallsApi;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.callstexts.model.CallRecipient;
import com.callfire.api.client.api.callstexts.model.request.FindCallsRequest;
import com.callfire.api.client.api.callstexts.model.request.SendCallsRequest;
import com.callfire.api.client.api.campaigns.model.CallRecording;
import com.callfire.api.client.api.campaigns.model.Voice;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.integration.AbstractIntegrationTest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * integration tests for /calls api endpoint
 */
public class CallsApiTest extends AbstractIntegrationTest {

    @Test
    public void testGetCall() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        Call call = callfireClient.callsApi().get(1L, "id,toNumber,state");

        assertEquals(Long.valueOf(1L), call.getId());
        assertEquals("18088395900", call.getToNumber());
        assertEquals(Call.State.FINISHED, call.getState());

        System.out.println(call);
    }

    @Test
    public void testFindCalls() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        FindCallsRequest request = FindCallsRequest.create()
            .intervalBegin(DateUtils.addMonths(new Date(), -10))
            .intervalEnd(new Date())
            .limit(1L)
            .build();
        Page<Call> calls = callfireClient.callsApi().find(request);
        System.out.println(calls);

        assertEquals(1, calls.getItems().size());
    }

    @Test
    public void testSendCall() throws Exception {
        CallfireClient client = getCallfireClient();

        CallRecipient recipient1 = new CallRecipient();
        recipient1.setLiveMessageSoundId(1L);
        recipient1.setPhoneNumber(getCallerId());
        recipient1.setTransferMessage("transferTestMessage");
        recipient1.setTransferNumber("14246525473");
        recipient1.setTransferDigit("1");
        CallRecipient recipient2 = new CallRecipient();
        recipient2.setLiveMessageSoundId(1L);
        recipient2.setPhoneNumber(getCallerId());
        recipient2.setTransferMessage("transferTestMessage");
        recipient2.setTransferDigit("2");
        recipient2.setTransferMessageSoundId(1L);
        recipient2.setFromNumber(getCallerId());

        List<Call> calls = client.callsApi()
            .send(asList(recipient1, recipient2), 7373471003L, "items(id,fromNumber,state)");

        System.out.println(calls);

        assertEquals(2, calls.size());
        assertNotNull(calls.get(0).getId());
        assertNull(calls.get(0).getCampaignId());
        assertEquals(Call.State.READY, calls.get(0).getState());

        SendCallsRequest request = SendCallsRequest.create()
            .recipients(asList(recipient1, recipient2))
            .defaultLiveMessage("defaultLive")
            .defaultMachineMessage("defaultMachine")
            .defaultVoice(Voice.FRENCHCANADIAN1)
            .strictValidation(true)
            .build();

        calls = client.callsApi().send(request);
        assertEquals(2, calls.size());

        request = SendCallsRequest.create()
            .recipients(asList(recipient1, recipient2))
            .defaultLiveMessageSoundId(1L)
            .defaultMachineMessageSoundId(1L)
            .defaultVoice(Voice.FRENCHCANADIAN1)
            .build();

        calls = client.callsApi().send(request);
        assertEquals(2, calls.size());
    }

    @Test
    public void testGetCallRecording() throws Exception {
        CallsApi api = getCallfireClient().callsApi();

        CallRecording rec = api.getCallRecording(1L);

        System.out.print(rec.toString());
        assertNotNull(rec);
        assertNotNull(rec.getId());
        assertNotNull(rec.getMp3Url());

        rec = api.getCallRecording(1L, "campaignId");
        assertNull(rec.getId());
    }

    @Test
    public void getCallRecordingInMp3Format() throws Exception {
        CallsApi api = getCallfireClient().callsApi();

        InputStream is = api.getCallRecordingMp3(1L);
        File tempFile = File.createTempFile("test_call_recording", "mp3");
        try (FileOutputStream os = new FileOutputStream(tempFile))
        {
            IOUtils.copy(is, os);
        }
    }

    @Test
    public void testGetCallRecordings() throws Exception {
        CallsApi api = getCallfireClient().callsApi();

        CallRecording rec = api.getCallRecording(1L);
        List<CallRecording> recs = api.getCallRecordings(rec.getCallId());
        assertNotNull(recs);
        assertEquals(rec.getCallId(), recs.get(0).getCallId());

        recs = api.getCallRecordings(rec.getCallId(), "items(callId)");
        assertNull(recs.get(0).getId());
        assertNotNull(recs.get(0).getCallId());
    }

    @Test
    public void testGetCallRecordingByName() throws Exception {
        CallsApi api = getCallfireClient().callsApi();

        CallRecording rec = api.getCallRecording(18666772003L);
        CallRecording recording = api.getCallRecordingByName(rec.getCallId(), rec.getName());
        assertNotNull(rec);
        assertEquals(recording.getCallId(), rec.getCallId());
        assertEquals(recording.getId(), rec.getId());
        assertEquals(recording.getName(), rec.getName());
        assertEquals(recording.getMp3Url(), rec.getMp3Url());

        recording = api.getCallRecordingByName(rec.getCallId(), rec.getName(), "callId");
        assertNull(recording.getId());
        assertNotNull(recording.getCallId());
    }

    @Test
    public void getCallRecordingMp3ByName() throws Exception {
        CallsApi api = getCallfireClient().callsApi();

        CallRecording rec = api.getCallRecording(18666772003L);
        InputStream is = api.getCallRecordingMp3ByName(rec.getCallId(), rec.getName());
        File tempFile = File.createTempFile("test_call_recording", "mp3");
        try (FileOutputStream os = new FileOutputStream(tempFile))
        {
            IOUtils.copy(is, os);
        }
    }
}
