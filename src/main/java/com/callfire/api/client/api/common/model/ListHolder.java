package com.callfire.api.client.api.common.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListHolder<T> extends CallfireModel {
    protected List<T> items = new ArrayList<>();
}
