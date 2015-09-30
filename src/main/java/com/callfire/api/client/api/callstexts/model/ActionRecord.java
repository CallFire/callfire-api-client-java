package com.callfire.api.client.api.callstexts.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class ActionRecord extends CallfireModel {
    private Long id;
    private Double billedAmount;
    private Date finishTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBilledAmount() {
        return billedAmount;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("billedAmount", billedAmount)
            .append("finishTime", finishTime)
            .toString();
    }
}
