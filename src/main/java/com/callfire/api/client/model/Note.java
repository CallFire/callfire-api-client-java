package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Note extends BaseModel {
    private String text;
    private Long created;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("text", text)
            .append("created", created)
            .toString();
    }
}
