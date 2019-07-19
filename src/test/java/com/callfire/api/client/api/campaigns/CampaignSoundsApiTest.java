package com.callfire.api.client.api.campaigns;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.callfire.api.client.api.AbstractApiTest;
import com.callfire.api.client.api.campaigns.model.CallCreateSound;
import com.callfire.api.client.api.campaigns.model.CampaignSound;
import com.callfire.api.client.api.campaigns.model.TextToSpeech;
import com.callfire.api.client.api.campaigns.model.Voice;
import com.callfire.api.client.api.campaigns.model.request.FindSoundsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;

public class CampaignSoundsApiTest extends AbstractApiTest {
    private static final String JSON_PATH = BASE_PATH + "/campaigns/campaignSoundsApi";

    @Test
    public void testFind() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/findCampaignSounds.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        FindSoundsRequest request = FindSoundsRequest.create()
            .limit(5L)
            .offset(0L)
            .filter("1234")
            .includeArchived(true)
            .includePending(true)
            .includeScrubbed(true)
            .build();
        Page<CampaignSound> sounds = client.campaignSoundsApi().find(request);
        assertThat(jsonConverter.serialize(sounds), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));

        assertUriContainsQueryParams(arg.getURI(), "limit=5", "offset=0", "filter=1234", "includeArchived=true", "includePending=true", "includeScrubbed=true");
    }

    @Test
    public void testGet() throws Exception {
        String expectedJson = getJsonPayload(JSON_PATH + "/response/getCampaignSound.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(expectedJson);

        CampaignSound campaignSound = client.campaignSoundsApi().get(11L, FIELDS);
        assertThat(jsonConverter.serialize(campaignSound), equalToIgnoringWhiteSpace(expectedJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpGet.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString(ENCODED_FIELDS));

        client.campaignSoundsApi().get(11L);
        assertEquals(2, captor.getAllValues().size());
        assertThat(captor.getAllValues().get(1).getURI().toString(), not(containsString("fields")));
    }

    @Test
    public void testDeleteByNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.campaignSoundsApi().delete(null);
    }

    @Test
    public void testDelete() throws Exception {
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse();

        client.campaignSoundsApi().delete(TEST_ID);

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpDelete.METHOD_NAME, arg.getMethod());
        assertNull(extractHttpEntity(arg));
        assertThat(arg.getURI().toString(), containsString("/" + TEST_ID));
    }

    @Test
    public void testGetNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.campaignSoundsApi().get(null);
    }

    @Test
    public void testGetMp3NullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.campaignSoundsApi().getMp3(null);
    }

    @Test
    public void testGetWavNullId() throws Exception {
        ex.expectMessage("id cannot be null");
        ex.expect(NullPointerException.class);
        client.campaignSoundsApi().getWav(null);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void testUpload() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/uploadSound.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);
        File file = new File(getClass().getClassLoader().getResource("file-examples/train.mp3").toURI());

        ResourceId id = client.campaignSoundsApi().upload(file, "fname");
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));
        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void testUploadAndGetSoundDetails() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/uploadSoundExtended.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);
        File file = new File(getClass().getClassLoader().getResource("file-examples/train.mp3").toURI());

        CampaignSound sound = client.campaignSoundsApi().uploadAndGetSoundDetails(file);
        assertThat(jsonConverter.serialize(sound), equalToIgnoringWhiteSpace(responseJson));
        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void testUploadAndGetSoundDetailsWithFileName() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/uploadSoundExtended.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);
        File file = new File(getClass().getClassLoader().getResource("file-examples/train.mp3").toURI());

        CampaignSound sound = client.campaignSoundsApi().uploadAndGetSoundDetails(file, "fname");
        assertThat(jsonConverter.serialize(sound), equalToIgnoringWhiteSpace(responseJson));
        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void testUploadAndGetSoundDetailsWithFileNameAndFields() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/uploadSoundExtended.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);
        File file = new File(getClass().getClassLoader().getResource("file-examples/train.mp3").toURI());

        CampaignSound sound = client.campaignSoundsApi().uploadAndGetSoundDetails(file, "fname", "id,name,created,lengthInSeconds,status,duplicate");
        assertThat(jsonConverter.serialize(sound), equalToIgnoringWhiteSpace(responseJson));
        HttpUriRequest arg = captor.getValue();
        assertUriContainsQueryParams(arg.getURI(), "fields=id%2Cname%2Ccreated%2ClengthInSeconds%2Cstatus%2Cduplicate");
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
    }

    @Test
    public void testRecordViaPhone() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/uploadSound.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/recordViaPhone.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        CallCreateSound callCreateSound = new CallCreateSound();
        callCreateSound.setName("My sound file");
        callCreateSound.setToNumber("12135551122");
        ResourceId id = client.campaignSoundsApi().recordViaPhone(callCreateSound);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testRecordViaPhoneExtendedWithParams() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/uploadSoundExtended.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/recordViaPhone.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        CallCreateSound callCreateSound = new CallCreateSound();
        callCreateSound.setName("My sound file");
        callCreateSound.setToNumber("12135551122");
        CampaignSound sound = client.campaignSoundsApi().recordViaPhoneAndGetSoundDetails(callCreateSound, "id,name,status");
        assertThat(jsonConverter.serialize(sound), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertUriContainsQueryParams(arg.getURI(), "fields=id%2Cname%2Cstatus");
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testRecordViaPhoneExtendedWithoutParams() throws Exception {
        String   responseJson = getJsonPayload(JSON_PATH + "/response/uploadSound.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/recordViaPhone.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        CallCreateSound callCreateSound = new CallCreateSound();
        callCreateSound.setName("My sound file");
        callCreateSound.setToNumber("12135551122");
        CampaignSound sound = client.campaignSoundsApi().recordViaPhoneAndGetSoundDetails(callCreateSound);
        assertThat(jsonConverter.serialize(sound), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testCreateFromTts() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/uploadSound.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/createFromTts.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        TextToSpeech textToSpeech = new TextToSpeech();
        textToSpeech.setVoice(Voice.MALE1);
        textToSpeech.setMessage("My sound file");
        ResourceId id = client.campaignSoundsApi().createFromTts(textToSpeech);
        assertThat(jsonConverter.serialize(id), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testCreateFromTtsExtendedWithoutParameters() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/uploadSound.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/createFromTts.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        TextToSpeech textToSpeech = new TextToSpeech();
        textToSpeech.setVoice(Voice.MALE1);
        textToSpeech.setMessage("My sound file");
        CampaignSound sound = client.campaignSoundsApi().createFromTtsAndGetSoundDetails(textToSpeech);
        assertThat(jsonConverter.serialize(sound), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }

    @Test
    public void testCreateFromTtsExtendedWithParams() throws Exception {
        String responseJson = getJsonPayload(JSON_PATH + "/response/uploadSoundExtended.json");
        String requestJson = getJsonPayload(JSON_PATH + "/request/createFromTts.json");
        ArgumentCaptor<HttpUriRequest> captor = mockHttpResponse(responseJson);

        TextToSpeech textToSpeech = new TextToSpeech();
        textToSpeech.setVoice(Voice.MALE1);
        textToSpeech.setMessage("My sound file");
        CampaignSound sound = client.campaignSoundsApi().createFromTtsAndGetSoundDetails(textToSpeech, FIELDS);
        assertThat(jsonConverter.serialize(sound), equalToIgnoringWhiteSpace(responseJson));

        HttpUriRequest arg = captor.getValue();
        assertEquals(HttpPost.METHOD_NAME, arg.getMethod());
        assertUriContainsQueryParams(arg.getURI(), ENCODED_FIELDS);
        assertThat(extractHttpEntity(arg), equalToIgnoringWhiteSpace(requestJson));
    }
}
