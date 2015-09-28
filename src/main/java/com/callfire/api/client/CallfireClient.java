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

import java.io.IOException;
import java.util.Properties;

import static com.callfire.api.client.ClientConstants.CLIENT_CONFIG_FILE;

/**
 * Callfire API v2 client
 *
 * @author Vladimir Mikhailov (email: vmikhailov@callfire.com)
 * @version 1.0
 */
public class CallfireClient {
    private static Properties clientConfig = new Properties();

    static {
        loadConfig();
    }

    private RestApiClient restApiClient;

    // campaigns
    private AgentsApi agentsApi;
    private AgentGroupsApi agentGroupsApi;
    private BatchesApi batchesApi;
    private CampaignSoundsApi campaignSoundsApi;
    private CccsApi cccsApi;
    private IvrBroadcastsApi ivrBroadcastsApi;
    private VoiceBroadcastsApi voiceBroadcastsApi;
    private TextBroadcastsApi textBroadcastsApi;
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
     * Get client configuration
     *
     * @return configuration properties
     */
    public static Properties getClientConfig() {
        return clientConfig;
    }

    /**
     * Get /campaigns/cccs/agents api endpoint
     *
     * @return endpoint object
     */
    public AgentsApi agentsApi() {
        if (agentsApi == null) {
            agentsApi = new AgentsApi(restApiClient);
        }
        return agentsApi;
    }

    // TODO vmikhailov temporary disabled

    /**
     * Get /campaigns/cccs api endpoint
     *
     * @return endpoint object
     */
    private CccsApi cccsApi() {
        if (cccsApi == null) {
            cccsApi = new CccsApi(restApiClient);
        }
        return cccsApi;
    }

    /**
     * Get /campaigns/cccs api endpoint
     *
     * @return endpoint object
     */
    public IvrBroadcastsApi ivrBroadcastsApi() {
        if (ivrBroadcastsApi == null) {
            ivrBroadcastsApi = new IvrBroadcastsApi(restApiClient);
        }
        return ivrBroadcastsApi;
    }

    /**
     * Get /campaigns/text-broadcasts api endpoint
     *
     * @return endpoint object
     */
    public TextBroadcastsApi textBroadcastsApi() {
        if (textBroadcastsApi == null) {
            textBroadcastsApi = new TextBroadcastsApi(restApiClient);
        }
        return textBroadcastsApi;
    }

    /**
     * Get /campaigns/voice-broadcasts api endpoint
     *
     * @return endpoint object
     */
    public VoiceBroadcastsApi voiceBroadcastsApi() {
        if (voiceBroadcastsApi == null) {
            voiceBroadcastsApi = new VoiceBroadcastsApi(restApiClient);
        }
        return voiceBroadcastsApi;
    }

    /**
     * Get /me api endpoint
     *
     * @return endpoint object
     */
    public MeApi meApi() {
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
    public ContactsApi contactsApi() {
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
    public SubscriptionsApi subscriptionsApi() {
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
    public TextsApi textsApi() {
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
    public KeywordsApi keywordsApi() {
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
    public KeywordLeasesApi keywordLeasesApi() {
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
    public NumbersApi numbersApi() {
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
    public NumberLeasesApi numberLeasesApi() {
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
    public CallsApi callsApi() {
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
    public OrdersApi ordersApi() {
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
    public WebhooksApi webhooksApi() {
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
    public AgentGroupsApi agentGroupsApi() {
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
    public CampaignSoundsApi campaignSoundsApi() {
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
    public DncListsApi dncListsApi() {
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
    public TextAutoRepliesApi textAutoRepliesApi() {
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
    public BatchesApi batchesApi() {
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
    public DncApi dncApi() {
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
    public ContactListsApi contactListsApi() {
        if (contactListsApi == null) {
            contactListsApi = new ContactListsApi(restApiClient);
        }
        return contactListsApi;
    }

    private static void loadConfig() {
        try {
            clientConfig.load(CallfireClient.class.getResourceAsStream(CLIENT_CONFIG_FILE));
        } catch (IOException e) {
            throw new CallfireClientException("Cannot instantiate Callfire Client.", e);
        }
    }
}
