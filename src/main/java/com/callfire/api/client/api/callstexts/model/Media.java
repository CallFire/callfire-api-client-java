package com.callfire.api.client.api.callstexts.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Media for MMS message
 */
public class Media extends CallfireModel {
    private Long id;
    private Long accountId;
    private String name;
    private Long created;
    private Long lengthInBytes;
    private MediaType mediaType;
    private String publicUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreated() {
        return created;
    }

    public Long getLengthInBytes() {
        return lengthInBytes;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("accountId", accountId)
            .append("name", name)
            .append("created", created)
            .append("lengthInBytes", lengthInBytes)
            .append("mediaType", mediaType)
            .append("publicUrl", publicUrl)
            .toString();
    }
}
