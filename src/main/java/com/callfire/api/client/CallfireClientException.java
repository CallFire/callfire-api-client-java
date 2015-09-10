package com.callfire.api.client;

/**
 * Thrown by client in case of invalid instantiation
 */
public class CallfireClientException extends RuntimeException {
    public CallfireClientException() {
    }

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
