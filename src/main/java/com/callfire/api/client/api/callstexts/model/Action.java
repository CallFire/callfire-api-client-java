package com.callfire.api.client.api.callstexts.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.contacts.model.Contact;

import lombok.Getter;

@Getter
public class Action<T extends ActionRecord> extends CallfireModel {
    private Long id;
    private String fromNumber;
    private String toNumber;
    private Long campaignId;
    private Long batchId;
    private Contact contact;
    private Boolean inbound;
    private Date created;
    private Date modified;
    private State state;
    private List<String> labels = new ArrayList<>();
    private Map<String, String> attributes = new HashMap<>();
    private List<T> records = new ArrayList<>();

    public enum State {
        // non-terminal
        READY,
        SELECTED,
        CALLBACK,

        // terminal
        FINISHED,
        DISABLED,
        DNC,
        DUP,
        INVALID,
        TIMEOUT,
        PERIOD_LIMIT
    }
}
