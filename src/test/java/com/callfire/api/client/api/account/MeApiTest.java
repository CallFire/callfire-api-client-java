package com.callfire.api.client.api.account;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.account.model.Account;
import com.callfire.api.client.api.account.model.ApiCredentials;
import com.callfire.api.client.api.account.model.BillingPlanUsage;
import com.callfire.api.client.api.account.model.CallerId;
import com.callfire.api.client.api.account.model.request.CallerIdVerificationRequest;
import com.callfire.api.client.api.common.model.ListHolder;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.request.CommonFindRequest;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
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
        String expectedJson = getJsonPayload(BASE_PATH + "/account/meApi/response/getAccount.json");
        mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        Account account = client.meApi().getAccount();
        assertThat(jsonConverter.serialize(account), equalToIgnoringWhiteSpace(expectedJson));
    }

    @Test
    public void testGetBillingPlanUsage() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + "/account/meApi/response/getBillingPlanUsage.json");
        mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        BillingPlanUsage billingPlanUsage = client.meApi().getBillingPlanUsage();
        assertThat(jsonConverter.serialize(billingPlanUsage), equalToIgnoringWhiteSpace(expectedJson));
    }

    @Test
    public void testGetCallerIds() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + "/account/meApi/response/getCallerIds.json");
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
        String expectedJson = getJsonPayload(BASE_PATH + "/account/meApi/response/verifyCallerId.json");
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

    @Test
    public void testCreateApiCredentials() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + "/account/meApi/response/createApiCredentials.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        ApiCredentials credentials = new ApiCredentials("test_name");
        ApiCredentials apiCredentials = client.meApi().createApiCredentials(credentials);
        assertThat(jsonConverter.serialize(apiCredentials), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertEquals(jsonConverter.serialize(credentials), extractHttpEntity(arg));
    }

    @Test
    public void testFindApiCredentials() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + "/account/meApi/response/findApiCredentials.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        CommonFindRequest request = CommonFindRequest.create()
            .limit(1L)
            .offset(5L)
            .build();
        Page<ApiCredentials> apiCredentials = client.meApi().findApiCredentials(request);
        assertThat(jsonConverter.serialize(apiCredentials), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("limit=1"));
        assertThat(arg.getURI().toString(), containsString("offset=5"));
    }

    @Test
    public void testGetApiCredentials() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + "/account/meApi/response/getApiCredentials.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson);

        ApiCredentials apiCredentials = client.meApi().getApiCredentials(11L, FIELDS);
        assertThat(jsonConverter.serialize(apiCredentials), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));

        client.meApi().getApiCredentials(11L);
        assertEquals(2, captor.getAllValues().size());
        assertThat(captor.getAllValues().get(1).getURI().toString(), not(containsString("fields")));
    }

    @Test
    public void testDeleteCredentials() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(mockHttpClient, mockHttpResponse);

        client.meApi().deleteApiCredentials(11L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/11"));
    }

}
