package com.callfire.api.client;

/**
 * Contains Callfire API v2 endpoint URLs
 */
public interface ApiEndpoints {

    String ADMIN_STATS_PATH = "/admin/stats";
    String ADMIN_CALLERIDS_PATH = "/admin/callerids";
    String ADMIN_CALLERIDS_CODE_PATH = "/admin/callerids/{}";
    String ADMIN_CALLERIDS_VERIFY_PATH = "/admin/callerids/{}/verification-code";

    String AGENT_GROUPS_PATH = "/agent-groups";
    String AGENT_GROUPS_GROUP_PATH = "/agent-groups/{}";

    String ME_ACCOUNT_PATH = "/me/account";
    String ME_BILLING_PATH = "/me/billing/plan-usage";
}
