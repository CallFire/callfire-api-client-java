package com.callfire.api.client.api.common.model;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.io.Serializable;

import com.callfire.api.client.ModelValidationException;

/**
 * Abstract model object
 */
public abstract class CallfireModel implements Serializable {

    /**
     * Override in case you need additional validation of entity state
     *
     * @throws ModelValidationException in case validation is failed
     */
    public void validate() {
    }

    public static String toString(final Object object) {
        return reflectionToString(object, SHORT_PREFIX_STYLE);
    }

    @Override
    public String toString() {
        return toString(this);
    }
}
