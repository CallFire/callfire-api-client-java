package com.callfire.api.client.api.callstexts.model;

import java.util.Date;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

@Getter
public class Note extends CallfireModel {
    private String text;
    private Date created;
}
