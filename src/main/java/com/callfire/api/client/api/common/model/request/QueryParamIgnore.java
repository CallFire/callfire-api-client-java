package com.callfire.api.client.api.common.model.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excludes selected field from http query param
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface QueryParamIgnore {
    /**
     * Set annotation enabled. Enabled by default
     *
     * @return true if annotation enabled
     */
    boolean enabled() default true;
}
