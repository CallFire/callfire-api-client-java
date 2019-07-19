package com.callfire.api.client.api.callstexts.model;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

/**
 * Media for MMS message
 */
@Getter
public class Media extends CallfireModel {
    private Long id;
    private Long accountId;
    private String name;
    private Long created;
    private Long lengthInBytes;
    private MediaType mediaType;
    private String publicUrl;
}
