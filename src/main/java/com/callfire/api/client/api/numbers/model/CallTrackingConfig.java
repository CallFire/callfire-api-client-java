package com.callfire.api.client.api.numbers.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class CallTrackingConfig extends CallfireModel {
    private Boolean screen;
    private Boolean recorded;
    private Long introSoundId;
    private Long whisperSoundId;
    private List<String> transferNumbers = new ArrayList<>();
    private Boolean voicemail;
    private Long voicemailSoundId;
    private Long failedTransferSoundId;
    private WeeklySchedule weeklySchedule;
    private GoogleAnalytics googleAnalytics;

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

    public Boolean getVoicemail() {
        return voicemail;
    }

    public void setVoicemail(Boolean voicemail) {
        this.voicemail = voicemail;
    }

    public Long getVoicemailSoundId() {
        return voicemailSoundId;
    }

    public void setVoicemailSoundId(Long voicemailSoundId) {
        this.voicemailSoundId = voicemailSoundId;
    }

    public Long getFailedTransferSoundId() {
        return failedTransferSoundId;
    }

    public void setFailedTransferSoundId(Long failedTransferSoundId) {
        this.failedTransferSoundId = failedTransferSoundId;
    }

    public WeeklySchedule getWeeklySchedule() {
        return weeklySchedule;
    }

    public void setWeeklySchedule(WeeklySchedule weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }

    public GoogleAnalytics getGoogleAnalytics() {
        return googleAnalytics;
    }

    public void setGoogleAnalytics(GoogleAnalytics googleAnalytics) {
        this.googleAnalytics = googleAnalytics;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("screen", screen)
            .append("recorded", recorded)
            .append("introSoundId", introSoundId)
            .append("whisperSoundId", whisperSoundId)
            .append("transferNumbers", transferNumbers)
            .append("voicemail", voicemail)
            .append("failedTransferSoundId", failedTransferSoundId)
            .append("voicemailSoundId", voicemailSoundId)
            .append("weeklySchedule", weeklySchedule)
            .append("googleAnalytics", googleAnalytics)
            .toString();
    }
}
