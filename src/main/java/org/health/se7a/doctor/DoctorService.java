package org.health.se7a.doctor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DoctorService {
    Boolean createDoctor(DoctorDTO doctorDTO);
    Boolean  updateDoctor(Long id, DoctorDTO doctorDTO);
    DoctorDTO getDoctorById(Long id);
    Page<DoctorDTO> getAllDoctors(Pageable page);
    List<DoctorLookupDTO> getAllDoctorsForLookup();

}
