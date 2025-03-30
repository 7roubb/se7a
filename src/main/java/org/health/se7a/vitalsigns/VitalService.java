package org.health.se7a.vitalsigns;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface VitalService {
    Boolean updateVitalSigns(Long id,VitalSignsDTO vitalSigns);
    Boolean deleteVitalSigns(Long id);
    Boolean createVitalSigns(VitalSignsDTO vitalSigns);
    Page<VitalSignsResponseDTO> getVitalSignsByPatient(Long patientId, Pageable pageable);
    Page<VitalSignsResponseDTO> getVitalSignsDTOByNurse(Long nurseId, Pageable pageable);
    VitalSignsResponseDTO getVitalSignsDTOById(Long id);
}
