package com.callfire.api.client.integration.endpoint;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.model.Call;
import com.callfire.api.client.model.Page;
import com.callfire.api.client.model.request.FindCallsRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * integration tests for /calls api endpoint
 */
public class CallsEndpointTest extends AbstractIntegrationTest {

    @Test
    public void testGetCall() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        Call call = callfireClient.getCallsEndpoint().getCall(593409674003L, "id,toNumber,state");

        assertEquals(Long.valueOf(593409674003L), call.getId());
        assertEquals("12132212385", call.getToNumber());
        assertEquals(Call.State.FINISHED, call.getState());

        System.out.println(call);
    }

    @Test
    public void testFindCalls() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        FindCallsRequest request = FindCallsRequest.create()
            .setStates(Arrays.asList(Call.State.FINISHED, Call.State.READY))
            .setIntervalBegin(DateUtils.addMonths(new Date(), -2))
            .setIntervalEnd(new Date())
            .setLimit(3L)
            .build();
        Page<Call> calls = callfireClient.getCallsEndpoint().findCalls(request);

        assertEquals(3, calls.getItems().size());

        System.out.println(calls);
    }
}
