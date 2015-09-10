package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResourceId extends BaseModel {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .toString();
    }
}
