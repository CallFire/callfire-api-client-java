package com.callfire.api.client.api.callstexts.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ActionRecord extends CallfireModel {
    private Long id;
    private Double billedAmount;
    private Date finishTime;
    private String toNumber;
    private Set<String> labels = new HashSet<>();

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

    public String getToNumber() {
        return toNumber;
    }

    public Set<String> getLabels() {
        return labels;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("billedAmount", billedAmount)
            .append("finishTime", finishTime)
            .append("toNumber", toNumber)
            .append("labels", labels)
            .toString();
    }
}
