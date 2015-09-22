package com.callfire.api.client.integration.endpoint.campaigns;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.campaigns.SoundsApi;
import com.callfire.api.client.api.campaigns.model.TextToSpeech;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.integration.endpoint.AbstractIntegrationTest;
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
public class SoundsApiTest extends AbstractIntegrationTest {

    @Test
    public void testFindCampaignSounds() throws Exception {
        CallfireClient callfireClient = getCallfireClient();

        FindSoundsRequest request = FindSoundsRequest.create()
            .limit(3L)
            .filter("sample")
            .build();
        Page<CampaignSound> campaignSounds = callfireClient.getCampaignsApi().getSoundsApi()
            .findCampaignSounds(request);
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
        ResourceId resourceId = callfireClient.getCampaignsApi().getSoundsApi()
            .postCallCampaignSound(callCreateSound);
        assertNotNull(resourceId.getId());
        System.out.println(resourceId.getId());
    }

    @Test
    public void testUploadMp3WavFilesAndGetThem() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        SoundsApi soundsApi = callfireClient.getCampaignsApi().getSoundsApi();
        long timstamp = new Date().getTime();
        String soundName = "mp3_test_" + timstamp;
        File mp3File = new File(getClass().getClassLoader().getResource("file-examples/train.mp3").toURI());
        File wavFile = new File(getClass().getClassLoader().getResource("file-examples/train.wav").toURI());
        ResourceId mp3ResourceId = soundsApi.postFileCampaignSound(mp3File, soundName);
        ResourceId wavResourceId = soundsApi.postFileCampaignSound(wavFile);
        assertNotNull(mp3ResourceId.getId());
        assertNotNull(wavResourceId.getId());

        // getSubscription sound metadata
        CampaignSound campaignSound = soundsApi.getCampaignSoundMeta(mp3ResourceId.getId(),
            "name,status,lengthInSeconds");
        assertNull(campaignSound.getId());
        assertEquals(soundName, campaignSound.getName());
        assertEquals(ACTIVE, campaignSound.getStatus());
        assertEquals(Integer.valueOf(6), campaignSound.getLengthInSeconds());

        // getSubscription mp3
        InputStream is = soundsApi.getCampaignSoundDataMp3(mp3ResourceId.getId());
        File tempFile = File.createTempFile("mp3_sound", "mp3");
        try (FileOutputStream os = new FileOutputStream(tempFile)) {
            IOUtils.copy(is, os);
        }

        // getSubscription wav
        is = soundsApi.getCampaignSoundDataWav(mp3ResourceId.getId());
        tempFile = File.createTempFile("wav_sound", "wav");
        try (FileOutputStream os = new FileOutputStream(tempFile)) {
            IOUtils.copy(is, os);
        }
    }

    @Test
    public void testCreateTts() throws Exception {
        CallfireClient callfireClient = getCallfireClient();
        TextToSpeech tts = new TextToSpeech();
        tts.setMessage("this is TTS message from java client");
        ResourceId resourceId = callfireClient.getCampaignsApi().getSoundsApi().postTtsCampaignSound(tts);
        CampaignSound campaignSound = callfireClient.getCampaignsApi().getSoundsApi()
            .getCampaignSoundMeta(resourceId.getId());
        assertEquals(resourceId.getId(), campaignSound.getId());
        assertThat(campaignSound.getLengthInSeconds(), greaterThan(2));
    }
}
