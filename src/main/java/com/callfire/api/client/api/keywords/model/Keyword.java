package com.callfire.api.client.api.keywords.model;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Keyword extends CallfireModel {

    /**
     * Short code assigned
     *
     * @param shortCode short code assigned
     * @return short code assigned
     */
    private String shortCode;

    /**
     * Keyword for short code
     *
     * @param keyword keyword for short code
     * @return keyword for short code
     */
    private String keyword;
}
