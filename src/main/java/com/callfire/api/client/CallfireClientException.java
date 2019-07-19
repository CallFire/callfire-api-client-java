package com.callfire.api.client;

import lombok.NoArgsConstructor;

/**
 * Thrown by client in case of invalid instantiation
 *
 * @since 1.0
 */
@NoArgsConstructor
public class CallfireClientException extends RuntimeException {
    public CallfireClientException(String message) {
        super(message);
    }

    public CallfireClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public CallfireClientException(Throwable cause) {
        super(cause);
    }
}
