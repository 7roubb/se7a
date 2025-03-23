package org.health.se7a.vitalsigns;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VitalServiceImpl implements VitalService {

    @Override
    public Boolean updateVitalSigns(VitalSigns vitalSigns) {
        return null;
    }

    @Override
    public Boolean deleteVitalSigns(Long id) {
        return null;
    }

    @Override
    public Boolean createVitalSigns(VitalSigns vitalSigns) {
        return null;
    }

    @Override
    public Page<VitalSignsDTO> getVitalSignsByPatient(Long patientId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<VitalSignsDTO> getVitalSignsDTOByNurse(Long nurseId, Pageable pageable) {
        return null;
    }

    @Override
    public VitalSignsDTO getVitalSignsDTOById(Long id) {
        return null;
    }
}
