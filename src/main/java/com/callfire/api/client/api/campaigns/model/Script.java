package com.callfire.api.client.api.campaigns.model;

import java.util.ArrayList;
import java.util.List;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class Script extends CallfireModel {
    private String content;
    private List<Question> questions = new ArrayList<>();
}
