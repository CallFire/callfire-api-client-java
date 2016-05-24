package com.callfire.api.client.api.campaigns;

import com.callfire.api.client.*;
import com.callfire.api.client.api.campaigns.model.CallCreateSound;
import com.callfire.api.client.api.campaigns.model.CampaignSound;
import com.callfire.api.client.api.campaigns.model.TextToSpeech;
import com.callfire.api.client.api.campaigns.model.request.FindSoundsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.of;
import static com.callfire.api.client.ModelType.pageOf;

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
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<CampaignSound> find(FindSoundsRequest request) {
        return client.get(SOUNDS_PATH, pageOf(CampaignSound.class), request);
    }

    /**
     * Returns a single CampaignSound instance for a given campaign sound id. This is the meta
     * data to the sounds only. No audio data is returned from this API.
     *
     * @param id id of campaign sound
     * @return CampaignSound meta object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
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
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CampaignSound get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = SOUNDS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, of(CampaignSound.class), queryParams);
    }

    /**
     * Download the MP3 version of the hosted file.
     *
     * @param id id of sound
     * @return mp3 file as input stream
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public InputStream getMp3(Long id) {
        Validate.notNull(id, "id cannot be null");
        String path = SOUNDS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()) + ".mp3";
        return client.get(path, of(InputStream.class));
    }

    /**
     * Download the WAV version of the hosted file.
     *
     * @param id id of sound
     * @return wav file as input stream
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public InputStream getWav(Long id) {
        Validate.notNull(id, "id cannot be null");
        String path = SOUNDS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()) + ".wav";
        return client.get(path, of(InputStream.class));
    }

    /**
     * Use this API to create a sound via phone call. Supply the required phone number in
     * the CallCreateSound object inside of the request, and the user will receive a call
     * shortly after with instructions on how to record a sound over the phone.
     *
     * @param callCreateSound request object to create campaign sound
     * @return ResourceId object with sound id
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId recordViaPhone(CallCreateSound callCreateSound) {
        return client.post(SOUNDS_CALLS_PATH, of(ResourceId.class), callCreateSound);
    }

    /**
     * Use this API to create a sound via phone call. Supply the required phone number in
     * the CallCreateSound object inside of the request, and the user will receive a call
     * shortly after with instructions on how to record a sound over the phone.
     *
     * @param callCreateSound request object to create campaign sound
     * @return CampaignSound object with sound id,name etc
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CampaignSound recordViaPhoneExtended(CallCreateSound callCreateSound) {
        return recordViaPhoneExtended(callCreateSound, null);
    }

    /**
     * Use this API to create a sound via phone call. Supply the required phone number in
     * the CallCreateSound object inside of the request, and the user will receive a call
     * shortly after with instructions on how to record a sound over the phone.
     *
     * @param callCreateSound request object to create campaign sound
     * @param fields fields returned. E.g. fields=id,name or fields=items(id,name)
     * @return CampaignSound object with sound id,name etc
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CampaignSound recordViaPhoneExtended(CallCreateSound callCreateSound, String fields) {
        List<NameValuePair> queryParams = new ArrayList<>(7);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.post(SOUNDS_CALLS_PATH, of(CampaignSound.class), callCreateSound, queryParams);
    }

    /**
     * Upload a MP3 or WAV file to account
     *
     * @param file file to upload
     * @return ResourceId object with sound id
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
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
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId upload(File file, String name) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("file", file);
        params.put("name", name);
        return client.postFile(SOUNDS_FILES_PATH, of(ResourceId.class), params);
    }

    /**
     * Upload a MP3 or WAV file to account
     *
     * @param file file to upload
     * @return CampaignSound object with sound id
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CampaignSound extendedUpload(File file) {
        return extendedUpload(file, null);
    }

    /**
     * Upload a MP3 or WAV file to account
     *
     * @param file file to upload
     * @param name name for file uploaded
     * @return CampaignSound object with sound data
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CampaignSound extendedUpload(File file, String name) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("file", file);
        params.put("name", name);
        return client.postFile(SOUNDS_FILES_PATH, of(CampaignSound.class), params);
    }

    /**
     * Use this API to create a sound file via a supplied string of text.
     *
     * @param textToSpeech TTS object to create
     * @return ResourceId object with sound id
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId createFromTts(TextToSpeech textToSpeech) {
        return client.post(SOUNDS_TTS_PATH, of(ResourceId.class), textToSpeech);
    }

    /**
     * Use this API to create a sound file via a supplied string of text.
     *
     * @param textToSpeech TTS object to create
     * @return CampaignSound object with sound id,name etc
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CampaignSound createFromTtsExtended(TextToSpeech textToSpeech) {
        return createFromTtsExtended(textToSpeech, null);
    }

    /**
     * Use this API to create a sound file via a supplied string of text.
     *
     * @param textToSpeech TTS object to create
     * @param fields fields returned. E.g. fields=id,name or fields=items(id,name)
     * @return CampaignSound object with sound id,name etc
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CampaignSound createFromTtsExtended(TextToSpeech textToSpeech, String fields) {
        List<NameValuePair> queryParams = new ArrayList<>(7);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.post(SOUNDS_TTS_PATH, of(CampaignSound.class), textToSpeech, queryParams);
    }
}
