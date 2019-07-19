package com.callfire.api.client;

import com.callfire.api.client.api.common.model.ErrorMessage;

/**
 * Exception thrown in case if platform returns HTTP code 403 - Forbidden, insufficient permissions
 *
 * @since 1.5
 */
public class AccessForbiddenException extends CallfireApiException {
    public AccessForbiddenException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
