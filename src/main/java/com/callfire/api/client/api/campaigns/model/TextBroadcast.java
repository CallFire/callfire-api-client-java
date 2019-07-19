package com.callfire.api.client.api.campaigns.model;

import java.util.ArrayList;
import java.util.List;

import com.callfire.api.client.api.callstexts.model.Media;

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
public class TextBroadcast extends Broadcast {
    private String message;
    private BigMessageStrategy bigMessageStrategy;
    @Builder.Default private List<TextRecipient> recipients = new ArrayList<>();
    @Builder.Default private List<Media> media = new ArrayList<>();

    public enum BigMessageStrategy {
        SEND_MULTIPLE,
        DO_NOT_SEND,
        TRIM,
        MMS
    }
}
