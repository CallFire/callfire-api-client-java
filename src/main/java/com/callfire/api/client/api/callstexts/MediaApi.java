package com.callfire.api.client.api.callstexts;

import com.callfire.api.client.*;
import com.callfire.api.client.api.callstexts.model.Media;
import com.callfire.api.client.api.callstexts.model.MediaType;
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

/**
 * Represents rest endpoint /media
 * Use the /media API to upload and download different types of media files: images, videos, music, etc.
 * <br>
 *
 * @since 2.0
 */
public class MediaApi {
    private static final String MEDIA_PATH = "/media";
    private static final String MEDIA_FILE_PATH = "/media/{}/file";
    private static final String MEDIA_ITEM_PATH = "/media/{}";
    private static final String MEDIA_ITEM_ID_PATH = "/media/{}.{}";
    private static final String MEDIA_ITEM_KEY_PATH = "/media/public/{}.{}";

    private RestApiClient client;

    public MediaApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Upload media file to account
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
     * Upload media file to account
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
        return client.postFile(MEDIA_PATH, of(ResourceId.class), params);
    }

    /**
     * Returns a single Media instance for a given media file id. This is the metadata
     * for the media only. No content data is returned from this API.
     *
     * @param id id of media file
     * @return Media meta object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Media get(Long id) {
        return get(id, null);
    }

    /**
     * Returns a single Media instance for a given media file id. This is the metadata
     * for the media only. No content data is returned from this API.
     *
     * @param id     id of media file
     * @param fields Limit text fields returned. Example fields=limit,offset,items(id,message)
     * @return Media meta object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Media get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = MEDIA_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, of(Media.class), queryParams);
    }

    /**
     * Returns media file's data as stream, in case there is no appropriate MediaType for your media file pass
     * MediaType.UNKNOWN
     *
     * @param id id of media file
     * @return file data as binary stream
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public InputStream getData(Long id, MediaType type) {
        Validate.notNull(id, "id cannot be null");
        Validate.notNull(type, "type cannot be null");
        String path = type == MediaType.UNKNOWN ? MEDIA_FILE_PATH.replaceFirst(PLACEHOLDER, id.toString())
            : MEDIA_ITEM_ID_PATH.replaceFirst(PLACEHOLDER, id.toString()).replaceFirst(PLACEHOLDER, type.toValue());
        return client.get(path, of(InputStream.class));
    }

    /**
     * Returns media file's data as stream
     *
     * @param key key of media file
     * @return file data as binary stream
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public InputStream getData(String key, MediaType type) {
        Validate.notBlank(key, "key cannot be blank");
        Validate.notNull(type, "type cannot be null");
        String path = MEDIA_ITEM_KEY_PATH.replaceFirst(PLACEHOLDER, key).replaceFirst(PLACEHOLDER, type.toValue());
        return client.get(path, of(InputStream.class));
    }
}
