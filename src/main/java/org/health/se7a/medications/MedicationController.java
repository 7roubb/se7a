package org.health.se7a.medications;

import lombok.RequiredArgsConstructor;
import org.health.se7a.common.OnCreate;
import org.health.se7a.common.OnUpdate;
import org.health.se7a.common.XppResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medications")
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

    @GetMapping("/{id}")
    public XppResponseEntity<MedicationDTO> getMedicationById(@PathVariable Long id) {
        Medication medication = medicationService.findMedicationById(id);
        return XppResponseEntity.map(MedicationMapper.toDto(medication));
    }

    @PostMapping
    @PreAuthorize("@authorizationService.loggedInUserIsNurse()")
    public XppResponseEntity<Boolean> addMedication(@RequestBody @Validated(OnCreate.class) MedicationDTO medicationDTO) {
        return XppResponseEntity.map(medicationService.addMedication(medicationDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("@authorizationService.loggedInUserIsNurse()")
    public XppResponseEntity<Boolean> updateMedication(
            @PathVariable Long id,
            @RequestBody @Validated(OnUpdate.class) MedicationDTO medicationDTO) {
        return XppResponseEntity.map(medicationService.updateMedication(id, medicationDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@authorizationService.loggedInUserIsNurse()")
    public XppResponseEntity<Boolean> deleteMedication(@PathVariable Long id) {
        return XppResponseEntity.map(medicationService.deleteMedication(id));
    }

    @GetMapping("/patient/{patientNatId}")
    public XppResponseEntity<Page<MedicationDTO>> getMedicationsByPatient(@PathVariable String patientNatId, Pageable pageable) {
        return XppResponseEntity.map(medicationService.getMedicationByPatientNatId(patientNatId, pageable));
    }

    @GetMapping("/nurse")
    @PreAuthorize("@authorizationService.loggedInUserIsNurse()")
    public XppResponseEntity<Page<MedicationDTO>> getMedicationsByLoggedInNurse(Pageable pageable) {
        return XppResponseEntity.map(medicationService.getMedicationByNurse(pageable));
    }
}
