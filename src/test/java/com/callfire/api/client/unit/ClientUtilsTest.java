package com.callfire.api.client.unit;

import com.callfire.api.client.ClientUtils;
import com.callfire.api.client.model.Call;
import com.callfire.api.client.model.request.FindCallsRequest;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ClientUtilsTest {

    @Test
    public void testBuildQueryParams() throws Exception {
        FindCallsRequest request = FindCallsRequest.FindCallsRequestBuilder.create()
            .setLimit(3L)
            .setBroadcastId(2L)
            .setResults(Arrays.asList(Call.FinalCallResult.AM, Call.FinalCallResult.BUSY))
            .build();
        List<NameValuePair> queryParams = ClientUtils.buildQueryParams(request);
        assertEquals(3, queryParams.size());
        assertThat(queryParams, hasItem(new BasicNameValuePair("results", "AM,BUSY")));
        assertThat(queryParams, hasItem(new BasicNameValuePair("broadcastId", Long.valueOf(2).toString())));
        assertThat(queryParams, hasItem(new BasicNameValuePair("limit", Long.valueOf(3).toString())));
    }
}
