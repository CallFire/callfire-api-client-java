package com.callfire.api.client;

import com.callfire.api.client.api.common.model.ErrorMessage;

/**
 * Exception thrown in case if platform returns HTTP code 401 - Unauthorized, API Key missing or invalid
 *
 * @since 1.5
 */
public class UnauthorizedException extends CallfireApiException {
    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
