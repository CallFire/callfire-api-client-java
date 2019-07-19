package com.callfire.api.client.api.numbers.model;

import java.util.Date;
import java.util.List;

import com.callfire.api.client.api.keywords.model.LeaseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
public class NumberLease extends Number {
    private Date leaseBegin;
    private Date leaseEnd;
    private LeaseStatus status;
    @Setter private Boolean autoRenew;
    @Setter private FeatureStatus callFeatureStatus;
    @Setter private FeatureStatus textFeatureStatus;
    @Setter private List<String> labels;

    public enum FeatureStatus {
        UNSUPPORTED,
        PENDING,
        DISABLED,
        ENABLED
    }
}
