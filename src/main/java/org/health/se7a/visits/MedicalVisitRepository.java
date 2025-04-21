package org.health.se7a.visits;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicalVisitRepository extends JpaRepository<MedicalVisit, Long> {
    Page<MedicalVisit> getMedicalVisitByPatients_NationalityID(String natId, Pageable page);
}
