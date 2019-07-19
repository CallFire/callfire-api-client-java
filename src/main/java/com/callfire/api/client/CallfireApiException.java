package com.callfire.api.client;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.ErrorMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Callfire API exception is thrown by client in case of 4xx or 5xx HTTP code
 * response
 *
 * @since 1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class CallfireApiException extends RuntimeException {

    /**
     * Detailed error message with HTTP code, help link, etc.
     *
     * @param apiErrorMessage detailed message
     * @return detailed message
     */
    protected ErrorMessage apiErrorMessage;

    @Override
    public String toString() {
        return CallfireModel.toString(this);
    }
}
