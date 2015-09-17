package com.callfire.api.client.endpoint;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.model.Keyword;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ApiEndpoints.Type.BOOLEAN_TYPE;
import static com.callfire.api.client.ClientConstants.PLACEHOLDER;

/**
 * Represents rest endpoint /keywords
 */
public class KeywordsEndpoint {
    private static final String KEYWORDS_PATH = "/keywords";
    private static final String KEYWORD_AVAILABLE_PATH = "/keywords/{}/available";
    private static final TypeReference<List<Keyword>> KEYWORDS_LIST_TYPE = new TypeReference<List<Keyword>>() {
    };

    private RestApiClient client;

    public KeywordsEndpoint(RestApiClient client) {
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
    public List<Keyword> getKeywordsInCatalog(List<String> keywords)
        throws CallfireApiException, CallfireClientException {
        List<NameValuePair> queryParams = new ArrayList<>(keywords.size());
        keywords.stream().forEach(k -> queryParams.add(new BasicNameValuePair("keywords", k)));
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
    public Boolean isKeywordAvailable(String keyword) throws CallfireApiException, CallfireClientException {
        return client.get(KEYWORD_AVAILABLE_PATH.replaceFirst(PLACEHOLDER, keyword), BOOLEAN_TYPE);
    }
}
