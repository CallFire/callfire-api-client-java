package com.callfire.api.client.api.callstexts.model;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

@Getter
public class QuestionResponse extends CallfireModel {
    private String question;
    private String response;
}
