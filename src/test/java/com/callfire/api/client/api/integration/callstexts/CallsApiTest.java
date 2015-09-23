package com.callfire.api.client.api.integration.callstexts;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.callstexts.model.request.FindCallsRequest;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * integration tests for /calls api endpoint
 */
public class CallsApiTest extends AbstractIntegrationTest {

    @Test
    public void testGetCall() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        Call call = callfireClient.getCallsApi().getCall(593409674003L, "id,toNumber,state");

        assertEquals(Long.valueOf(593409674003L), call.getId());
        assertEquals("12132212385", call.getToNumber());
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
        Page<Call> calls = callfireClient.getCallsApi().findCalls(request);

        assertEquals(3, calls.getItems().size());

        System.out.println(calls);
    }
}
