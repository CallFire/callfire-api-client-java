package com.callfire.api.client.api.campaigns.model;

import java.util.Date;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

/**
 * CallRecording
 */
@Getter
public class CallRecording extends CallfireModel {
    private Long id;
    private Long callId;
    private Long campaignId;
    private String name;
    private Date created;
    private Long lengthInBytes;
    private Integer lengthInSeconds;
    private String hash;
    private String mp3Url;
    private CallRecordingState state;

    public enum CallRecordingState {
        RECORDING,
        READY,
        ERROR
    }
}
