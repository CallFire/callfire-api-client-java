package com.callfire.api.client.integration.endpoint.keywords;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.keywords.model.Keyword;
import com.callfire.api.client.integration.endpoint.AbstractIntegrationTest;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * integration tests for /texts api endpoint
 */
public class KeywordsApiTest extends AbstractIntegrationTest {

    @Test
    public void testGetKeywordsInCatalog() throws Exception {
        CallfireClient client = getCallfireClient();
        String KW1 = "callfire";
        String KW2 = "TEST1";
        String KW3 = "TEST2";

        List<Keyword> keywords = client.getKeywordsApi().getKeywordsInCatalog(asList(KW1, KW2, KW3));
        assertEquals(2, keywords.size());
        assertThat(keywords, hasItem(Matchers.<Keyword>hasProperty("keyword", is(KW2))));
        assertThat(keywords, hasItem(Matchers.<Keyword>hasProperty("keyword", is(KW3))));
    }

    @Test
    public void testIsKeywordAvailable() throws Exception {
        CallfireClient client = getCallfireClient();
        assertTrue(client.getKeywordsApi().isKeywordAvailable("TEST"));
        assertTrue(client.getKeywordsApi().isKeywordAvailable("KW"));
        assertFalse(client.getKeywordsApi().isKeywordAvailable("callfire"));
    }
}
