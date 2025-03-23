package org.health.se7a.vitalsigns;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface VitalService {
    Boolean updateVitalSigns(VitalSigns vitalSigns);
    Boolean deleteVitalSigns(Long id);
    Boolean createVitalSigns(VitalSigns vitalSigns);
    Page<VitalSignsDTO> getVitalSignsByPatient(Long patientId, Pageable pageable);
    Page<VitalSignsDTO> getVitalSignsDTOByNurse(Long nurseId, Pageable pageable);
    VitalSignsDTO getVitalSignsDTOById(Long id);
}
