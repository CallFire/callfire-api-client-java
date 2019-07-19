package com.callfire.api.client.api.account.model;

import java.util.ArrayList;
import java.util.List;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NumberOrderItem extends CallfireModel {
    private Integer ordered = 0;
    private Double unitCost;
    private List<String> fulfilled = new ArrayList<>();
}
