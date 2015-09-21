package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.campaigns.model.TextToSpeech;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.campaigns.model.CallCreateSound;
import com.callfire.api.client.api.campaigns.model.CampaignSound;
import com.callfire.api.client.api.campaigns.model.request.FindSoundsRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.callfire.api.client.ClientConstants.Type.INPUT_STREAM_TYPE;
import static com.callfire.api.client.ClientConstants.Type.RESOURCE_ID_TYPE;
import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /campaigns/sounds
 */
public class SoundsApi {
    private static final String SOUNDS_PATH = "/campaigns/sounds";
    private static final String SOUNDS_ITEM_PATH = "/campaigns/sounds/{}";
    private static final String SOUNDS_CALLS_PATH = "/campaigns/sounds/calls";
    private static final String SOUNDS_FILES_PATH = "/campaigns/sounds/files";
    private static final String SOUNDS_TTS_PATH = "/campaigns/sounds/tts";

    private static final TypeReference<CampaignSound> CALL_SOUND_TYPE = new TypeReference<CampaignSound>() {
    };
    public static final TypeReference<Page<CampaignSound>> PAGE_OF_SOUNDS_TYPE = new TypeReference<Page<CampaignSound>>() {
    };

    private RestApiClient client;

    public SoundsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find sounds in account
     *
     * @param request request object with different fields for search
     * @return page with campaign sound objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     * @see FindSoundsRequest
     */
    public Page<CampaignSound> findCampaignSounds(FindSoundsRequest request) {
        return client.get(SOUNDS_PATH, PAGE_OF_SOUNDS_TYPE, request);
    }

    /**
     * Get sound metadata by id
     *
     * @param id id of campaign sound
     * @return CampaignSound meta object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public CampaignSound getCampaignSoundMeta(Long id) {
        return getCampaignSoundMeta(id, null);
    }

    /**
     * Get sound metadata by id
     *
     * @param id     id of campaign sound
     * @param fields Limit text fields returned. Example fields=limit,offset,items(id,message)
     * @return CampaignSound meta object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public CampaignSound getCampaignSoundMeta(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>();
        addQueryParamIfSet("fields", fields, queryParams);
        String path = SOUNDS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, CALL_SOUND_TYPE, queryParams);
    }

    /**
     * Returns mp3 of sound by id
     *
     * @param id id of sound
     * @return mp3 file as input stream
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public InputStream getCampaignSoundDataMp3(Long id) {
        Validate.notNull(id, "id cannot be null");
        String path = SOUNDS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()) + ".mp3";
        return client.get(path, INPUT_STREAM_TYPE);
    }

    /**
     * Returns wav of sound by id
     *
     * @param id id of sound
     * @return wav file as input stream
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public InputStream getCampaignSoundDataWav(Long id) {
        Validate.notNull(id, "id cannot be null");
        String path = SOUNDS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()) + ".wav";
        return client.get(path, INPUT_STREAM_TYPE);
    }

    /**
     * Record a sound file via a phone call
     *
     * @param callCreateSound request object to create campaign sound
     * @return ResourceId object with sound id
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId postCallCampaignSound(CallCreateSound callCreateSound) {
        return client.post(SOUNDS_CALLS_PATH, RESOURCE_ID_TYPE, callCreateSound);
    }

    /**
     * Upload a MP3 or WAV file to account
     *
     * @param file file to upload
     * @return ResourceId object with sound id
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId postFileCampaignSound(File file) {
        return postFileCampaignSound(file, null);
    }

    /**
     * Upload a MP3 or WAV file to account
     *
     * @param file file to upload
     * @param name name for file uploaded
     * @return ResourceId object with sound id
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId postFileCampaignSound(File file, String name) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("file", file);
        params.put("name", name);
        return client.postFile(SOUNDS_FILES_PATH, RESOURCE_ID_TYPE, params);
    }

    /**
     * Creates a sound file using TTS
     *
     * @param textToSpeech TTS object to create
     * @return ResourceId object with sound id
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId postTtsCampaignSound(TextToSpeech textToSpeech) {
        return client.post(SOUNDS_TTS_PATH, RESOURCE_ID_TYPE, textToSpeech);
    }
}
