package com.callfire.api.client.api.numbers.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class GoogleAnalytics extends CallfireModel {
    private String domain;
    private String googleAccountId;
    private String category;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getGoogleAccountId() {
        return googleAccountId;
    }

    public void setGoogleAccountId(String googleAccountId) {
        this.googleAccountId = googleAccountId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("domain", domain)
            .append("googleAccountId", googleAccountId)
            .append("category", category)
            .toString();
    }
}
