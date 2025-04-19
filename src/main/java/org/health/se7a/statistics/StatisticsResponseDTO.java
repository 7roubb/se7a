package org.health.se7a.statistics;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class StatisticsResponseDTO {
    private long totalLabTests;
    private long totalMedications;
    private long totalVitalSigns;
    private String range;
}
