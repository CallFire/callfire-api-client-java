package com.callfire.api.client.api.numbers.model;

import java.util.List;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.WeeklySchedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallTrackingConfig extends CallfireModel {
    private Boolean screen;
    private Boolean recorded;
    private Long introSoundId;
    private Long whisperSoundId;
    private List<String> transferNumbers;
    private Boolean voicemail;
    private Long voicemailSoundId;
    private Long failedTransferSoundId;
    private WeeklySchedule weeklySchedule;
    private GoogleAnalytics googleAnalytics;
}
