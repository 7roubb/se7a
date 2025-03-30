package org.health.se7a.vitalsigns;

import lombok.RequiredArgsConstructor;
import org.health.se7a.exception.XppException;
import org.health.se7a.patients.PatientRepository;
import org.health.se7a.patients.Patients;
import org.health.se7a.nurse.Nurse;
import org.health.se7a.nurse.NurseRepository;
import org.health.se7a.security.util.SecurityContextUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VitalServiceImpl implements VitalService {

    private final VitalSignsRepository vitalSignsRepository;
    private final PatientRepository patientsRepository;
    private final NurseRepository nurseRepository;

    @Override
    @Transactional
    public Boolean updateVitalSigns(Long id ,VitalSignsDTO vitalSignsDTO) {
        VitalSigns existingVitalSigns = vitalSignsRepository.findById(id)
                .orElseThrow(() -> notFoundException(id, "vitalSigns.not.found"));
        updateVitalDetails(existingVitalSigns, vitalSignsDTO);
        vitalSignsRepository.save(existingVitalSigns);
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteVitalSigns(Long id) {
        VitalSigns existingVitalSigns = vitalSignsRepository.findById(id)
                .orElseThrow(() -> notFoundException(id, "vitalSigns.not.found"));

        vitalSignsRepository.delete(existingVitalSigns);
        return true;
    }

    @Override
    @Transactional
    public Boolean createVitalSigns(VitalSignsDTO vitalSignsDTO) {
        Patients patient = patientsRepository.getPatientsByNationalityID(vitalSignsDTO.getPatientNatId())
                .orElseThrow(() -> notFoundException(vitalSignsDTO.getPatientNatId(), "patient.not.found"));

        Nurse nurse = nurseRepository.findById(loggedInUserId())
                .orElseThrow(() -> notFoundException(vitalSignsDTO.getNurseId(), "nurse.not.found"));

        if (!patient.getNurses().contains(nurse)) {
            patient.getNurses().add(nurse);
        }
        VitalSigns vitalSigns = VitalSignsMapper.toEntity(vitalSignsDTO, patient, nurse);
        vitalSignsRepository.save(vitalSigns);
        return true;
    }

    @Override
    public Page<VitalSignsResponseDTO> getVitalSignsByPatient(Long patientId, Pageable pageable) {
        return vitalSignsRepository.findByPatientId(patientId, pageable)
                .map(VitalSignsMapper::toDto);

    }

    @Override
    public Page<VitalSignsResponseDTO> getVitalSignsDTOByNurse(Long nurseId, Pageable pageable) {
        return vitalSignsRepository.findByNurseId(nurseId, pageable)
                .map(VitalSignsMapper::toDto);
    }

    @Override
    public VitalSignsResponseDTO getVitalSignsDTOById(Long id) {
        return vitalSignsRepository.findById(id)
                .map(VitalSignsMapper::toDto)
                .orElseThrow(() -> notFoundException(id, "vitalSigns.not.found"));

    }

    private void updateVitalDetails(VitalSigns vitalSigns, VitalSignsDTO vitalSignsDTO) {
        vitalSigns.setBloodPressure(vitalSignsDTO.getBloodPressure());
        vitalSigns.setHeartRate(vitalSignsDTO.getHeartRate());
        vitalSigns.setTemperature(vitalSignsDTO.getTemperature());
        vitalSigns.setRespiratoryRate(vitalSignsDTO.getRespiratoryRate());
        vitalSigns.setRecordedAt(vitalSignsDTO.getRecordedAt());
    }

    private XppException notFoundException(Object id, String messageKey) {
        return new XppException(List.of(id), HttpStatus.NOT_FOUND, messageKey);
    }

    private Long loggedInUserId() {
        return SecurityContextUtil.loggedUser().getId();
    }
}
