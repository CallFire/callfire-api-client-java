package com.callfire.api.client.api.campaigns.model;

import java.util.ArrayList;
import java.util.List;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetryConfig extends CallfireModel {
    private Integer maxAttempts;
    private Integer minutesBetweenAttempts;
    @Builder.Default private List<RetryResults> retryResults = new ArrayList<>();
    @Builder.Default private List<RetryPhoneTypes> retryPhoneTypes = new ArrayList<>();

    public enum RetryResults {
        LA,
        AM,
        BUSY,
        DNC,
        XFER,
        XFER_LEG,
        NO_ANS,
        UNDIALED,
        SENT,
        RECEIVED,
        DNT,
        TOO_BIG,
        INTERNAL_ERROR,
        CARRIER_ERROR,
        CARRIER_TEMP_ERROR,
        SD,
        POSTPONED,
        ABANDONED,
        SKIPPED
    }

    public enum RetryPhoneTypes {
        FIRST_NUMBER,
        HOME_PHONE,
        WORK_PHONE,
        MOBILE_PHONE
    }
}
