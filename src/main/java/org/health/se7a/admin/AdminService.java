package org.health.se7a.admin;


import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.health.se7a.diagnosis.DiagnosisRepository;
import org.health.se7a.doctor.Doctor;
import org.health.se7a.doctor.DoctorRepository;
import org.health.se7a.entity.EntityService;
import org.health.se7a.exception.XppException;
import org.health.se7a.labtests.LabTestRepository;
import org.health.se7a.nurse.Nurse;
import org.health.se7a.nurse.NurseRepository;
import org.health.se7a.patients.PatientRepository;
import org.health.se7a.secretary.SecretaryRepository;
import org.health.se7a.security.UserRepository;
import org.health.se7a.security.model.AccountStatus;
import org.health.se7a.security.model.LoginType;
import org.health.se7a.security.model.LoginUser;
import org.health.se7a.security.service.LoginDetailsServiceImpl;
import org.health.se7a.visits.MedicalVisitRepository;
import org.health.se7a.vitalsigns.VitalSignsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;
    private final LoginDetailsServiceImpl loginDetailsService;
    private final EntityService entityService;
    private final NurseRepository nurseRepository;
    private final DoctorRepository doctorRepository;
    private final SecretaryRepository secretaryRepository;
    private final VitalSignsRepository vitalSignsRepository;
    private final PatientRepository patientRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final LabTestRepository labTestRepository;
    private final MedicalVisitRepository medicalVisitRepository;


    @PostConstruct
    @Transactional
    public void initAdmin() {
        if (adminRepository.count() == 0) {
            Admin defaultAdmin = Admin.builder()
                    .name("Admin")
                    .telNumber("0599078888")
                    .accountStatus(AccountStatus.ACTIVE)
                    .build();

            adminRepository.save(defaultAdmin);
            entityService.addUserLoginInfo(defaultAdmin.getTelNumber(), LoginType.ADMIN);

            log.info("Default Admin Created Successfully!");
        } else {
            log.info("Admin already exists. Skipping creation.");
        }
    }


    public void setAccountStatus(String phoneNumber,LoginType loginType,AccountStatus accountStatus) {
        System.out.println(loginType);
        UserRepository repository = loginDetailsService.getRepositoryByLoginType(loginType);
        LoginUser user = repository.findByTelNumber(phoneNumber)
                .orElseThrow(() -> new XppException(List.of(phoneNumber),
                        HttpStatus.NOT_FOUND,
                        "user.not.found"
                ));
        user.setAccountStatus(accountStatus);
        System.out.println(user.getType());
        if (user.getType() == LoginType.DOCTOR) {
            ((DoctorRepository) repository).save((Doctor) user);
        } else if (user.getType() == LoginType.NURSE) {
            ((NurseRepository) repository).save((Nurse) user);
        }
    }
    public SystemStatsDTO getSystemStatistics() {
        SystemStatsDTO stats = new SystemStatsDTO();

        long activeAdmins = adminRepository.countByAccountStatus(AccountStatus.ACTIVE);
        long activeDoctors = doctorRepository.countByAccountStatus(AccountStatus.ACTIVE);
        long activeNurses = nurseRepository.countByAccountStatus(AccountStatus.ACTIVE);
        long activeSecretaries = secretaryRepository.countByAccountStatus(AccountStatus.ACTIVE);
        stats.setTotalActiveUsers(activeAdmins + activeDoctors + activeNurses + activeSecretaries);

        long disabledAdmins = adminRepository.countByAccountStatus(AccountStatus.DISABLED);
        long disabledDoctors = doctorRepository.countByAccountStatus(AccountStatus.DISABLED);
        long disabledNurses = nurseRepository.countByAccountStatus(AccountStatus.DISABLED);
        long disabledSecretaries = secretaryRepository.countByAccountStatus(AccountStatus.DISABLED);
        stats.setTotalDisabledUsers(disabledAdmins + disabledDoctors + disabledNurses + disabledSecretaries);

        stats.setTotalUsers(adminRepository.count() + doctorRepository.count()
                + nurseRepository.count() + secretaryRepository.count());

        stats.setTotalPatients(patientRepository.count());
        stats.setTotalVitalSignsRecords(vitalSignsRepository.count());
        stats.setTotalDiagnoses(diagnosisRepository.count());
        stats.setTotalLabTests(labTestRepository.count());
        stats.setTotalMedicalVisits(medicalVisitRepository.count());

        return stats;
    }
}
