package org.health.se7a.medications;

import lombok.RequiredArgsConstructor;
import org.health.se7a.exception.XppException;
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
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;

    @Override
    public Boolean addMedication(Medication medication) {
        medication.setAdministeredAt(LocalDateTime.now());
        medicationRepository.save(medication);
        return true;
    }

    @Override
    @Transactional
    public Boolean updateMedication(Long id, MedicationDTO medication) {
        Medication existingMedication = medicationRepository.findById(id)
                .orElseThrow(() -> notFoundException(id, "medication.not.found"));
        updateMedicationDetails(existingMedication, medication);
        return true;
    }

    @Override
    public Boolean deleteMedication(Long id) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> notFoundException(id, "medication.not.found"));
        medicationRepository.delete(medication);
        return true;
    }

    @Override
    public Medication findMedicationById(Long id) {
        return medicationRepository.findById(id)
                .orElseThrow(() -> notFoundException(id, "medication.not.found"));
    }

    @Override
    public Page<Medication> findAllMedications(Pageable pageable) {
        return medicationRepository.findAll(pageable);
    }

    private XppException notFoundException(Object identifier, String messageKey) {
        return new XppException(
                List.of(identifier),
                HttpStatus.NOT_FOUND,
                messageKey
        );
    }

    private void updateMedicationDetails(Medication existingMedication, MedicationDTO newMedication) {
        Optional.ofNullable(newMedication.getDrugName()).ifPresent(existingMedication::setDrugName);
        Optional.ofNullable(newMedication.getDosage()).ifPresent(existingMedication::setDosage);
        Optional.ofNullable(newMedication.getAdministrationMethod()).ifPresent(existingMedication::setAdministrationMethod);
        existingMedication.setAdministeredAt(LocalDateTime.now());
        medicationRepository.save(existingMedication);
    }
}