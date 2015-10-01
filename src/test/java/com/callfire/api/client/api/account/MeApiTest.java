package com.callfire.api.client.api.account;

import com.callfire.api.client.JsonConverter;
import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.account.model.Account;
import com.callfire.api.client.api.account.model.BillingPlanUsage;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class MeApiTest extends AbstractApiTest {

    @Spy
    private HttpClient mockHttpClient = client.getRestApiClient().getHttpClient();
    @Mock
    private CloseableHttpResponse mockHttpResponse;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        client.getRestApiClient().setHttpClient(mockHttpClient);
    }

    @Test
    public void testGetAccount() throws Exception {
        String responseJson = getJsonPayload("/responses/account/meApi/getAccount.json");

        when(mockHttpResponse.getStatusLine()).thenReturn(getStatus200_Ok());
        when(mockHttpResponse.getEntity()).thenReturn(EntityBuilder.create().setText(responseJson).build());
        doReturn(mockHttpResponse).when(mockHttpClient).execute(any(HttpUriRequest.class));

        Account account = client.meApi().getAccount();
        String jsonString = new JsonConverter().serialize(account);

        assertThat(jsonString, equalToIgnoringWhiteSpace(responseJson));
    }

    @Test
    public void testGetBillingPlanUsage() throws Exception {
        String responseJson = getJsonPayload("/responses/account/meApi/getBillingPlanUsage.json");

        when(mockHttpResponse.getStatusLine()).thenReturn(getStatus200_Ok());
        when(mockHttpResponse.getEntity()).thenReturn(EntityBuilder.create().setText(responseJson).build());
        doReturn(mockHttpResponse).when(mockHttpClient).execute(any(HttpUriRequest.class));

        BillingPlanUsage billingPlanUsage = client.meApi().getBillingPlanUsage();
        String jsonString = new JsonConverter().serialize(billingPlanUsage);

        assertThat(jsonString, equalToIgnoringWhiteSpace(responseJson));
    }
}
