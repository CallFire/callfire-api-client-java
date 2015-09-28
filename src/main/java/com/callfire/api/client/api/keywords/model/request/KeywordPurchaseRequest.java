package com.callfire.api.client.api.keywords.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class KeywordPurchaseRequest extends CallfireModel {
    private List<String> keywords = new ArrayList<>();

    private KeywordPurchaseRequest() {
    }

    public static Builder create() {
        return new Builder();
    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("keywords", keywords)
            .toString();
    }

    /**
     * Builder class for request
     */
    public static class Builder extends AbstractBuilder<KeywordPurchaseRequest> {
        public Builder keywords(List<String> keywords) {
            request.keywords = keywords;
            return this;
        }

        private Builder() {
            super(new KeywordPurchaseRequest());
        }
    }
}
