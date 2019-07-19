package com.callfire.api.client.api.campaigns.model;

import java.util.ArrayList;
import java.util.List;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Question extends CallfireModel {
    private ResponseType responseType;
    private String question;
    private List<String> choices = new ArrayList<>();

    public enum ResponseType {
        STRING,
        CHOICE,
        NUMERIC
    }
}
