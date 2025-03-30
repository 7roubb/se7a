package org.health.se7a.medications;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class MedicationDTO {

    private Long id;

    @NotNull
    private String patientNatId;

    @NotNull
    private String drugName;

    @NotNull
    private Double dosage;

    @NotNull
    private String administrationMethod;

    private LocalDateTime administeredAt;
}
