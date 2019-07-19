package com.callfire.api.client.api.contacts.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;

import lombok.Getter;

/**
 * Abstract request object for controlling calls/texts flags availability
 */
@Getter
public abstract class CallsTextsRequest extends CallfireModel {

    /**
     * Call flag
     *
     * @return Dnc call flag
     */
    protected Boolean call;

    /**
     * Text flag
     *
     * @return Dnc text flag
     */
    protected Boolean text;

    /**
     * Builder class
     */
    @SuppressWarnings("unchecked")
    public static abstract class CallsTextsBuilder<B extends CallsTextsBuilder, R extends CallsTextsRequest>
            extends AbstractBuilder<R> {

        public CallsTextsBuilder(R request) {
            super(request);
        }

        /**
         * Set Dnc call flag
         *
         * @param call Dnc call flag
         * @return builder self-reference
         */
        public B call(Boolean call) {
            request.call = call;
            return (B) this;
        }

        /**
         * Set Dnc text flag
         *
         * @param text Dnc text flag
         * @return builder self-reference
         */
        public B text(Boolean text) {
            request.text = text;
            return (B) this;
        }
    }
}
