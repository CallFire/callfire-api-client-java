package com.callfire.api.client;

/**
 * Exception is used by Callfire model validation methods
 *
 * @since 1.6
 */
public class ModelValidationException extends CallfireClientException {
    public ModelValidationException() {
    }

    public ModelValidationException(String message) {
        super(message);
    }
}
