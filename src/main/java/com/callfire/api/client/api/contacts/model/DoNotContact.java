package com.callfire.api.client.api.contacts.model;

import java.util.Date;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

@Getter
public class DoNotContact extends CallfireModel {
    private String number;
    private Boolean call;
    private Boolean text;
    private String source;
    private Long campaignId;
    private Date created;
}
