package org.health.se7a.vitalsigns;

import org.health.se7a.nurse.NurseDTO;
import org.health.se7a.nurse.NurseMapper;
import org.health.se7a.patients.PatientMapper;
import org.health.se7a.patients.Patients;
import org.health.se7a.nurse.Nurse;

import java.util.Optional;

public class VitalSignsMapper {

    public static VitalSignsResponseDTO toDto(VitalSigns vitalSigns) {
        return Optional.ofNullable(vitalSigns)
                .map(v -> VitalSignsResponseDTO.builder()
                        .bloodPressure(v.getBloodPressure())
                        .heartRate(v.getHeartRate())
                        .patient(PatientMapper.toDto(v.getPatient()))
                        .nurse(NurseMapper.toDto(v.getNurse()))
                        .temperature(v.getTemperature())
                        .respiratoryRate(v.getRespiratoryRate())
                        .recordedAt(v.getRecordedAt())
                        .build())
                .orElse(null);
    }

    public static VitalSigns toEntity(VitalSignsDTO vitalSignsDTO, Patients patient, Nurse nurse) {
        return Optional.ofNullable(vitalSignsDTO)
                .map(dto -> {
                    VitalSigns vitalSigns = new VitalSigns();
                    vitalSigns.setPatient(patient);
                    vitalSigns.setNurse(nurse);
                    vitalSigns.setBloodPressure(dto.getBloodPressure());
                    vitalSigns.setHeartRate(dto.getHeartRate());
                    vitalSigns.setTemperature(dto.getTemperature());
                    vitalSigns.setRespiratoryRate(dto.getRespiratoryRate());
                    vitalSigns.setRecordedAt(dto.getRecordedAt());
                    return vitalSigns;
                })
                .orElse(null);
    }
}