package org.health.se7a.patients;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.health.se7a.common.OnCreate;
import org.health.se7a.common.OnUpdate;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientResponseDTO {

    @NotNull(message = "{patient.id.notnull}", groups = {OnCreate.class, OnUpdate.class})
    private Long id;

    @NotBlank(message = "{patient.medicalHistory.notblank}", groups = OnCreate.class)
    @Size(max = 255, message = "{patient.medicalHistory.size}", groups = {OnCreate.class, OnUpdate.class})
    private String medicalHistory;

    @NotBlank(message = "{patient.nationalityID.notblank}", groups = OnCreate.class)
    @Size(min = 9, max = 9, message = "{patient.nationalityID.size}", groups = {OnCreate.class, OnUpdate.class})
    private String nationalityID;

    @NotNull(message = "{patient.age.notnull}", groups = {OnCreate.class, OnUpdate.class})
    private Long age;

    @NotNull(message = "{patient.gender.notnull}", groups = {OnCreate.class, OnUpdate.class})
    private Gender gender;

    @NotNull(message = "{patient.doctorPatients.notnull}", groups = {OnCreate.class, OnUpdate.class})
    private List<Long> doctorPatientIds;

    @NotNull(message = "{patient.nursePatients.notnull}", groups = {OnCreate.class, OnUpdate.class})
    private List<Long> nursePatientIds;

    @NotNull(message = "{patient.medicalHistories.notnull}", groups = {OnCreate.class, OnUpdate.class})
    private List<MedicalHistory> medicalHistoryIds;
}
