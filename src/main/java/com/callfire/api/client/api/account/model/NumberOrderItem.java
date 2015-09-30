package com.callfire.api.client.api.account.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class NumberOrderItem extends CallfireModel {
    private Integer ordered = 0;
    private Double unitCost;
    private List<String> fulfilled = new ArrayList<>();

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public List<String> getFulfilled() {
        return fulfilled;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("ordered", ordered)
            .append("unitCost", unitCost)
            .append("fulfilled", fulfilled)
            .toString();
    }
}
