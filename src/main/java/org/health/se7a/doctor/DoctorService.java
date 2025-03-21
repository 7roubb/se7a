package org.health.se7a.doctor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DoctorService {
    Boolean createDoctor(DoctorDTO doctorDTO);
    Boolean  updateDoctor(Long id, DoctorDTO doctorDTO);
    Boolean deleteDoctor(Long id);
    DoctorDTO getDoctorById(Long id);
    Page<DoctorDTO> getAllDoctors(Pageable page);
}
