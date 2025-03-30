package org.health.se7a.patients;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {

    Boolean createPatient(PatientRequestDTO patientRequestDTO);
    Boolean updatePatient(Long id, PatientRequestDTO patientRequestDTO);
    Boolean deletePatient(Long id);
    PatientResponseDTO getPatient(Long id);
    Page<PatientResponseDTO> getAllPatients(Pageable pageable);
    PatientResponseDTO getPatient(String id);


}
