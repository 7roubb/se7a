package org.health.se7a.doctor;

import lombok.RequiredArgsConstructor;
import org.health.se7a.entity.EntityService;
import org.health.se7a.exception.XppException;
import org.health.se7a.security.model.AccountStatus;
import org.health.se7a.security.model.LoginType;
import org.health.se7a.users.UserRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final EntityService entityService;
    private final UserRepo userRepo;

    @Override
    public Boolean createDoctor(DoctorDTO doctorDTO) {
        validateDoctorPhoneNumberUniqueness(doctorDTO.getTelNumber());
        Doctor doctor = DoctorMapper.toEntity(doctorDTO);
        doctor.setCreatedAt(LocalDateTime.now());
        doctor.setAccountStatus(AccountStatus.ACTIVE);
        doctorRepository.save(doctor);
        entityService.addUserLoginInfo(doctor.getTelNumber(), LoginType.DOCTOR);
        return true;
    }

    @Override
    @Transactional
    public Boolean updateDoctor(Long id, DoctorDTO doctorDTO) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> notFoundException(id, "doctor.not.found"));

        validatePhoneNumberUpdate(doctor, doctorDTO.getTelNumber());
        updateDoctorDetails(doctor, doctorDTO);

        return true;
    }

    @Override
    public Boolean deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> notFoundException(id, "doctor.not.found"));
        doctorRepository.delete(doctor);
        return true;
    }

    @Override
    public DoctorDTO getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .map(DoctorMapper::toDto)
                .orElseThrow(() -> notFoundException(id, "doctor.not.found"));
    }

    @Override
    public Page<DoctorDTO> getAllDoctors(Pageable pageable) {
        return doctorRepository.findAll(pageable)
                .map(DoctorMapper::toDto);
    }

    private XppException notFoundException(Object identifier, String messageKey) {
        return new XppException(
                List.of(identifier),
                HttpStatus.NOT_FOUND,
                messageKey
        );
    }

    private void validateDoctorPhoneNumberUniqueness(String phoneNumber) {
        Optional.of(phoneNumber)
                .filter(number -> userRepo.existsByTelNumber(phoneNumber))
                .ifPresent(number -> {
                    throw new XppException(
                            List.of(number),
                            HttpStatus.BAD_REQUEST,
                            "doctor.telNumber.exists"
                    );
                });
    }

    private void validatePhoneNumberUpdate(Doctor existingDoctor, String newNumber) {
        Optional.ofNullable(newNumber)
                .filter(number -> !number.equals(existingDoctor.getTelNumber()))
                .ifPresent(this::validateDoctorPhoneNumberUniqueness);
    }

    private void updateDoctorDetails(Doctor doctor, DoctorDTO doctorDTO) {
        Optional.ofNullable(doctorDTO.getName()).ifPresent(doctor::setName);
        Optional.ofNullable(doctorDTO.getTelNumber()).ifPresent(doctor::setTelNumber);
        Optional.ofNullable(doctorDTO.getSpecialty()).ifPresent(doctor::setSpecialty);
        doctor.setUpdatedAt(LocalDateTime.now());
        doctorRepository.save(doctor);
    }
}
