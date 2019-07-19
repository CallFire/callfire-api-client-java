package com.callfire.api.client;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.auth.BasicAuth;

public class RestApiClientTest extends AbstractApiTest {
    private RestApiClient client = new RestApiClient(new BasicAuth("1", "2"));
    private String expectedJson = getJsonPayload("/common/sampleErrorMessage.json");

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        client.setHttpClient(mockHttpClient);
    }

    @Test(expected = BadRequestException.class)
    public void testExpectBadRequestWhen400() throws Exception {
        mockHttpResponse(expectedJson, 400);
        client.delete("/");
    }

    @Test(expected = UnauthorizedException.class)
    public void testExpectUnauthorizedWhen401() throws Exception {
        mockHttpResponse(expectedJson, 401);
        client.delete("/");
    }

    @Test(expected = AccessForbiddenException.class)
    public void testExpectAccessForbiddenWhen403() throws Exception {
        mockHttpResponse(expectedJson, 403);
        client.delete("/");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testExpectResourceNotFoundWhen404() throws Exception {
        mockHttpResponse(expectedJson, 404);
        client.delete("/");
    }

    @Test(expected = InternalServerErrorException.class)
    public void testExpectInternalServerErrorWhen500() throws Exception {
        mockHttpResponse(expectedJson, 500);
        client.delete("/");
    }

    @Test(expected = CallfireApiException.class)
    public void testExpectCallfireApiExceptionInOtherCodeReturned() throws Exception {
        mockHttpResponse(expectedJson, 499);
        client.delete("/");
    }

    @Test
    public void testMimeTypes() {
        assertEquals("image/jpeg", RestApiClient.getContentType("/tmp/1.jpeg").getMimeType());
        assertEquals("image/png", RestApiClient.getContentType("/tmp/2.png").getMimeType());
        assertEquals("image/bmp", RestApiClient.getContentType("/tmp/3.bmp").getMimeType());
        assertEquals("image/gif", RestApiClient.getContentType("/tmp/4.gif").getMimeType());
        assertEquals("video/mp4", RestApiClient.getContentType("/tmp/5.mp4").getMimeType());
        assertEquals("audio/m4a", RestApiClient.getContentType("/tmp/6.m4a").getMimeType());
        assertEquals("audio/mp3", RestApiClient.getContentType("/tmp/7.mp3").getMimeType());
        assertEquals("audio/x-wav", RestApiClient.getContentType("/tmp/8.wav").getMimeType());
        assertEquals("application/octet-stream", RestApiClient.getContentType("/tmp/run.sh").getMimeType());
    }
}
