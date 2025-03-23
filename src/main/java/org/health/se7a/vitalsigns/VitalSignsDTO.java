package org.health.se7a.vitalsigns;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.health.se7a.common.OnCreate;
import org.health.se7a.common.OnUpdate;


import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VitalSignsDTO {
    @NotNull(message = "{vitals.patient.notnull}", groups = OnCreate.class)
    private Long patientId;

    @NotNull(message = "{vitals.nurse.notnull}", groups = OnCreate.class)
    private Long nurseId;

    @Positive(message = "{vitals.bloodPressure.notnull}", groups = {OnCreate.class, OnUpdate.class})
    private Double bloodPressure;

    @Positive(message = "{vitals.heartRate.positive}", groups = {OnCreate.class, OnUpdate.class})
    private Integer heartRate;

    @Positive(message = "{vitals.temperature.positive}", groups = {OnCreate.class, OnUpdate.class})
    private Double temperature;
    @Positive(message = "{vitals.respiratoryRate.positive}", groups = {OnCreate.class, OnUpdate.class})
    private Integer respiratoryRate;

    @NotNull(message = "{vitals.recordedAt.notnull}", groups = OnCreate.class)
    private LocalDateTime recordedAt;
}