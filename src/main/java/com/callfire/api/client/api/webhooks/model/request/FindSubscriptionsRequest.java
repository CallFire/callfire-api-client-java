package com.callfire.api.client.api.webhooks.model.request;

import com.callfire.api.client.api.common.model.request.GetRequest;
import com.callfire.api.client.api.webhooks.model.Subscription.NotificationFormat;
import com.callfire.api.client.api.webhooks.model.Subscription.TriggerEvent;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for GET /subscriptions which incapsulates
 * different query pairs
 *
 * @since 1.0
 */
public class FindSubscriptionsRequest extends GetRequest {
    private Long campaignId;
    private TriggerEvent trigger;
    private NotificationFormat format;
    private String fromNumber;
    private String toNumber;

    private FindSubscriptionsRequest() {
    }

    /**
     * Create request builder
     *
     * @return request build
     */
    public static Builder create() {
        return new Builder();
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
            .toString();
    }

    /**
     * Builder class for find method
     */
    public static class Builder extends GetRequestBuilder<Builder, FindSubscriptionsRequest> {
        private Builder() {
            super(new FindSubscriptionsRequest());
        }

        public Builder campaignId(Long campaignId) {
            request.campaignId = campaignId;
            return this;
        }

        public Builder trigger(TriggerEvent trigger) {
            request.trigger = trigger;
            return this;
        }

        public Builder format(NotificationFormat format) {
            request.format = format;
            return this;
        }

        public Builder fromNumber(String fromNumber) {
            request.fromNumber = fromNumber;
            return this;
        }

        public Builder toNumber(String toNumber) {
            request.toNumber = toNumber;
            return this;
        }
    }
}
