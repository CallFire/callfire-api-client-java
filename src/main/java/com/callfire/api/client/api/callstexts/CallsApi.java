package com.callfire.api.client.api.callstexts;

import com.callfire.api.client.*;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.callstexts.model.CallRecipient;
import com.callfire.api.client.api.callstexts.model.request.FindCallsRequest;
import com.callfire.api.client.api.campaigns.model.CallRecording;
import com.callfire.api.client.api.campaigns.model.Voice;
import com.callfire.api.client.api.common.model.Page;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.*;

/**
 * Represents rest endpoint /calls
 * Use the /calls API to quickly send individual calls, get results.
 * A verified Caller ID and sufficient credits are required to make a call.
 * <br>
 *
 * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
 * @since 1.0
 */
public class CallsApi {
    private static final String CALLS_PATH = "/calls";
    private static final String CALLS_ITEM_PATH = "/calls/{}";
    private static final String CALLS_ITEM_RECORDINGS_PATH = "/calls/{}/recordings";
    private static final String CALLS_ITEM_RECORDING_BY_NAME_PATH = "/calls/{}/recordings/{}";
    private static final String CALLS_ITEM_MP3_RECORDING_BY_NAME_PATH = "/calls/{}/recordings/{}.mp3";
    private static final String CALLS_ITEM_RECORDING_BY_ID_PATH = "/calls/recordings/{}";
    private static final String CALLS_ITEM_MP3_RECORDING_BY_ID_PATH = "/calls/recordings/{}.mp3";

    private RestApiClient client;

