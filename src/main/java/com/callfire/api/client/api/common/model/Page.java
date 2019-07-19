package com.callfire.api.client.api.common.model;

import lombok.Getter;

@Getter
public class Page<T> extends ListHolder<T> {
    private Long limit;
    private Long offset;
    private Long totalCount;
}
