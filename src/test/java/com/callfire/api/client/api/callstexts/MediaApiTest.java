package com.callfire.api.client.api.callstexts;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.callstexts.model.Media;
import com.callfire.api.client.api.common.model.ResourceId;

public class MediaApiTest extends AbstractApiTest {

    @Test
    public void upload() throws Exception {
        String responseJson = getJsonPayload("/callstexts/mediaApi/response/upload.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);
        File file = new File(getClass().getClassLoader().getResource("file-examples/train.mp3").toURI());

        ResourceId id = client.mediaApi().upload(file, "fname");
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));
        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
    }

    @Test
    public void get() throws Exception {
        String expectedJson = getJsonPayload("/callstexts/mediaApi/response/get.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        Media media = client.mediaApi().get(11L, FIELDS);
        assertThat(jsonConverter.serialize(media), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));

        client.mediaApi().get(11L);
        assertEquals(2, captor.getAllValues().size());
        assertThat(captor.getAllValues().get(1).getURI().toString(), not(containsString("fields")));
    }

    @Test
    public void getNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.mediaApi().get(null);
    }

    @Test
    public void getDataNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.mediaApi().getData((Long) null, null);

        ex.expectMessage("type cannot be null");
        ex.expect(NullPointerException.class);
        client.mediaApi().getData(1L, null);
    }

    @Test
    public void getDataEmptyKey() throws Exception {
        ex.expectMessage("key cannot be blank");
        ex.expect(IllegalArgumentException.class);
        client.mediaApi().getData(" ", null);

        ex.expectMessage("type cannot be null");
        ex.expect(NullPointerException.class);
        client.mediaApi().getData("key", null);
    }
}
