package com.callfire.api.client.api.account;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.account.model.*;
import com.callfire.api.client.api.account.model.request.CallerIdVerificationRequest;
import com.callfire.api.client.api.account.model.request.DateIntervalRequest;
import com.callfire.api.client.api.common.model.ListHolder;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.request.CommonFindRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Date;
import java.util.List;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class MeApiTest extends AbstractApiTest {

    @Test
    public void testGetAccount() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + "/account/meApi/response/getAccount.json");
        mockHttpResponse(expectedJson);

        Account account = client.meApi().getAccount();
        assertThat(jsonConverter.serialize(account), equalToIgnoringWhiteSpace(expectedJson));
    }

    @Test
    public void testGetBillingPlanUsage() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + "/account/meApi/response/getBillingPlanUsage.json");
        mockHttpResponse(expectedJson);

        BillingPlanUsage billingPlanUsage = client.meApi().getBillingPlanUsage();
        assertThat(jsonConverter.serialize(billingPlanUsage), equalToIgnoringWhiteSpace(expectedJson));
    }

    @Test
    public void testGetCreditUsage() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + "/account/meApi/response/getCreditsUsage.json");

        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        DateIntervalRequest request = DateIntervalRequest.create()
            .intervalBegin(DateUtils.addMonths(new Date(), -2))
            .intervalEnd(new Date())
            .build();
        CreditsUsage creditUsage = client.meApi().getCreditUsage(request);
        assertThat(jsonConverter.serialize(creditUsage), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertThat(arg.getURI().toString(), containsString(String.valueOf(request.getIntervalBegin().getTime())));
        assertThat(arg.getURI().toString(), containsString(String.valueOf(request.getIntervalEnd().getTime())));
    }

    @Test
    public void testGetCallerIds() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + "/account/meApi/response/getCallerIds.json");
        mockHttpResponse(expectedJson);

        List<CallerId> callerIds = client.meApi().getCallerIds();
        assertThat(jsonConverter.serialize(new ListHolder<>(callerIds)), equalToIgnoringWhiteSpace(expectedJson));
    }

    @Test
    public void testSendVerificationCode() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();
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
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

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
        String responseJson = getJsonPayload(BASE_PATH + "/account/meApi/response/createApiCredentials.json");
        String requestJson = getJsonPayload(BASE_PATH + "/account/meApi/request/createApiCredentials.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        ApiCredentials credentials = new ApiCredentials("api_20_account");
        ApiCredentials apiCredentials = client.meApi().createApiCredentials(credentials);
        assertThat(jsonConverter.serialize(apiCredentials), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testFindApiCredentials() throws Exception {
        String expectedJson = getJsonPayload(BASE_PATH + "/account/meApi/response/findApiCredentials.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

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
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

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
    public void testDeleteApiCredentials() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        client.meApi().deleteApiCredentials(11L);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/11"));
    }
}
