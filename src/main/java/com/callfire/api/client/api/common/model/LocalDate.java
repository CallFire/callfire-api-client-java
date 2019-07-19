package com.callfire.api.client.api.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto to represent date (year, month, day)
 * 1 based date, example (1964, 12, 25)
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalDate extends CallfireModel {
    protected Integer year;
    protected Integer month;
    protected Integer day;
}
