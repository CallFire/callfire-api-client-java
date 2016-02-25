package com.callfire.api.client;

/**
 * Client constants
 *
 * @since 1.0
 */
public interface ClientConstants {
    String BASE_PATH_PROPERTY = "com.callfire.api.client.path";
    String USER_AGENT_PROPERTY = "com.callfire.api.client.version";
    String PROXY_ADDRESS_PROPERTY = "com.callfire.api.client.proxy.address";
    String PROXY_CREDENTIALS_PROPERTY = "com.callfire.api.client.proxy.credentials";

	int DEFAULT_PROXY_PORT = 8080;

    String CLIENT_CONFIG_FILE = "/com/callfire/api/client/callfire.properties";

    String PLACEHOLDER = "\\{\\}";
    // Use ISO 8601 format for date and datetime.
    // See https://en.wikipedia.org/wiki/ISO_8601
    String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    String GENERIC_HELP_LINK = "https://answers.callfire.com/hc/en-us";

}
