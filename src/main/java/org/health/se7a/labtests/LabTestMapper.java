package org.health.se7a.labtests;

import org.health.se7a.nurse.NurseMapper;
import org.health.se7a.patients.PatientMapper;
import org.health.se7a.patients.Patients;

import java.util.Optional;

public class LabTestMapper {

    public static LabTestResponseDTO toResponse(LabTest labTest) {
        return Optional.ofNullable(labTest)
                .map(l -> LabTestResponseDTO.builder()
                        .id(l.getId())
                        .testName(l.getTestName())
                        .result(l.getResult())
                        .testDate(l.getTestDate())
                        .nurse(NurseMapper.toDto(l.getNurse()))
                        .patient(PatientMapper.toDto(l.getPatient()))
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
