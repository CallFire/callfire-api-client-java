package com.callfire.api.client;

import com.callfire.api.client.api.common.model.ErrorMessage;

/**
 * Exception thrown in case if platform returns HTTP code 500 - Internal Server Error
 *
 * @since 1.5
 */
public class InternalServerErrorException extends CallfireApiException {
    public InternalServerErrorException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
