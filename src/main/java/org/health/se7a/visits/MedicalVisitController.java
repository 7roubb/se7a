package org.health.se7a.visits;

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
@RequestMapping("/medical-visits")
@RequiredArgsConstructor
public class MedicalVisitController {

    private final MedicalVisitService medicalVisitService;

    @GetMapping("/patient/{patientNatId}")
    public XppResponseEntity<Page<MedicalVisitResponseDTO>> getMedicalVisitsByPatient(
            @PathVariable String patientNatId,
            Pageable pageable) {
        return XppResponseEntity.map(
                medicalVisitService.getMedicalVisits(patientNatId, pageable)
        );
    }

    @GetMapping("/{id}")
    public XppResponseEntity<MedicalVisitResponseDTO> getMedicalVisitById(
            @PathVariable Long id) {
        return XppResponseEntity.map(
                medicalVisitService.getMedicalVisit(id)
        );
    }

    @PostMapping
    @PreAuthorize("@authorizationService.loggedInUserIsSecretary()")
    public XppResponseEntity<Long> createMedicalVisit(
            @RequestBody @Validated(OnCreate.class) MedicalVisitRequestDTO dto) {
        return XppResponseEntity.map(
                medicalVisitService.createMedicalVisit(dto)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("@authorizationService.loggedInUserIsDoctor()")
    public XppResponseEntity<Boolean> updateMedicalVisit(
            @PathVariable Long id,
            @RequestBody @Validated(OnUpdate.class) MedicalVisitRequestDTO dto) {
        return XppResponseEntity.map(
                medicalVisitService.updateMedicalVisit(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@authorizationService.loggedInUserIsDoctor()")
    public XppResponseEntity<Boolean> deleteMedicalVisit(
            @PathVariable Long id) {
        return XppResponseEntity.map(
                medicalVisitService.deleteMedicalVisit(id)
        );
    }
}
