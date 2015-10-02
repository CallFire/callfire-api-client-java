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
 *
 * @since 1.0
 */
public class CampaignSoundsApi {
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

    public CampaignSoundsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find all campaign sounds that were created by the user.
     * These are all of the available sounds to be used in campaigns.
     *
     * @param request request object with different fields for search
     * @return page with campaign sound objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     * @see FindSoundsRequest
     */
    public Page<CampaignSound> find(FindSoundsRequest request) {
        return client.get(SOUNDS_PATH, PAGE_OF_SOUNDS_TYPE, request);
    }

    /**
     * Returns a single CampaignSound instance for a given campaign sound id. This is the meta
     * data to the sounds only. No audio data is returned from this API.
     *
     * @param id id of campaign sound
     * @return CampaignSound meta object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public CampaignSound get(Long id) {
        return get(id, null);
    }

    /**
     * Returns a single CampaignSound instance for a given campaign sound id. This is the meta
     * data to the sounds only. No audio data is returned from this API.
     *
     * @param id     id of campaign sound
     * @param fields Limit text fields returned. Example fields=limit,offset,items(id,message)
     * @return CampaignSound meta object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public CampaignSound get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = SOUNDS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, CALL_SOUND_TYPE, queryParams);
    }

    /**
     * Download the MP3 version of the hosted file.
     *
     * @param id id of sound
     * @return mp3 file as input stream
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public InputStream getMp3(Long id) {
        Validate.notNull(id, "id cannot be null");
        String path = SOUNDS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()) + ".mp3";
        return client.get(path, INPUT_STREAM_TYPE);
    }

    /**
     * Download the WAV version of the hosted file.
     *
     * @param id id of sound
     * @return wav file as input stream
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public InputStream getWav(Long id) {
        Validate.notNull(id, "id cannot be null");
        String path = SOUNDS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()) + ".wav";
        return client.get(path, INPUT_STREAM_TYPE);
    }

    /**
     * Use this API to create a sound via phone call. Supply the required phone number in
     * the CallCreateSound object inside of the request, and the user will receive a call
     * shortly after with instructions on how to record a sound over the phone.
     *
     * @param callCreateSound request object to create campaign sound
     * @return ResourceId object with sound id
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId recordViaPhone(CallCreateSound callCreateSound) {
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
    public ResourceId upload(File file) {
        return upload(file, null);
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
    public ResourceId upload(File file, String name) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("file", file);
        params.put("name", name);
        return client.postFile(SOUNDS_FILES_PATH, RESOURCE_ID_TYPE, params);
    }

    /**
     * Use this API to create a sound file via a supplied string of text.
     *
     * @param textToSpeech TTS object to create
     * @return ResourceId object with sound id
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId createFromTts(TextToSpeech textToSpeech) {
        return client.post(SOUNDS_TTS_PATH, RESOURCE_ID_TYPE, textToSpeech);
    }
}
