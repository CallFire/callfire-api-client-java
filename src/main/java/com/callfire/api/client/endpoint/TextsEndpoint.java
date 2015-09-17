package com.callfire.api.client.endpoint;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.model.Page;
import com.callfire.api.client.model.Text;
import com.callfire.api.client.model.request.FindTextsRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /texts
 */
public class TextsEndpoint {
    private static final String TEXTS_PATH = "/texts";
    private static final String TEXTS_ITEM_PATH = "/texts/{}";
    private static final TypeReference<Text> TEXT_TYPE = new TypeReference<Text>() {
    };
    private static final TypeReference<Page<Text>> PAGE_OF_TEXT_TYPE = new TypeReference<Page<Text>>() {
    };

    private RestApiClient client;

    public TextsEndpoint(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find texts by broadcast id etc... If no limit is given then the last 100 texts will be returned.
     *
     * @param request request object with different fields to filter
     * @return Page with @{Text} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Text> findTexts(FindTextsRequest request) throws CallfireApiException, CallfireClientException {
        return client.get(TEXTS_PATH, PAGE_OF_TEXT_TYPE, request);
    }

    /**
     * Get text by id.
     *
     * @param id id of text
     * @return text object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Text getText(Long id) throws CallfireApiException, CallfireClientException {
        return getText(id, null);
    }

    /**
     * Get text by id.
     *
     * @param id     id of text
     * @param fields limit fields returned. Example fields=id,name
     * @return text object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Text getText(Long id, String fields) throws CallfireApiException, CallfireClientException {
        List<NameValuePair> queryParams = new ArrayList<>();
        addQueryParamIfSet("fields", fields, queryParams);
        String path = TEXTS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, TEXT_TYPE, queryParams);
    }
}
