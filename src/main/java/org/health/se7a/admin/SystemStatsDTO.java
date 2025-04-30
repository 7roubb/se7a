package org.health.se7a.admin;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SystemStatsDTO {
    private long totalActiveUsers;
    private long totalDisabledUsers;
    private long totalUsers;
    private long totalPatients;
    private long totalVitalSignsRecords;
    private long totalDiagnoses;
    private long totalLabTests;
    private long totalMedicalVisits;
}
