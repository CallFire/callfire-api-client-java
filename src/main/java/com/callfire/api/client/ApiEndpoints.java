package com.callfire.api.client;

import com.callfire.api.client.model.Page;
import com.callfire.api.client.model.ResourceId;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;

/**
 * Contains Callfire API v2 endpoint URLs
 */
public interface ApiEndpoints {
    String ADMIN_STATS_PATH = "/admin/stats";
    String ADMIN_CALLERIDS_PATH = "/admin/callerids";
    String ADMIN_CALLERIDS_CODE_PATH = "/admin/callerids/{}";
    String ADMIN_CALLERIDS_VERIFY_PATH = "/admin/callerids/{}/verification-code";

    String AGENT_GROUPS_PATH = "/agent-groups";
    String AGENT_GROUPS_ITEM_PATH = "/agent-groups/{}";

    String ME_ACCOUNT_PATH = "/me/account";
    String ME_BILLING_PATH = "/me/billing/plan-usage";

    String SUBSCRIPTIONS_PATH = "/subscriptions";
    String SUBSCRIPTIONS_ITEM_PATH = "/subscriptions/{}";

    /**
     * Jackson TypeReference types for valid serialization/deserialization
     */
    interface Type {
        TypeReference<String> STRING_TYPE = new TypeReference<String>() {
        };
        TypeReference<Boolean> BOOLEAN_TYPE = new TypeReference<Boolean>() {
        };
        TypeReference<InputStream> INPUT_STREAM_TYPE = new TypeReference<InputStream>() {
        };
        TypeReference<ResourceId> RESOURCE_ID_TYPE = new TypeReference<ResourceId>() {
        };
        TypeReference<Page<ResourceId>> PAGE_OF_RESOURCE_ID_TYPE = new TypeReference<Page<ResourceId>>() {
        };
    }
}
