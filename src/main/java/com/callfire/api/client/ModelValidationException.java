package com.callfire.api.client;

import lombok.NoArgsConstructor;

/**
 * Exception is used by Callfire model validation methods
 *
 * @since 1.6
 */
@NoArgsConstructor
public class ModelValidationException extends CallfireClientException {
    public ModelValidationException(String message) {
        super(message);
    }
}
