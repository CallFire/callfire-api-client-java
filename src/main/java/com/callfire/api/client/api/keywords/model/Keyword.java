package com.callfire.api.client.api.keywords.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Keyword extends CallfireModel {
    private String shortCode;
    private String keyword;

    /**
     * Get short code assigned
     *
     * @return short code assigned
     */
    public String getShortCode() {
        return shortCode;
    }

    /**
     * Set short code assigned
     *
     * @param shortCode short code assigned
     */
    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    /**
     * Get keyword for short code
     *
     * @return keyword for short code
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Set keyword for short code
     *
     * @param keyword keyword for short code
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("shortCode", shortCode)
            .append("keyword", keyword)
            .toString();
    }
}
