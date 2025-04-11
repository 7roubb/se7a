package org.health.se7a.labtests;

import org.health.se7a.medications.Medication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabTestRepository extends JpaRepository<LabTest, Long> {
    Page<LabTest> findLabTestByPatient_NationalityID(String natId, Pageable pageable);
    Page<LabTest> findLabTestByNurse_Id(Long nurseId, Pageable pageable);

}
