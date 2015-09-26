package com.callfire.api.client.api.keywords;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.keywords.model.Keyword;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.Type.BOOLEAN_TYPE;
import static com.callfire.api.client.ClientConstants.PLACEHOLDER;

/**
 * Represents rest endpoint /keywords
 *
 * @since 1.0
 */
public class KeywordsApi {
    private static final String KEYWORDS_PATH = "/keywords";
    private static final String KEYWORD_AVAILABLE_PATH = "/keywords/{}/available";
    private static final TypeReference<List<Keyword>> KEYWORDS_LIST_TYPE = new TypeReference<List<Keyword>>() {
    };

    private RestApiClient client;

    public KeywordsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find keywords by name. If queried keyword appears in the list, it is available.
     *
     * @param keywords keywords to find
     * @return available keywords
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<Keyword> find(List<String> keywords) {
        List<NameValuePair> queryParams = new ArrayList<>(keywords.size());
        for (String keyword : keywords) {
            queryParams.add(new BasicNameValuePair("keywords", keyword));
        }
        return client.get(KEYWORDS_PATH, KEYWORDS_LIST_TYPE, queryParams);
    }

    /**
     * Find the status of an individual keyword
     *
     * @param keyword keyword to check status of, can send multiple keyword params
     * @return true if keyword is available, otherwise false
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Boolean isAvailable(String keyword) {
        Validate.notBlank(keyword, "keyword cannot be blank");
        return client.get(KEYWORD_AVAILABLE_PATH.replaceFirst(PLACEHOLDER, keyword), BOOLEAN_TYPE);
    }
}
