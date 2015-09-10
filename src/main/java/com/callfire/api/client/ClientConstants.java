package com.callfire.api.client;

/**
 * Client constants
 */
public interface ClientConstants {
    String CALLFIRE_HOST = "callfire.com";
    String BASE_PATH = "https://www.callfire.com/api/v2";
    String PLACEHOLDER = "\\{\\}";
    // Use ISO 8601 format for date and datetime.
    // See https://en.wikipedia.org/wiki/ISO_8601
    String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    // TODO vmikhailov add versioning to user agent
    String USER_AGENT = "Java-Client-0.1-SNAPSHOT";
}
