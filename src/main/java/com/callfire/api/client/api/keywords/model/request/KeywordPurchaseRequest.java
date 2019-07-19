package com.callfire.api.client.api.keywords.model.request;

import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.List;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class KeywordPurchaseRequest extends CallfireModel {
    private List<String> keywords = new ArrayList<>();

    public static Builder create() {
        return new Builder();
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
