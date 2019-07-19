package com.callfire.api.client.api.keywords.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
public class KeywordLease extends Keyword {
    private Date leaseBegin;
    private Date leaseEnd;
    @Setter private Boolean autoRenew;
    private LeaseStatus status;
}
