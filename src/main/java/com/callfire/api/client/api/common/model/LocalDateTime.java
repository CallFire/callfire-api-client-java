package com.callfire.api.client.api.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto to represent date and time (year, month, day, hour, minute, second).
 * 1 based date, 0 based time
 * example (1964, 12, 25, 23, 0, 59)
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalDateTime extends LocalDate {
    private Integer hour;
    private Integer minute;
    private Integer second;
}
