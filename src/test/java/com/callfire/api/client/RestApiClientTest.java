package com.callfire.api.client;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.auth.BasicAuth;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javax.activation.MimetypesFileTypeMap;

import static org.junit.Assert.assertEquals;

public class RestApiClientTest extends AbstractApiTest {
    private RestApiClient client = new RestApiClient(new BasicAuth("1", "2"));
    private String expectedJson = getJsonPayload("/common/sampleErrorMessage.json");

    @Before
    public void setUp() throws Exception {
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
    public void testMimeTypes() throws Exception {
        assertEquals("image/jpeg", MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType("/tmp/1.jpeg"));
        assertEquals("image/png", MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType("/tmp/2.png"));
        assertEquals("image/bmp", MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType("/tmp/3.bmp"));
        assertEquals("image/gif", MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType("/tmp/4.gif"));
        assertEquals("video/mp4", MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType("/tmp/5.mp4"));
        assertEquals("audio/m4a", MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType("/tmp/6.m4a"));
        assertEquals("audio/mp3", MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType("/tmp/7.mp3"));
        assertEquals("audio/x-wav", MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType("/tmp/8.wav"));
    }
}
