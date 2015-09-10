package com.callfire.api.client.unit;

import com.callfire.api.client.ClientUtils;
import com.callfire.api.client.model.request.FindAgentGroupsRequest;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ClientUtilsTest {

    @Test
    public void testBuildQueryParams() throws Exception {
        FindAgentGroupsRequest request = FindAgentGroupsRequest.FindAgentGroupsRequestBuilder.create()
            .setAgentEmail("mail")
            .setAgentId(1L)
            .setCampaignId(2L)
            .setLimit(3L)
            .build();
        List<NameValuePair> queryParams = ClientUtils.buildQueryParams(request);
        assertEquals(4, queryParams.size());
        assertThat(queryParams, hasItem(new BasicNameValuePair("agentEmail", "mail")));
        assertThat(queryParams, hasItem(new BasicNameValuePair("agentId", Long.valueOf(1).toString())));
        assertThat(queryParams, hasItem(new BasicNameValuePair("campaignId", Long.valueOf(2).toString())));
        assertThat(queryParams, hasItem(new BasicNameValuePair("limit", Long.valueOf(3).toString())));
    }
}
