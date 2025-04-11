package org.health.se7a.medications;

import org.health.se7a.nurse.NurseMapper;
import org.health.se7a.patients.PatientMapper;
import org.health.se7a.patients.Patients;
import org.health.se7a.nurse.Nurse;

import java.util.Optional;

public class MedicationMapper {

    public static MedicationResponseDTO toResponse(Medication medication) {
        return Optional.ofNullable(medication)
                .map(m -> MedicationResponseDTO.builder()
                        .id(m.getId())
                        .drugName(m.getDrugName())
                        .dosage(m.getDosage())
                        .patient(PatientMapper.toDto(m.getPatient()))
                        .nurse(NurseMapper.toDto(m.getNurse()))
                        .administrationMethod(m.getAdministrationMethod())
                        .administeredAt(m.getAdministeredAt())
                        .build())
                .orElse(null);
    }

    public static Medication toEntity(MedicationDTO medicationDTO, Patients patient, Nurse nurse) {
        return Optional.ofNullable(medicationDTO)
                .map(dto -> {
                    Medication medication = new Medication();
                    medication.setPatient(patient);
                    medication.setNurse(nurse);
                    medication.setDrugName(dto.getDrugName());
                    medication.setDosage(dto.getDosage());
                    medication.setAdministrationMethod(dto.getAdministrationMethod());
                    medication.setAdministeredAt(dto.getAdministeredAt());
                    return medication;
                })
                .orElse(null);
    }
}