package com.callfire.api.client.api.contacts.model;

import java.util.Date;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

@Getter
public class ContactList extends CallfireModel {
    private Long id;
    private String name;
    private Integer size;
    private Date created;
    private Status status;

    public enum Status {
        ACTIVE,
        VALIDATING,
        IMPORTING,
        IMPORT_FAILED,
        ERRORS, DELETED,
        PARSE_FAILED,
        COLUMN_TOO_LARGE
    }
}
