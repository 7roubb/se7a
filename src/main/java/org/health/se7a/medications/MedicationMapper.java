package org.health.se7a.medications;

import org.health.se7a.patients.Patients;
import org.health.se7a.nurse.Nurse;

import java.util.Optional;

public class MedicationMapper {

    public static MedicationDTO toDto(Medication medication) {
        return Optional.ofNullable(medication)
                .map(m -> MedicationDTO.builder()
                        .patientNatId(m.getPatient().getNationalityID())
                        .drugName(m.getDrugName())
                        .dosage(m.getDosage())
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