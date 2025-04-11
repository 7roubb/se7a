package org.health.se7a.labtests;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LabTestService {
    Boolean createLabTest(LabTestDTO labTestDTO);
    Boolean updateLabTest(Long id,LabTestDTO labTestDTO);
    Boolean deleteLabTest(Long id);
    Page<LabTestDTO> getLabTestsByPatientNatId(String natId, Pageable pageable);
    LabTestDTO getLabTestById(Long id);
    Page<LabTestDTO> getByNurse(Pageable pageable);
}
