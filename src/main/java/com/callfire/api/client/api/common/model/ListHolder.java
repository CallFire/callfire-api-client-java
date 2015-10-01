package com.callfire.api.client.api.common.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class ListHolder<T> extends CallfireModel {
    protected List<T> items = new ArrayList<>();

    public ListHolder() {
    }

    public ListHolder(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("items", items)
            .toString();
    }
}
