package com.callfire.api.client.api.account.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * Contains intervalBegin and intervalEnd fields for date range filtering
 */
public class DateIntervalRequest extends CallfireModel {
    private Date intervalBegin;
    private Date intervalEnd;

    /**
     * Gets beginning of filtering period
     *
     * @return beginning of filtering period
     */
    public Date getIntervalBegin() {
        return intervalBegin;
    }

    /**
     * Gets end of filtering period
     *
     * @return end of filtering period
     */
    public Date getIntervalEnd() {
        return intervalEnd;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("intervalBegin", intervalBegin)
            .append("intervalEnd", intervalEnd)
            .toString();
    }

    public static Builder create() {
        return new Builder();
    }

    @SuppressWarnings("unchecked")
    public static class Builder extends AbstractBuilder<DateIntervalRequest> {

        private Builder() {
            super(new DateIntervalRequest());
        }

        public Builder intervalBegin(Date intervalBegin) {
            request.intervalBegin = intervalBegin;
            return this;
        }

        public Builder intervalEnd(Date intervalEnd) {
            request.intervalEnd = intervalEnd;
            return this;
        }
    }
}
