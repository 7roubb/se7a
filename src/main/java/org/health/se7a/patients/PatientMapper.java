package org.health.se7a.patients;

import org.health.se7a.doctor.DoctorMapper;
import org.health.se7a.nurse.NurseMapper;

import java.util.Optional;
import java.util.stream.Collectors;

public class PatientMapper {

    public static PatientResponseDTO toDto(Patients patient) {
        return Optional.ofNullable(patient)
                .map(p -> PatientResponseDTO.builder()
                        .id(p.getId())
                        .nationalityID(p.getNationalityID())
                        .age(p.getAge())
                        .name(p.getName())
                        .telNumber(p.getTelNumber())
                        .createdAt(p.getCreatedAt())
                        .gender(p.getGender())
                        .doctors(p.getDoctors() != null ? p.getDoctors().stream()
                                .map(DoctorMapper::toDto)
                                .collect(Collectors.toList()) : null)
                        .nurses(p.getNurses() != null ? p.getNurses().stream()
                                .map(NurseMapper::toDto)
                                .collect(Collectors.toList()) : null)
                        .build())
                .orElse(null);
    }

    public static Patients toEntity(PatientRequestDTO dto) {
        return Optional.ofNullable(dto)
                .map(d -> Patients.builder()
                        .nationalityID(d.getNationalityID())
                        .age(d.getAge())
                        .name(d.getName())
                        .telNumber(d.getTelNumber())
                        .gender(d.getGender())
                        .build())
                .orElse(null);
    }
}
