package com.callfire.api.client;

import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.callstexts.model.request.FindCallsRequest;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.api.contacts.model.request.FindContactsRequest;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
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
    public void testBuildQueryParamsWithList() throws Exception {
        FindContactsRequest request = FindContactsRequest.create()
            .number(asList("16506190257", "18778973473"))
            .id(asList(413858626003L, 425456525003L))
            .build();
        List<NameValuePair> queryParams = ClientUtils.buildQueryParams(request);
        System.out.println(queryParams);

        assertEquals(4, queryParams.size());
        assertThat(queryParams, hasItem(new BasicNameValuePair("number", "16506190257")));
        assertThat(queryParams, hasItem(new BasicNameValuePair("number", "18778973473")));
        assertThat(queryParams, hasItem(new BasicNameValuePair("id", Long.valueOf(413858626003L).toString())));
        assertThat(queryParams, hasItem(new BasicNameValuePair("id", Long.valueOf(425456525003L).toString())));
    }

    @Test
    public void testBuildQueryParamsWithIgnore() throws Exception {
        GetByIdRequest request = GetByIdRequest.create()
            .id(2L)
            .limit(10L)
            .build();

        List<NameValuePair> queryParams = ClientUtils.buildQueryParams(request);
        assertEquals(1, queryParams.size());
        assertThat(queryParams, hasItem(new BasicNameValuePair("limit", Long.valueOf(10).toString())));
    }

}
