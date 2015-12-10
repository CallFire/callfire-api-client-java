package com.callfire.api.client.api.callstexts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public enum MediaType {
    JPEG("jpeg"),
    PNG("png"),
    BMP("bmp"),
    GIF("gif"),
    WAV("wav"),
    MP3("mp3"),
    M4A("m4a"),
    MP4("mp4"),
    UNKNOWN("unknown");

    private String type;

    MediaType(String property) {
        this.type = property;
    }

    @JsonCreator
    public static MediaType fromValue(String type) {
        for (MediaType t : values()) {
            if (Objects.equals(String.valueOf(t.type), type)) {
                return t;
            }
        }
        throw new IllegalArgumentException("there is no type for MediaType: " + type);
    }

    @JsonValue
    public String toValue() {
        return type;
    }
}
