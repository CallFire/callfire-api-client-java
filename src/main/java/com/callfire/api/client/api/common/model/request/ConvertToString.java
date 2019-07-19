package com.callfire.api.client.api.common.model.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Converts iterable field to string representation using provided separator char
 * [A, B, C] to "A,B,C"
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConvertToString {
    /**
     * Separator between joined strings. Comma by default.
     *
     * @return values separator
     */
    String separator() default ",";
}
