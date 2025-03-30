package org.health.se7a.labtests;

import org.health.se7a.patients.Patients;

import java.util.Optional;

public class LabTestMapper {

    public static LabTestDTO toDto(LabTest labTest) {
        return Optional.ofNullable(labTest)
                .map(l -> LabTestDTO.builder()
                        .id(l.getId())
                        .patientNatId(l.getPatient().getNationalityID())
                        .testName(l.getTestName())
                        .result(l.getResult())
                        .testDate(l.getTestDate())
                        .build())
                .orElse(null);
    }

    public static LabTest toEntity(LabTestDTO labTestDTO, Patients patient) {
        return Optional.ofNullable(labTestDTO)
                .map(dto -> {
                    LabTest labTest = new LabTest();
                    labTest.setPatient(patient);
                    labTest.setTestName(dto.getTestName());
                    labTest.setResult(dto.getResult());
                    labTest.setTestDate(dto.getTestDate());
                    return labTest;
                })
                .orElse(null);
    }
}
