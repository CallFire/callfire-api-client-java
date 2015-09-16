package com.callfire.api.client.model.request;

import com.callfire.api.client.model.Subscription.NotificationFormat;
import com.callfire.api.client.model.Subscription.TriggerEvent;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for GET /subscription which incapsulates
 * different query pairs
 */
public class FindSubscriptionRequest extends FindRequest {
    private Long campaignId;
    private TriggerEvent trigger;
    private NotificationFormat format;
    private String fromNumber;
    private String toNumber;

    private FindSubscriptionRequest() {
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public TriggerEvent getTrigger() {
        return trigger;
    }

    public NotificationFormat getFormat() {
        return format;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public String getToNumber() {
        return toNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("campaignId", campaignId)
            .append("trigger", trigger)
            .append("format", format)
            .append("fromNumber", fromNumber)
            .append("toNumber", toNumber)
            .append("baseRequest", super.toString())
            .toString();
    }

    /**
     * Builder class for findSubscriptions method
     */
    public static class FindSubscriptionRequestBuilder extends FindRequestBuilder<FindSubscriptionRequestBuilder> {
        private FindSubscriptionRequest request;

        private FindSubscriptionRequestBuilder() {
            request = new FindSubscriptionRequest();
        }

        public static FindSubscriptionRequestBuilder create() {
            return new FindSubscriptionRequestBuilder();
        }

        public FindSubscriptionRequestBuilder setCampaignId(Long campaignId) {
            request.campaignId = campaignId;
            return this;
        }

        public FindSubscriptionRequestBuilder setTrigger(TriggerEvent trigger) {
            request.trigger = trigger;
            return this;
        }

        public FindSubscriptionRequestBuilder setFormat(NotificationFormat format) {
            request.format = format;
            return this;
        }

        public FindSubscriptionRequestBuilder setFromNumber(String fromNumber) {
            request.fromNumber = fromNumber;
            return this;
        }

        public FindSubscriptionRequestBuilder setToNumber(String toNumber) {
            request.toNumber = toNumber;
            return this;
        }

        @Override
        public FindSubscriptionRequest build() {
            return request;
        }

        @Override
        protected FindSubscriptionRequestBuilder getChild() {
            return this;
        }

        @Override
        protected FindSubscriptionRequest getRequest() {
            return request;
        }
    }
}
