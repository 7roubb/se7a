package org.health.se7a.medications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationDTO {
    private Long id;
    private Long patientId;
    private String drugName;
    private Double dosage;
    private String administrationMethod;
    private LocalDateTime administeredAt;
    private Long nurseId;
}
