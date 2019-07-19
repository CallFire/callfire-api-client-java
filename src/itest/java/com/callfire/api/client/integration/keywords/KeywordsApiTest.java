package com.callfire.api.client.integration.keywords;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.keywords.model.Keyword;
import com.callfire.api.client.integration.AbstractIntegrationTest;

/**
 * integration tests for /keywords api endpoint
 */
public class KeywordsApiTest extends AbstractIntegrationTest {

    @Test
    public void testGetKeywordsInCatalog() throws Exception {
        CallfireClient client = getCallfireClient();
        String KW1 = "TEST1";
        String KW2 = "TEST2";

        List<Keyword> keywords = client.keywordsApi().find(asList(KW1, KW2));
        assertEquals(2, keywords.size());
        assertThat(keywords, hasItem(Matchers.<Keyword>hasProperty("keyword", is(KW1))));
        assertThat(keywords, hasItem(Matchers.<Keyword>hasProperty("keyword", is(KW2))));
    }

    @Test
    public void testIsKeywordAvailable() throws Exception {
        CallfireClient client = getCallfireClient();
        assertTrue(client.keywordsApi().isAvailable("TEST"));
        assertTrue(client.keywordsApi().isAvailable("KW"));
    }
}