    public CallsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Finds all calls sent or received by the user, filtered by different properties, broadcast id,
     * toNumber, fromNumber, label, state, etc. Use "campaignId=0" parameter to query
     * for all calls sent through the POST /calls API {@link CallsApi#send(List)}.
     *
     * @param request request object with different fields to filter
     * @return Page with @{Call} objects
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
     */
    public Page<Call> find(FindCallsRequest request) {
        return client.get(CALLS_PATH, pageOf(Call.class), request);
    }

    /**
     * Get call by id
     *
     * @param id id of call
     * @return Call object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Call get(Long id) {
        return get(id, null);
    }

    /**
     * Get call by id
     *
     * @param id     id of call
     * @param fields limit fields returned. Example fields=id,name
     * @return Call object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Call get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = CALLS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, of(Call.class), queryParams);
    }

    /**
     * Send calls to recipients through default campaign.
     * Use the API to quickly send individual calls.
     * A verified Caller ID and sufficient credits are required to make a call.
     *
     * @param recipients call recipients
     * @return list of {@link Call}
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Call> send(List<CallRecipient> recipients) {
        return send(recipients, null, null, null, null, null, null, null);
    }

    /**
     * Send call to recipients through existing campaign, if null default campaign will be used
     * Use the API to quickly send individual calls.
     * A verified Caller ID and sufficient credits are required to make a call.
     *
     * @param recipients call recipients
     * @param campaignId specify a campaignId to send calls quickly on a previously created campaign
     * @param fields     fields returned. E.g. fields=id,name or fields=items(id,name)
     * @return list of {@link Call}
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Call> send(List<CallRecipient> recipients, Long campaignId, String fields) {
        return send(recipients, campaignId, fields, null, null, null, null, null);
    }

    /**
     * Send call to recipients through existing campaign, if null default campaign will be used
     * Use the API to quickly send individual calls.
     * A verified Caller ID and sufficient credits are required to make a call.
     *
     * @param recipients call recipients
     * @param campaignId specify a campaignId to send calls quickly on a previously created campaign
     * @param fields     fields returned. E.g. fields=id,name or fields=items(id,name)
     * @param defaultLiveMessage     specify live message to use in case when recipient is missing that param
     * @param defaultMachineMessage     specify machine message to use in case when recipient is missing that param
     * @param defaultLiveMessageSoundId     specify live message sound id to use in case when recipient is missing that param
     * @param defaultMachineMessageSoundId     specify machine message sound id to use in case when recipient is missing that param
     * @param defaultVoice     specify voice to use in case when recipient is missing that param
     * @return list of {@link Call}
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Call> send(List<CallRecipient> recipients, Long campaignId, String fields, String defaultLiveMessage, String defaultMachineMessage, Long defaultLiveMessageSoundId, Long defaultMachineMessageSoundId, Voice defaultVoice) {
        List<NameValuePair> queryParams = new ArrayList<>(7);
        addQueryParamIfSet("campaignId", campaignId, queryParams);
        addQueryParamIfSet("fields", fields, queryParams);
        addQueryParamIfSet("defaultLiveMessage", defaultLiveMessage, queryParams);
        addQueryParamIfSet("defaultMachineMessage", defaultMachineMessage, queryParams);
        addQueryParamIfSet("defaultLiveMessageSoundId", defaultLiveMessageSoundId, queryParams);
        addQueryParamIfSet("defaultMachineMessageSoundId", defaultMachineMessageSoundId, queryParams);
        addQueryParamIfSet("defaultVoice", defaultVoice != null ? defaultVoice.toString() : null, queryParams);

        return client.post(CALLS_PATH, listHolderOf(Call.class), recipients, queryParams).getItems();
    }


    /**
     * Returns call recordings for a call
     *
     * @param id     id of call
     * @return list of {@link CallRecording}
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<CallRecording> getCallRecordings(Long id) {
        return getCallRecordings(id, null);
    }

    /**
     * Returns call recordings for a call
     *
     * @param id     id of call
     * @param fields Limit text fields returned. Example fields=limit,offset,items(id,message)
     * @return list of {@link CallRecording}
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<CallRecording> getCallRecordings(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = CALLS_ITEM_RECORDINGS_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, listHolderOf(CallRecording.class), queryParams).getItems();
    }

    /**
     * Returns call recording by name
     *
     * @param callId        id of call
     * @param recordingName name of call recording
     * @return CallRecording meta object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CallRecording getCallRecordingByName(Long callId, String recordingName) {
        return getCallRecordingByName(callId, recordingName, null);
    }

    /**
     * Returns call recording by name
     *
     * @param callId        id of call
     * @param recordingName name of call recording
     * @param fields        Limit text fields returned. Example fields=limit,offset,items(id,message)
     * @return CallRecording meta object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CallRecording getCallRecordingByName(Long callId, String recordingName, String fields) {
        Validate.notNull(callId, "id cannot be null");
        Validate.notNull(recordingName, "recordingName cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = CALLS_ITEM_RECORDING_BY_NAME_PATH.replaceFirst(PLACEHOLDER, callId.toString()).replaceFirst(PLACEHOLDER, recordingName);
        return client.get(path, of(CallRecording.class), queryParams);
    }

    /**
     * Download call mp3 recording by name
     *
     * @param callId        id of call
     * @param recordingName name of call recording
     * @return mp3 file as input stream
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public InputStream getCallRecordingMp3ByName(Long callId, String recordingName) {
        Validate.notNull(callId, "id cannot be null");
        Validate.notNull(recordingName, "recordingName cannot be null");
        String path = CALLS_ITEM_MP3_RECORDING_BY_NAME_PATH.replaceFirst(PLACEHOLDER, callId.toString()).replaceFirst(PLACEHOLDER, recordingName);
        return client.get(path, of(InputStream.class));
    }

    /**
     * Returns call recording by id
     *
     * @param id id of call recording
     * @return CallRecording meta object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CallRecording getCallRecording(Long id) {
        return getCallRecording(id, null);
    }

    /**
     * Returns call recording by id
     *
     * @param id     id of call recording
     * @param fields Limit text fields returned. Example fields=limit,offset,items(id,message)
     * @return CallRecording meta object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public CallRecording getCallRecording(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = CALLS_ITEM_RECORDING_BY_ID_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, of(CallRecording.class), queryParams);
    }

    /**
     * Download call mp3 recording by id
     *
     * @param id id of call recording
     * @return mp3 file as input stream
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public InputStream getCallRecordingMp3(Long id) {
        Validate.notNull(id, "id cannot be null");
        String path = CALLS_ITEM_MP3_RECORDING_BY_ID_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, of(InputStream.class));
    }
}
