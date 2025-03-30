package org.health.se7a.patients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patients,Long> {
    Optional<Patients> getPatientsByNationalityID(String id);
}
