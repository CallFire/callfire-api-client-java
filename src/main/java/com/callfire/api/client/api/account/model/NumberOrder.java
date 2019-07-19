package com.callfire.api.client.api.account.model;

import java.util.Date;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

@Getter
public class NumberOrder extends CallfireModel {
    private Long id;
    private Status status;
    private Date created;
    private Double totalCost;
    private NumberOrderItem localNumbers;
    private NumberOrderItem tollFreeNumbers;
    private NumberOrderItem keywords;

    public enum Status {
        NEW,
        PROCESSING,
        FINISHED,
        ERRORED,
        VOID,
        WAIT_FOR_PAYMENT,
        ADJUSTED,
        APPROVE_TIER_ONE,
        APPROVE_TIER_TWO,
        REJECTED
    }
}
