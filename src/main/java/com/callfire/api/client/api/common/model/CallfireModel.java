package com.callfire.api.client.api.common.model;

import com.callfire.api.client.ModelValidationException;

import java.io.Serializable;

/**
 * Abstract model object
 */
public abstract class CallfireModel implements Serializable {

    /**
     * Override in case you need additional validation of entity state
     *
     * @throws ModelValidationException
     */
    public void validate() {
    }
}
