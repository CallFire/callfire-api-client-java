package com.callfire.api.client.api.contacts.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class ContactList extends CallfireModel {
    private Long id;
    private String name;
    private Integer size;
    private Date created;
    private Status status;

    public enum Status {
        ACTIVE, VALIDATING, IMPORTING, IMPORT_FAILED, ERRORS, DELETED, PARSE_FAILED, COLUMN_TOO_LARGE
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("name", name)
            .append("size", size)
            .append("created", created)
            .append("status", status)
            .toString();
    }
}
