package com.callfire.api.client;

import com.callfire.api.client.api.account.MeApi;
import com.callfire.api.client.api.account.OrdersApi;
import com.callfire.api.client.api.callstexts.CallsApi;
import com.callfire.api.client.api.callstexts.TextsApi;
import com.callfire.api.client.api.campaigns.AgentGroupsApi;
import com.callfire.api.client.api.campaigns.BatchesApi;
import com.callfire.api.client.api.campaigns.CampaignSoundsApi;
import com.callfire.api.client.api.campaigns.TextAutoRepliesApi;
import com.callfire.api.client.api.keywords.KeywordLeasesApi;
import com.callfire.api.client.api.numbers.NumberLeasesApi;
import com.callfire.api.client.api.webhooks.SubscriptionsApi;
import com.callfire.api.client.api.webhooks.WebhooksApi;
import com.callfire.api.client.auth.BasicAuth;
import com.callfire.api.client.api.contacts.ContactsApi;
import com.callfire.api.client.api.keywords.KeywordsApi;
import com.callfire.api.client.api.numbers.NumbersApi;

/**
 * Callfire API v2 client
 *
 * @author Vladimir Mikhailov (email: vmikhailov@callfire.com)
 * @version 1.0
 */
public class CallfireClient {
    private RestApiClient restApiClient;

    private SubscriptionsApi subscriptionsApi;

    private CallsApi callsApi;
    private ContactsApi contactsApi;
    private MeApi meApi;
    private TextsApi textsApi;
    private KeywordsApi keywordsApi;
    private KeywordLeasesApi keywordLeasesApi;
    private NumbersApi numbersApi;
    private NumberLeasesApi numberLeasesApi;
    private OrdersApi ordersApi;
    private WebhooksApi webhooksApi;
    private AgentGroupsApi agentGroupsApi;
    private CampaignSoundsApi campaignSoundsApi;
    private TextAutoRepliesApi textAutoRepliesApi;
    private BatchesApi batchesApi;

    /**
     * Constructs callfire client
     *
     * @param username api login
     * @param password api password
     */
    public CallfireClient(String username, String password) {
        restApiClient = new RestApiClient(new BasicAuth(username, password));
    }

    /**
     * Get /me api endpoint
     *
     * @return endpoint object
     */
    public MeApi getMeApi() {
        if (meApi == null) {
            meApi = new MeApi(restApiClient);
        }
        return meApi;
    }

    /**
     * Get /contacts api endpoint
     *
     * @return endpoint object
     */
    public ContactsApi getContactsApi() {
        if (contactsApi == null) {
            contactsApi = new ContactsApi(restApiClient);
        }
        return contactsApi;
    }

    /**
     * Get /subscriptions api endpoint
     *
     * @return endpoint object
     */
    public SubscriptionsApi getSubscriptionsApi() {
        if (subscriptionsApi == null) {
            subscriptionsApi = new SubscriptionsApi(restApiClient);
        }
        return subscriptionsApi;
    }

    /**
     * Get /texts endpoint
     *
     * @return endpoint object
     * @see TextsApi
     */
    public TextsApi getTextsApi() {
        if (textsApi == null) {
            textsApi = new TextsApi(restApiClient);
        }
        return textsApi;
    }

    /**
     * Get /keywords endpoint
     *
     * @return endpoint object
     */
    public KeywordsApi getKeywordsApi() {
        if (keywordsApi == null) {
            keywordsApi = new KeywordsApi(restApiClient);
        }
        return keywordsApi;
    }

    /**
     * Get /keywords/leases endpoint
     *
     * @return endpoint object
     */
    public KeywordLeasesApi getKeywordLeasesApi() {
        if (keywordLeasesApi == null) {
            keywordLeasesApi = new KeywordLeasesApi(restApiClient);
        }
        return keywordLeasesApi;
    }

    /**
     * Get /numbers endpoint
     *
     * @return endpoint object
     */
    public NumbersApi getNumbersApi() {
        if (numbersApi == null) {
            numbersApi = new NumbersApi(restApiClient);
        }
        return numbersApi;
    }

    /**
     * Get /numbers/leases endpoint
     *
     * @return endpoint object
     */
    public NumberLeasesApi getNumberLeasesApi() {
        if (numberLeasesApi == null) {
            numberLeasesApi = new NumberLeasesApi(restApiClient);
        }
        return numberLeasesApi;
    }

    /**
     * Get /calls endpoint
     *
     * @return endpoint object
     */
    public CallsApi getCallsApi() {
        if (callsApi == null) {
            callsApi = new CallsApi(restApiClient);
        }
        return callsApi;
    }

    /**
     * Get /orders endpoint
     *
     * @return endpoint object
     */
    public OrdersApi getOrdersApi() {
        if (ordersApi == null) {
            ordersApi = new OrdersApi(restApiClient);
        }
        return ordersApi;
    }

    /**
     * Get /webhooks endpoint
     *
     * @return endpoint object
     */
    public WebhooksApi getWebhooksApi() {
        if (webhooksApi == null) {
            webhooksApi = new WebhooksApi(restApiClient);
        }
        return webhooksApi;
    }

    /**
     * Get /campaigns/cccs/agent-groups endpoint
     *
     * @return endpoint object
     */
    public AgentGroupsApi getAgentGroupsApi() {
        if (agentGroupsApi == null) {
            agentGroupsApi = new AgentGroupsApi(restApiClient);
        }
        return agentGroupsApi;
    }

    /**
     * Get /campaigns/sounds endpoint
     *
     * @return endpoint object
     */
    public CampaignSoundsApi getCampaignSoundsApi() {
        if (campaignSoundsApi == null) {
            campaignSoundsApi = new CampaignSoundsApi(restApiClient);
        }
        return campaignSoundsApi;
    }

    /**
     * Get REST api client which uses Apache httpclient inside
     *
     * @return rest client
     */
    public RestApiClient getRestApiClient() {
        return restApiClient;
    }

    /**
     * Get /campaigns/text-auto-replys endpoint
     *
     * @return endpoint object
     */
    public TextAutoRepliesApi getTextAutoRepliesApi() {
        if (textAutoRepliesApi == null) {
            textAutoRepliesApi = new TextAutoRepliesApi(restApiClient);
        }
        return textAutoRepliesApi;
    }

    /**
     * Get /campaigns/batches endpoint
     *
     * @return endpoint object
     */
    public BatchesApi getBatchesApi() {
        if (batchesApi == null) {
            batchesApi = new BatchesApi(restApiClient);
        }
        return batchesApi;
    }
}
