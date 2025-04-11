package org.health.se7a.medications;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    Page<Medication> findByPatient_NationalityID(String patientNationalityID, Pageable pageable);
    Page<Medication> findMedicationsByNurseId(Long nurseID, Pageable pageable);
}
