package org.health.se7a.medications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedicationService {
    Boolean addMedication(Medication medication);
    Boolean updateMedication(Long id,MedicationDTO medication);
    Boolean deleteMedication(Long id);
    Medication findMedicationById(Long id);
    Page<Medication> findAllMedications(Pageable pageable);
}
