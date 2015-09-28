package com.callfire.api.client;

import com.callfire.api.client.api.account.MeApi;
import com.callfire.api.client.api.account.OrdersApi;
import com.callfire.api.client.api.callstexts.CallsApi;
import com.callfire.api.client.api.callstexts.TextsApi;
import com.callfire.api.client.api.campaigns.*;
import com.callfire.api.client.api.contacts.ContactListsApi;
import com.callfire.api.client.api.contacts.ContactsApi;
import com.callfire.api.client.api.contacts.DncApi;
import com.callfire.api.client.api.contacts.DncListsApi;
import com.callfire.api.client.api.keywords.KeywordLeasesApi;
import com.callfire.api.client.api.keywords.KeywordsApi;
import com.callfire.api.client.api.numbers.NumberLeasesApi;
import com.callfire.api.client.api.numbers.NumbersApi;
import com.callfire.api.client.api.webhooks.SubscriptionsApi;
import com.callfire.api.client.api.webhooks.WebhooksApi;
import com.callfire.api.client.auth.BasicAuth;

/**
 * Callfire API v2 client
 *
 * @author Vladimir Mikhailov (email: vmikhailov@callfire.com)
 * @version 1.0
 */
public class CallfireClient {
    private RestApiClient restApiClient;

    // campaigns
    private AgentsApi agentsApi;
    private AgentGroupsApi agentGroupsApi;
    private BatchesApi batchesApi;
    private CampaignSoundsApi campaignSoundsApi;
    private TextAutoRepliesApi textAutoRepliesApi;
    // keywords
    private KeywordsApi keywordsApi;
    private KeywordLeasesApi keywordLeasesApi;
    // numbers
    private NumbersApi numbersApi;
    private NumberLeasesApi numberLeasesApi;
    // calls & texts
    private CallsApi callsApi;
    private TextsApi textsApi;
    // account
    private MeApi meApi;
    private OrdersApi ordersApi;
    // contacts
    private ContactsApi contactsApi;
    private ContactListsApi contactListsApi;
    private DncApi dncApi;
    private DncListsApi dncListsApi;
    // webhooks
    private SubscriptionsApi subscriptionsApi;
    private WebhooksApi webhooksApi;

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
     * Get REST api client which uses Apache httpclient inside
     *
     * @return rest client
     */
    public RestApiClient getRestApiClient() {
        return restApiClient;
    }

    /**
     * Get /campaigns/cccs/agents api endpoint
     *
     * @return endpoint object
     */
    public AgentsApi getAgentsApi() {
        if (agentsApi == null) {
            agentsApi = new AgentsApi(restApiClient);
        }
        return agentsApi;
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
     * Get /contacts/do-not-calls/lists api endpoint
     *
     * @return endpoint object
     */
    public DncListsApi getDncListsApi() {
        if (dncListsApi == null) {
            dncListsApi = new DncListsApi(restApiClient);
        }
        return dncListsApi;
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

    /**
     * Get /contacts/do-not-calls api endpoint
     *
     * @return endpoint object
     */
    public DncApi getDncApi() {
        if (dncApi == null) {
            dncApi = new DncApi(restApiClient);
        }
        return dncApi;
    }

    /**
     * Get /contacts/lists api endpoint
     *
     * @return endpoint object
     */
    public ContactListsApi getContactListsApi() {
        if (contactListsApi == null) {
            contactListsApi = new ContactListsApi(restApiClient);
        }
        return contactListsApi;
    }
}
