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
     * Builder class for findSubscriptions method
     */
    public static class Builder extends AbstractBuilder<Builder, FindSubscriptionRequest> {
        private Builder() {
            super(new FindSubscriptionRequest());
        }

        public Builder setCampaignId(Long campaignId) {
            request.campaignId = campaignId;
            return this;
        }

        public Builder setTrigger(TriggerEvent trigger) {
            request.trigger = trigger;
            return this;
        }

        public Builder setFormat(NotificationFormat format) {
            request.format = format;
            return this;
        }

        public Builder setFromNumber(String fromNumber) {
            request.fromNumber = fromNumber;
            return this;
        }

        public Builder setToNumber(String toNumber) {
            request.toNumber = toNumber;
            return this;
        }
    }
}
