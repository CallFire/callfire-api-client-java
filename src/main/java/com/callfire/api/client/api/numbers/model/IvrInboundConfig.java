package com.callfire.api.client.api.numbers.model;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IvrInboundConfig extends CallfireModel {
    private String dialplanXml;
}
