package com.callfire.api.client.integration.callstexts;

import com.callfire.api.client.BadRequestException;
import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.callstexts.model.Media;
import com.callfire.api.client.api.callstexts.model.MediaType;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.integration.AbstractIntegrationTest;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * integration tests for /media api endpoint
 */
public class MediaApiTest extends AbstractIntegrationTest {

    @Test

    public void testUpload() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        File mp3File = new File(getClass().getClassLoader().getResource("file-examples/train1.mp3").toURI());
        File wavFile = new File(getClass().getClassLoader().getResource("file-examples/train1.wav").toURI());

        ResourceId wavResourceId = callfireClient.mediaApi().upload(wavFile);
        ResourceId mp3ResourceId = callfireClient.mediaApi().upload(mp3File, createMp3FileName());

        assertNotNull(wavResourceId.getId());
        assertNotNull(mp3ResourceId.getId());
    }

    @Test
    public void testGet() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        File mp3File = new File(getClass().getClassLoader().getResource("file-examples/train1.mp3").toURI());

        ResourceId mp3ResourceId;
        try
        {
            mp3ResourceId = callfireClient.mediaApi().upload(mp3File, createMp3FileName());
        }
        catch (BadRequestException e)
        {
            mp3ResourceId = new ResourceId();
            mp3ResourceId.setId(selectIdFromBadRequestErrorString(e.getApiErrorMessage().getMessage()));
        }

        Media media = callfireClient.mediaApi().get(mp3ResourceId.getId());

        assertNotNull(media);
        assertTrue(media.getName().contains("mp3_test_"));
        assertEquals(media.getId(), mp3ResourceId.getId());
        assertEquals(media.getMediaType(), MediaType.MP3);
        assertNotNull(media.getLengthInBytes());
        assertNotNull(media.getCreated());
        assertNotNull(media.getPublicUrl());

        media = callfireClient.mediaApi().get(mp3ResourceId.getId(), "id,created");
        assertNull(media.getName());
        assertNull(media.getLengthInBytes());
        assertNull(media.getPublicUrl());
        assertNull(media.getMediaType());
    }

    @Test
    public void testGetDataById() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        File mp3File = new File(getClass().getClassLoader().getResource("file-examples/train1.mp3").toURI());

        ResourceId mp3ResourceId;
        try
        {
            mp3ResourceId = callfireClient.mediaApi().upload(mp3File, createMp3FileName());
        }
        catch (BadRequestException e)
        {
            mp3ResourceId = new ResourceId();
            mp3ResourceId.setId(selectIdFromBadRequestErrorString(e.getApiErrorMessage().getMessage()));
        }
        InputStream is;
        try
        {
            is = callfireClient.mediaApi().getData(mp3ResourceId.getId(), MediaType.MP3);
            File tempFile = File.createTempFile("mp3_sound", "mp3");
            try (FileOutputStream os = new FileOutputStream(tempFile))
            {
                IOUtils.copy(is, os);
            }
        }
        catch (CallfireApiException e)
        {
            assertTrue(e.getApiErrorMessage().getMessage().contains("file currently unavailable for mediaId:"));
        }

    }

    @Test
    public void testGetDataByKey() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        File mp3File = new File(getClass().getClassLoader().getResource("file-examples/train1.mp3").toURI());

        ResourceId mp3ResourceId;
        try
        {
            mp3ResourceId = callfireClient.mediaApi().upload(mp3File, createMp3FileName());
        }
        catch (BadRequestException e)
        {
            mp3ResourceId = new ResourceId();
            mp3ResourceId.setId(selectIdFromBadRequestErrorString(e.getApiErrorMessage().getMessage()));
        }

        Media media = callfireClient.mediaApi().get(mp3ResourceId.getId());
        InputStream is;
        try
        {
            is = callfireClient.mediaApi().getData(selectHashPartFromUrlString(media.getPublicUrl()), MediaType.MP3);
            File tempFile = File.createTempFile("mp3_sound", "mp3");
            try (FileOutputStream os = new FileOutputStream(tempFile))
            {
                IOUtils.copy(is, os);
            }
        }
        catch (CallfireApiException e)
        {
            assertTrue(e.getApiErrorMessage().getMessage().contains("file currently unavailable for mediaId:"));
        }
    }

    private static String createMp3FileName() {
        long timestamp = new Date().getTime();
        return"mp3_test_" + timestamp;
    }

    private static long selectIdFromBadRequestErrorString(String message){
        int mediaIdTextStartedAt = message.indexOf("mediaId:");
        int from = mediaIdTextStartedAt + 9;
        return Long.parseLong(message.substring(from, message.length()));
    }

    private static String selectHashPartFromUrlString(String message){
        int hashStartedAt = message.indexOf("public/") + 7;
        int hashFinishedAt = message.lastIndexOf(".");
        String res = message.substring(hashStartedAt, hashFinishedAt);
        return res;
    }
}
