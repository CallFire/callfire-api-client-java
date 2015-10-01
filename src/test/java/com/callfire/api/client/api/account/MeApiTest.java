package com.callfire.api.client.api.account;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.account.model.Account;
import com.callfire.api.client.api.account.model.BillingPlanUsage;
import com.callfire.api.client.api.account.model.CallerId;
import com.callfire.api.client.api.account.model.request.CallerIdVerificationRequest;
import com.callfire.api.client.api.common.model.ListHolder;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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
        String expectedJson = getJsonPayload("/responses/account/meApi/getAccount.json");
        mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        Account account = client.meApi().getAccount();
        assertThat(jsonConverter.serialize(account), equalToIgnoringWhiteSpace(expectedJson));
    }

    @Test
    public void testGetBillingPlanUsage() throws Exception {
        String expectedJson = getJsonPayload("/responses/account/meApi/getBillingPlanUsage.json");
        mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        BillingPlanUsage billingPlanUsage = client.meApi().getBillingPlanUsage();
        assertThat(jsonConverter.serialize(billingPlanUsage), equalToIgnoringWhiteSpace(expectedJson));
    }

    @Test
    public void testGetCallerIds() throws Exception {
        String expectedJson = getJsonPayload("/responses/account/meApi/getCallerIds.json");
        mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        List<CallerId> callerIds = client.meApi().getCallerIds();
        assertThat(jsonConverter.serialize(new ListHolder<>(callerIds)), equalToIgnoringWhiteSpace(expectedJson));
    }

    @Test
    public void testSendVerificationCode() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);
        String callerId = "1234567890";

        client.meApi().sendVerificationCode(callerId);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString(callerId));
        assertNull(extractHttpEntity(arg));
    }

    @Test
    public void testVerifyCallerId() throws Exception {
        String expectedJson = getJsonPayload("/responses/account/meApi/verifyCallerId.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        CallerIdVerificationRequest request = CallerIdVerificationRequest.create()
            .callerId("1234567890")
            .verificationCode("0987654321")
            .build();
        Boolean verified = client.meApi().verifyCallerId(request);
        assertThat(jsonConverter.serialize(verified), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertEquals(jsonConverter.serialize(request), extractHttpEntity(arg));
        assertEquals(2, arg.getAllHeaders().length);
        assertNotNull(arg.getFirstHeader(HttpHeaders.AUTHORIZATION).getValue());
        assertEquals(APPLICATION_JSON.getMimeType(), arg.getFirstHeader(HttpHeaders.CONTENT_TYPE).getValue());
        assertThat(arg.getURI().toString(), containsString(request.getCallerId()));
    }
}
