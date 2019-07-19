package com.callfire.api.client.api.numbers.model.request;

import static lombok.AccessLevel.PRIVATE;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class FindTollfreeNumbersRequest extends CallfireModel {
    private String pattern;
    private Long limit;
    private String fields;

    public static Builder create() {
        return new Builder();
    }

    public static class Builder extends AbstractBuilder<FindTollfreeNumbersRequest> {

        private Builder() {
            super(new FindTollfreeNumbersRequest());
        }

        public Builder pattern(String pattern) {
            request.pattern = pattern;
            return this;
        }

        public Builder limit(Long limit) {
            request.limit = limit;
            return this;
        }

        public Builder fields(String fields) {
            request.fields = fields;
            return this;
        }
    }
}
