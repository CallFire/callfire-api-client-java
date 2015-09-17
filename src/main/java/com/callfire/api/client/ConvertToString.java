package com.callfire.api.client;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Converts iterable field to string representation using provided separator char
 * [A, B, C] => "A,B,C"
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConvertToString {
    public String separator() default ",";
}
