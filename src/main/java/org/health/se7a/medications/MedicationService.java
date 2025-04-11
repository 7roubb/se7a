package org.health.se7a.medications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedicationService {
    Boolean addMedication(MedicationDTO medication);
    Boolean updateMedication(Long id,MedicationDTO medication);
    Boolean deleteMedication(Long id);
    Medication findMedicationById(Long id);
    Page<MedicationDTO> getMedicationByPatientNatId(String natId,Pageable pageable);
    Page<MedicationDTO> getMedicationByNurse(Pageable pageable);


}
