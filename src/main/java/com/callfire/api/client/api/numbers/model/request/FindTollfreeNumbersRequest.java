package com.callfire.api.client.api.numbers.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FindTollfreeNumbersRequest extends CallfireModel {
    private String pattern;
    private Long limit;
    private String fields;

    private FindTollfreeNumbersRequest() {
    }

    public static Builder create() {
        return new Builder();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("pattern", pattern)
            .append("limit", limit)
            .append("fields", fields)
            .toString();
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
