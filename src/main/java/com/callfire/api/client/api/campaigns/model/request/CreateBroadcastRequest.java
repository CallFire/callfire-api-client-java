package com.callfire.api.client.api.campaigns.model.request;

import com.callfire.api.client.api.campaigns.model.Broadcast;
import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CreateBroadcastRequest<T extends Broadcast> extends CallfireModel {
    @JsonIgnore
    private T broadcast;
    private Boolean strictValidation;
    private Boolean start;

    /**
     * Create request builder
     *
     * @return request build
     */
    public static <T extends Broadcast> Builder<T> create() {
        return new Builder();
    }

    public T getBroadcast() {
        return broadcast;
    }

    public Boolean getStrictValidation() {
        return strictValidation;
    }

    public Boolean getStart() {
        return start;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("broadcast", broadcast)
            .append("strictValidation", strictValidation)
            .append("start", start)
            .toString();
    }

    public static class Builder<T extends Broadcast> extends AbstractBuilder<CreateBroadcastRequest<T>> {
        private Builder() {
            super(new CreateBroadcastRequest<T>());
        }

        public Builder<T> broadcast(T broadcast) {
            request.broadcast = broadcast;
            return this;
        }

        public Builder<T> strictValidation(Boolean strictValidation) {
            request.strictValidation = strictValidation;
            return this;
        }

        public Builder<T> start(Boolean start) {
            request.start = start;
            return this;
        }

        @Override
        public CreateBroadcastRequest build() {
            Validate.notNull(request.broadcast, "request.broadcast cannot be null");
            return super.build();
        }
    }
}
