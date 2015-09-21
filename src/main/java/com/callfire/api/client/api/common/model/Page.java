package com.callfire.api.client.api.common.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class Page<T> extends CallfireModel {
    private Long limit;
    private Long offset;
    private Long totalCount;
    private List<T> items = new ArrayList<T>();

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
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
            .append("limit", limit)
            .append("offset", offset)
            .append("totalCount", totalCount)
            .append("items", items)
            .toString();
    }
}
