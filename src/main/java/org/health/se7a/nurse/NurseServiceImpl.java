package org.health.se7a.nurse;

import org.health.se7a.entity.EntityService;
import org.health.se7a.exception.XppException;
import org.health.se7a.security.model.LoginType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NurseServiceImpl implements NurseService {

    private final NurseRepository nurseRepository;
    private final EntityService entityService;

    @Override
    public NurseDTO getNurseById(Long id) {
        return nurseRepository.findById(id)
                .map(NurseMapper::toDto)
                .orElseThrow(() -> notFoundException(id, "nurse.not.found"));
    }

    @Override
    public Page<NurseDTO> getAllNurses(Pageable pageable) {
        return nurseRepository.findAll(pageable)
                .map(NurseMapper::toDto);
    }

    @Override
    @Transactional
    public Boolean createNurse(NurseDTO nurseDTO) {
        validatePhoneNumberUniqueness(nurseDTO.getTelNumber());
        Nurse nurse = NurseMapper.toEntity(nurseDTO);
        entityService.addUserLoginInfo(nurse.getTelNumber(), LoginType.NURSE);
        nurseRepository.save(nurse);
        return true;
    }

    @Override
    @Transactional
    public Boolean updateNurse(Long id, NurseDTO nurseDTO) {
        Nurse existingNurse = nurseRepository.findById(id)
                .orElseThrow(() -> notFoundException(id, "nurse.not.found"));
        validatePhoneNumberUpdate(existingNurse, nurseDTO.getTelNumber());
        updateNurseDetails(existingNurse, nurseDTO);

        return true;
    }

    @Override
    @Transactional
    public Boolean deleteNurse(Long id) {
        Nurse existingNurse = nurseRepository.findById(id)
                .orElseThrow(() -> notFoundException(id, "nurse.not.found"));

        nurseRepository.delete(existingNurse);

        return true;
    }

    private void updateNurseDetails(Nurse nurse, NurseDTO nurseDTO) {
        Optional.ofNullable(nurseDTO.getName()).ifPresent(nurse::setName);
        Optional.ofNullable(nurseDTO.getTelNumber()).ifPresent(nurse::setTelNumber);
        nurseRepository.save(nurse);
    }

    private void validatePhoneNumberUniqueness(String phoneNumber) {
        Optional.ofNullable(phoneNumber)
                .filter(telNumber -> !nurseRepository.existsByTelNumber(telNumber))
                .orElseThrow(() -> new XppException("phoneNumber.already.exists"));
    }

    private void validatePhoneNumberUpdate(Nurse existingNurse, String newPhoneNumber) {
        if (newPhoneNumber != null && !newPhoneNumber.equals(existingNurse.getTelNumber())) {
            validatePhoneNumberUniqueness(newPhoneNumber);
        }
    }

    private XppException notFoundException(Long id, String messageKey) {
        return new XppException(List.of(id), HttpStatus.NOT_FOUND, messageKey);
    }
}
