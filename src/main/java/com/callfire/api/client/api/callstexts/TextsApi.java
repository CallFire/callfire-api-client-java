package com.callfire.api.client.api.callstexts;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.callstexts.model.Text;
import com.callfire.api.client.api.callstexts.model.request.FindTextsRequest;
import com.callfire.api.client.api.campaigns.model.TextRecipient;
import com.callfire.api.client.api.common.model.ListHolder;
import com.callfire.api.client.api.common.model.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /texts
 *
 * @since 1.0
 */
public class TextsApi {
    private static final String TEXTS_PATH = "/texts";
    private static final String TEXTS_ITEM_PATH = "/texts/{}";
    private static final TypeReference<Text> TEXT_TYPE = new TypeReference<Text>() {
    };
    private static final TypeReference<ListHolder<Text>> LIST_OF_TEXTS_TYPE = new TypeReference<ListHolder<Text>>() {
    };
    public static final TypeReference<Page<Text>> PAGE_OF_TEXTS_TYPE = new TypeReference<Page<Text>>() {
    };

    private RestApiClient client;

    public TextsApi(RestApiClient client) {
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
    public Page<Text> find(FindTextsRequest request) {
        return client.get(TEXTS_PATH, PAGE_OF_TEXTS_TYPE, request);
    }

    /**
     * Get text by id.
     *
     * @param id id of text
     * @return text object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Text get(Long id) {
        return get(id, null);
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
    public Text get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = TEXTS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, TEXT_TYPE, queryParams);
    }

    /**
     * Send texts to recipients through default campaign
     *
     * @param recipients text recipients
     * @return list of {@link Text}
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<Text> send(List<TextRecipient> recipients) {
        return send(recipients, null, null);
    }

    /**
     * Send texts to recipients through existing campaign, if null default campaign will be used
     *
     * @param recipients text recipients
     * @param campaignId id of outbound campaign
     * @param fields     limit fields returned. Example items(id,name,fromNumber)
     * @return list of {@link Text}
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<Text> send(List<TextRecipient> recipients, Long campaignId, String fields) {
        List<NameValuePair> queryParams = new ArrayList<>(2);
        addQueryParamIfSet("campaignId", campaignId, queryParams);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.post(TEXTS_PATH, LIST_OF_TEXTS_TYPE, recipients, queryParams).getItems();
    }
}
