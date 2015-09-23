package com.callfire.api.client;

import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.callstexts.model.request.FindCallsRequest;
import com.callfire.api.client.api.contacts.model.request.GetContactListItemsRequest;
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
        FindCallsRequest request = FindCallsRequest.create()
            .limit(3L)
            .campaignId(2L)
            .results(Arrays.asList(Call.FinalCallResult.AM, Call.FinalCallResult.BUSY))
            .build();
        List<NameValuePair> queryParams = ClientUtils.buildQueryParams(request);
        assertEquals(3, queryParams.size());
        assertThat(queryParams, hasItem(new BasicNameValuePair("results", "AM,BUSY")));
        assertThat(queryParams, hasItem(new BasicNameValuePair("campaignId", Long.valueOf(2).toString())));
        assertThat(queryParams, hasItem(new BasicNameValuePair("limit", Long.valueOf(3).toString())));
    }

    @Test
    public void testBuildQueryParamsWithIgnore() throws Exception {
        GetContactListItemsRequest request = GetContactListItemsRequest.create()
            .id(2L)
            .limit(10L)
            .build();

        List<NameValuePair> queryParams = ClientUtils.buildQueryParams(request);
        assertEquals(1, queryParams.size());
        assertThat(queryParams, hasItem(new BasicNameValuePair("limit", Long.valueOf(10).toString())));
    }
}
