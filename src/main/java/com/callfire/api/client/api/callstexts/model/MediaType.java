package com.callfire.api.client.api.callstexts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public enum MediaType {
    JPEG("jpeg", "image/jpeg"),
    PNG("png", "image/png"),
    BMP("bmp", "image/bmp"),
    GIF("gif", "image/gif"),
    MP4("mp4", "video/mp4"),
    M4A("m4a", "audio/mp4"),
    MP3("mp3", "audio/mpeg"),
    WAV("wav", "audio/x-wav"),
    UNKNOWN("unknown", "application/octet-stream");

    private String type;
    private String mimeType;

    MediaType(String type, String mimeType) {
        this.type = type;
        this.mimeType = mimeType;
    }

    @JsonCreator
    public static MediaType fromMime(String mimeType) {
        for (MediaType t : values()) {
            if (Objects.equals(String.valueOf(t.mimeType), mimeType)) {
                return t;
            }
        }
        throw new IllegalArgumentException("there is no type for MediaType: " + mimeType);
    }

    public String getType() {
        return type;
    }

    @JsonValue
    public String getMimeType() {
        return mimeType;
    }

    @Override
    public String toString() {
        return mimeType;
    }
}
