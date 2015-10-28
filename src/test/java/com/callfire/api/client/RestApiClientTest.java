package com.callfire.api.client;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.auth.BasicAuth;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class RestApiClientTest extends AbstractApiTest {
    private RestApiClient client = new RestApiClient(new BasicAuth("1", "2"));
    private String expectedJson = getJsonPayload(BASE_PATH + "/common/sampleErrorMessage.json");

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        client.setHttpClient(mockHttpClient);
    }

    @Test(expected = BadRequestException.class)
    public void testExpectBadRequestWhen400() throws Exception {
        mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson, 400);
        client.delete("/");
    }

    @Test(expected = UnauthorizedException.class)
    public void testExpectUnauthorizedWhen401() throws Exception {
        mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson, 401);
        client.delete("/");
    }

    @Test(expected = AccessForbiddenException.class)
    public void testExpectAccessForbiddenWhen403() throws Exception {
        mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson, 403);
        client.delete("/");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testExpectResourceNotFoundWhen404() throws Exception {
        mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson, 404);
        client.delete("/");
    }

    @Test(expected = InternalServerErrorException.class)
    public void testExpectInternalServerErrorWhen500() throws Exception {
        mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson, 500);
        client.delete("/");
    }

    @Test(expected = CallfireApiException.class)
    public void testExpectCallfireApiExceptionInOtherCodeReturned() throws Exception {
        mockHttpResponse(mockHttpClient, mockHttpResponse, expectedJson, 499);
        client.delete("/");
    }
}
