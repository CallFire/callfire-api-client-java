package com.callfire.api.client.api.integration.callstexts;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.callstexts.model.CallRecipient;
import com.callfire.api.client.api.callstexts.model.request.FindCallsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
            .states(Arrays.asList(Call.State.FINISHED, Call.State.READY))
            .intervalBegin(DateUtils.addMonths(new Date(), -2))
            .intervalEnd(new Date())
            .limit(3L)
            .build();
        Page<Call> calls = callfireClient.callsApi().find(request);
        System.out.println(calls);

        assertEquals(3, calls.getItems().size());
    }

    @Test
    public void testSendCall() throws Exception {
        CallfireClient client = getCallfireClient();

        CallRecipient recipient1 = new CallRecipient();
        recipient1.setLiveMessageSoundId(1L);
        recipient1.setPhoneNumber(getCallerId());
        CallRecipient recipient2 = new CallRecipient();
        recipient2.setLiveMessageSoundId(1L);
        recipient2.setPhoneNumber(getCallerId());
        List<Call> calls = client.callsApi()
            .send(asList(recipient1, recipient2), null, "items(id,fromNumber,state)");

        System.out.println(calls);

        assertEquals(2, calls.size());
        assertNotNull(calls.get(0).getId());
        assertNull(calls.get(0).getCampaignId());
        assertEquals(Call.State.READY, calls.get(0).getState());
    }
}
