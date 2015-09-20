package com.callfire.api.client.model.request;

import com.callfire.api.client.model.BaseModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class KeywordPurchaseRequest extends BaseModel {
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

    public static class Builder {
        private KeywordPurchaseRequest request;

        public Builder setKeywords(List<String> keywords) {
            request.keywords = keywords;
            return this;
        }

        private Builder() {
            request = new KeywordPurchaseRequest();
        }

        public KeywordPurchaseRequest build() {
            return request;
        }
    }
}
