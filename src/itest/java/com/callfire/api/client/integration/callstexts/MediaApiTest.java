package com.callfire.api.client.integration.callstexts;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Date;

import org.junit.Test;

import com.callfire.api.client.BadRequestException;
import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.callstexts.model.Media;
import com.callfire.api.client.api.callstexts.model.MediaType;
import com.callfire.api.client.api.callstexts.model.request.FindMediaRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.integration.AbstractIntegrationTest;

/**
 * integration tests for /media api endpoint
 */
public class MediaApiTest extends AbstractIntegrationTest {
    public static final String TRAIN_MP3 = "file-examples/train1.mp3";
    public static final String TRAIN_WAV = "file-examples/train1.wav";

    @Test
    public void testFind()  throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        File cfLogoFile = new File(getClass().getClassLoader().getResource("file-examples/cf.png").toURI());
        File ezLogoFile = new File(getClass().getClassLoader().getResource("file-examples/ez.png").toURI());

        ResourceId cfResourceId = callfireClient.mediaApi().upload(ezLogoFile);
        ResourceId ezResourceId = callfireClient.mediaApi().upload(cfLogoFile);

        assertNotNull(cfResourceId.getId());
        assertNotNull(ezResourceId.getId());

        Page<Media> mediaPage = callfireClient.mediaApi().find(FindMediaRequest.create().filter("cf.png").build());
        assertEquals(1, mediaPage.getItems().size());
    }

    @Test
    public void testUpload() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        File mp3File = new File(getClass().getClassLoader().getResource(TRAIN_MP3).toURI());
        File wavFile = new File(getClass().getClassLoader().getResource(TRAIN_WAV).toURI());

        ResourceId wavResourceId = callfireClient.mediaApi().upload(wavFile);
        ResourceId mp3ResourceId = callfireClient.mediaApi().upload(mp3File, createMp3FileName());

        assertNotNull(wavResourceId.getId());
        assertNotNull(mp3ResourceId.getId());
    }

    @Test
    public void testGet() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        File mp3File = new File(getClass().getClassLoader().getResource(TRAIN_MP3).toURI());

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
        File mp3File = new File(getClass().getClassLoader().getResource(TRAIN_MP3).toURI());

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
        try
        {
            InputStream is = callfireClient.mediaApi().getData(mp3ResourceId.getId(), MediaType.MP3);
            File tempFile = File.createTempFile("mp3_sound", "mp3");
            Files.copy(is, tempFile.toPath(), REPLACE_EXISTING);
        }
        catch (CallfireApiException e)
        {
            assertTrue(e.getApiErrorMessage().getMessage().contains("file currently unavailable for mediaId:"));
        }

    }

    @Test
    public void testGetDataByKey() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        File mp3File = new File(getClass().getClassLoader().getResource(TRAIN_MP3).toURI());

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
        try
        {
            InputStream is = callfireClient.mediaApi().getData(selectHashPartFromUrlString(media.getPublicUrl()), MediaType.MP3);
            File tempFile = File.createTempFile("mp3_sound", "mp3");
            Files.copy(is, tempFile.toPath(), REPLACE_EXISTING);
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
        return Long.parseLong(message.substring(message.indexOf("mediaId:") + 9));
    }

    private static String selectHashPartFromUrlString(String message){
        int hashStartedAt = message.indexOf("public/") + 7;
        int hashFinishedAt = message.lastIndexOf('.');
        return message.substring(hashStartedAt, hashFinishedAt);
    }
}
