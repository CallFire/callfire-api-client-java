package com.callfire.api.client.api.account.model.request;

import java.util.Date;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Contains intervalBegin and intervalEnd fields for date range filtering
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DateIntervalRequest extends CallfireModel {
    /**
     * Gets beginning of filtering period
     *
     * @return beginning of filtering period
     */
    private Date intervalBegin;

    /**
     * Gets end of filtering period
     *
     * @return end of filtering period
     */
    private Date intervalEnd;

    @Deprecated
    public static Builder create() {
        return new Builder();
    }

    @Deprecated
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
