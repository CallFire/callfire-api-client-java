package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Stats extends BaseModel {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("name", name)
            .toString();
    }
}
