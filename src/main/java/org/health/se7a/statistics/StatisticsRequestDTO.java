package org.health.se7a.statistics;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StatisticsRequestDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private String type; // labtests, medications, vitals
}
