package com.callfire.api.client.api.common.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class ResourceIds extends CallfireModel {
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("ids", ids)
            .toString();
    }
}
