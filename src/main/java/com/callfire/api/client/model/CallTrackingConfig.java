package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class CallTrackingConfig extends BaseModel {
    private Boolean screen;
    private Boolean recorded;
    private Long introSoundId;
    private Long whisperSoundId;
    private List<String> transferNumbers = new ArrayList<>();

    public Boolean getScreen() {
        return screen;
    }

    public void setScreen(Boolean screen) {
        this.screen = screen;
    }

    public Boolean getRecorded() {
        return recorded;
    }

    public void setRecorded(Boolean recorded) {
        this.recorded = recorded;
    }

    public Long getIntroSoundId() {
        return introSoundId;
    }

    public void setIntroSoundId(Long introSoundId) {
        this.introSoundId = introSoundId;
    }

    public Long getWhisperSoundId() {
        return whisperSoundId;
    }

    public void setWhisperSoundId(Long whisperSoundId) {
        this.whisperSoundId = whisperSoundId;
    }

    public List<String> getTransferNumbers() {
        return transferNumbers;
    }

    public void setTransferNumbers(List<String> transferNumbers) {
        this.transferNumbers = transferNumbers;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("screen", screen)
            .append("recorded", recorded)
            .append("introSoundId", introSoundId)
            .append("whisperSoundId", whisperSoundId)
            .append("transferNumbers", transferNumbers)
            .toString();
    }
}
