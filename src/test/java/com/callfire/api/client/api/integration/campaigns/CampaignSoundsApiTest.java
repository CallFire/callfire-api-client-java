package com.callfire.api.client.api.integration.campaigns;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.campaigns.CampaignSoundsApi;
import com.callfire.api.client.api.campaigns.model.TextToSpeech;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.integration.AbstractIntegrationTest;
import com.callfire.api.client.api.campaigns.model.CallCreateSound;
import com.callfire.api.client.api.campaigns.model.CampaignSound;
import com.callfire.api.client.api.campaigns.model.request.FindSoundsRequest;
import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

import static com.callfire.api.client.api.campaigns.model.CampaignSound.Status.ACTIVE;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

/**
 * integration tests for /subscriptions api endpoint
 */
public class CampaignSoundsApiTest extends AbstractIntegrationTest {

    @Test
    public void testFind() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        FindSoundsRequest request = FindSoundsRequest.create()
            .limit(3L)
            .filter("sample")
            .build();
        Page<CampaignSound> campaignSounds = callfireClient.campaignSoundsApi().find(request);
        assertEquals(Long.valueOf(4), campaignSounds.getTotalCount());
        assertEquals(3, campaignSounds.getItems().size());
        for (CampaignSound cs : campaignSounds.getItems()) {
            assertThat(cs.getName(), containsString("Sample"));
        }
    }

    @Test
    @Ignore("performs real call to specified number")
    public void testCallInToRecord() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        CallCreateSound callCreateSound = new CallCreateSound();
        callCreateSound.setName("call_in_sound_" + new Date().getTime());
        callCreateSound.setToNumber(getCallerId());
        ResourceId resourceId = callfireClient.campaignSoundsApi().recordViaPhone(callCreateSound);
        assertNotNull(resourceId.getId());
        System.out.println(resourceId.getId());
    }

    @Test
    public void testUploadMp3WavFilesAndGetThem() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        CampaignSoundsApi campaignSoundsApi = callfireClient.campaignSoundsApi();
        long timstamp = new Date().getTime();
        String soundName = "mp3_test_" + timstamp;
        File mp3File = new File(getClass().getClassLoader().getResource("file-examples/train.mp3").toURI());
        File wavFile = new File(getClass().getClassLoader().getResource("file-examples/train.wav").toURI());
        ResourceId mp3ResourceId = campaignSoundsApi.upload(mp3File, soundName);
        ResourceId wavResourceId = campaignSoundsApi.upload(wavFile);
        assertNotNull(mp3ResourceId.getId());
        assertNotNull(wavResourceId.getId());

        // get sound metadata
        CampaignSound campaignSound = campaignSoundsApi.get(mp3ResourceId.getId(),
            "name,status,lengthInSeconds");
        assertNull(campaignSound.getId());
        assertEquals(soundName, campaignSound.getName());
        assertEquals(ACTIVE, campaignSound.getStatus());
        assertEquals(Integer.valueOf(6), campaignSound.getLengthInSeconds());

        // get mp3
        InputStream is = campaignSoundsApi.getMp3(mp3ResourceId.getId());
        File tempFile = File.createTempFile("mp3_sound", "mp3");
        try (FileOutputStream os = new FileOutputStream(tempFile)) {
            IOUtils.copy(is, os);
        }

        // get wav
        is = campaignSoundsApi.getWav(mp3ResourceId.getId());
        tempFile = File.createTempFile("wav_sound", "wav");
        try (FileOutputStream os = new FileOutputStream(tempFile)) {
            IOUtils.copy(is, os);
        }
    }

    @Test
    @Ignore("need TTS setup")
    public void testCreateFromTts() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        TextToSpeech tts = new TextToSpeech();
        tts.setMessage("this is TTS message from java client");
        ResourceId resourceId = callfireClient.campaignSoundsApi().createFromTts(tts);
        CampaignSound campaignSound = callfireClient.campaignSoundsApi().get(resourceId.getId());
        assertEquals(resourceId.getId(), campaignSound.getId());
        assertThat(campaignSound.getLengthInSeconds(), greaterThan(2));
    }
}
