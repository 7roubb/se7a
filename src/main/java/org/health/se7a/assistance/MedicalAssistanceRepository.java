package org.health.se7a.assistance;

import org.health.se7a.labtests.LabTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalAssistanceRepository extends JpaRepository<MedicalAssistance, Integer> {

    Page<MedicalAssistance> getAllByDoctor_Id(Long doctorId,Pageable pageable);
    Page<MedicalAssistance> getAllByNurse_Id(Long nurseId,Pageable pageable);
    Page<MedicalAssistance> findMedicalAssistanceByPatient_NationalityID(String natId, Pageable pageable);

}
