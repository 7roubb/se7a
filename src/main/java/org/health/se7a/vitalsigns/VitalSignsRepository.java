package org.health.se7a.vitalsigns;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VitalSignsRepository extends JpaRepository<VitalSigns,Long> {

    Page<VitalSigns> findByPatient_NationalityID(String patientNationalityID, Pageable pageable);
    Page<VitalSigns> findByNurseId(Long nurseId, Pageable pageable);

}
