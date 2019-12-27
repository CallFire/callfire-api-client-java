package com.callfire.api.client.integration.campaigns;

import static com.callfire.api.client.api.campaigns.model.CampaignSound.Status.ACTIVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Date;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.campaigns.CampaignSoundsApi;
import com.callfire.api.client.api.campaigns.model.CallCreateSound;
import com.callfire.api.client.api.campaigns.model.CampaignSound;
import com.callfire.api.client.api.campaigns.model.TextToSpeech;
import com.callfire.api.client.api.campaigns.model.request.FindSoundsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.integration.AbstractIntegrationTest;

/**
 * integration tests for /subscriptions api endpoint
 */
public class CampaignSoundsApiTest extends AbstractIntegrationTest {

    @Test
    public void testFind() {
        CallfireClient callfireClient = getCallfireClient();

        FindSoundsRequest request = FindSoundsRequest.create()
                .limit(3L)
                .filter("sample")
                .includeArchived(true)
                .includeScrubbed(true)
                .includePending(true)
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
    public void testCallInToRecord() {
        CallfireClient callfireClient = getCallfireClient();
        CallCreateSound callCreateSound = new CallCreateSound();
        callCreateSound.setName("call_in_sound_" + new Date().getTime());
        callCreateSound.setToNumber(getCallerId());
        ResourceId resourceId = callfireClient.campaignSoundsApi().recordViaPhone(callCreateSound);
        assertNotNull(resourceId.getId());
        System.out.println(resourceId.getId());

        CampaignSound sound = callfireClient.campaignSoundsApi().recordViaPhoneAndGetSoundDetails(callCreateSound);
        assertNotNull(sound);
    }

    @Test
    public void testUploadMp3WavFilesAndGetThem() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        CampaignSoundsApi campaignSoundsApi = callfireClient.campaignSoundsApi();
        long timestamp = new Date().getTime();
        String soundName = "mp3_test_" + timestamp;
        File mp3File = new File(getClass().getClassLoader().getResource("file-examples/train1.mp3").toURI());
        File wavFile = new File(getClass().getClassLoader().getResource("file-examples/train1.wav").toURI());
        ResourceId mp3ResourceId = campaignSoundsApi.upload(mp3File, soundName);
        ResourceId wavResourceId = campaignSoundsApi.upload(wavFile);
        assertNotNull(mp3ResourceId.getId());
        assertNotNull(wavResourceId.getId());

        CampaignSound mp3Sound = campaignSoundsApi.uploadAndGetSoundDetails(mp3File, soundName,
                "id,name,created,lengthInSeconds,status,duplicate");
        assertTrue(mp3Sound.getDuplicate());
        mp3Sound = campaignSoundsApi.uploadAndGetSoundDetails(mp3File);
        assertTrue(mp3Sound.getDuplicate());
        mp3Sound = campaignSoundsApi.uploadAndGetSoundDetails(mp3File, soundName);
        assertTrue(mp3Sound.getDuplicate());
        // get sound metadata
        CampaignSound campaignSound = campaignSoundsApi.get(mp3ResourceId.getId(), "name,status,lengthInSeconds");
        assertNull(campaignSound.getId());
        assertTrue(campaignSound.getName().contains("mp3_test"));
        assertEquals(ACTIVE, campaignSound.getStatus());
        assertEquals(Integer.valueOf(1), campaignSound.getLengthInSeconds());

        // get mp3
        InputStream is = campaignSoundsApi.getMp3(mp3ResourceId.getId());
        File tempFile = File.createTempFile("mp3_sound" + new Date().getTime(), ".mp3");
        Files.copy(is, tempFile.toPath(), REPLACE_EXISTING);

        // get wav
        is = campaignSoundsApi.getWav(mp3ResourceId.getId());
        tempFile = File.createTempFile("wav_sound" + new Date().getTime(), ".wav");
        Files.copy(is, tempFile.toPath(), REPLACE_EXISTING);
    }

    @Test
    public void testUploadSoundAndDeleteIt() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        CampaignSoundsApi campaignSoundsApi = callfireClient.campaignSoundsApi();
        long timestamp = new Date().getTime();
        String soundName = "mp3_test_" + timestamp;
        File mp3File = new File(getClass().getClassLoader().getResource("file-examples/train1.mp3").toURI());
        ResourceId mp3ResourceId = campaignSoundsApi.upload(mp3File, soundName);
        assertNotNull(mp3ResourceId.getId());

        // delete sound
        campaignSoundsApi.delete(mp3ResourceId.getId());

        Page<CampaignSound> sounds = campaignSoundsApi.find(FindSoundsRequest.create().filter(soundName).build());
        Assert.assertTrue(sounds.getItems().size() == 0);
    }

    @Test
    @Ignore("need TTS setup")
    public void testCreateFromTts() {
        CallfireClient callfireClient = getCallfireClient();
        TextToSpeech tts = new TextToSpeech();
        tts.setMessage("this is TTS message from java client");
        ResourceId resourceId = callfireClient.campaignSoundsApi().createFromTts(tts);
        CampaignSound campaignSound = callfireClient.campaignSoundsApi().get(resourceId.getId());
        assertEquals(resourceId.getId(), campaignSound.getId());
        assertThat(campaignSound.getLengthInSeconds(), greaterThan(2));

        CampaignSound sound = callfireClient.campaignSoundsApi().createFromTtsAndGetSoundDetails(tts);
        assertNotNull(sound);
    }
}
