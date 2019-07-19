package com.callfire.api.client.api.contacts.model;

import java.util.ArrayList;
import java.util.List;

import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.callstexts.model.Text;
import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

@Getter
public class ContactHistory extends CallfireModel {
    private Long id;
    private List<Call> calls = new ArrayList<>();
    private List<Text> texts = new ArrayList<>();
}
