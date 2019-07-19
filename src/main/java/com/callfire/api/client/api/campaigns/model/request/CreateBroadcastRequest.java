package com.callfire.api.client.api.campaigns.model.request;

import static lombok.AccessLevel.PRIVATE;

import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.callfire.api.client.api.campaigns.model.Broadcast;
import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class CreateBroadcastRequest<T extends Broadcast> extends CallfireModel {
    @JsonIgnore private T broadcast;
    private Boolean strictValidation;
    private Boolean start;

    /**
     * Create request builder
     *
     * @param <T> type of broadcast
     * @return request builder
     */
    public static <T extends Broadcast> Builder<T> create() {
        return new Builder<>();
    }

    public static class Builder<T extends Broadcast> extends AbstractBuilder<CreateBroadcastRequest<T>> {
        private Builder() {
            super(new CreateBroadcastRequest<>());
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
        public CreateBroadcastRequest<T> build() {
            Validate.notNull(request.broadcast, "request.broadcast cannot be null");
            return super.build();
        }
    }
}
