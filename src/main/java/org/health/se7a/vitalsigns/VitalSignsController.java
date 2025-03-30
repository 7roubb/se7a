package org.health.se7a.vitalsigns;


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
@RequestMapping("/vital-signs")
@RequiredArgsConstructor
public class VitalSignsController {

    private final VitalService vitalService;

    @GetMapping("/patient/{patientId}")
    public XppResponseEntity<Page<VitalSignsDTO>> getVitalSignsByPatient(@PathVariable Long patientId, Pageable pageable) {
        return XppResponseEntity.map(vitalService.getVitalSignsByPatient(patientId, pageable));
    }

    @GetMapping("/nurse/{nurseId}")
    @PreAuthorize("@authorizationService.userCanViewNurseDetails(#nurseId)")
    public XppResponseEntity<Page<VitalSignsDTO>> getVitalSignsByNurse(@PathVariable Long nurseId, Pageable pageable) {
        return XppResponseEntity.map(vitalService.getVitalSignsDTOByNurse(nurseId, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("@authorizationService.userCanViewVitalSigns(#id)")
    public XppResponseEntity<VitalSignsDTO> getVitalSignsById(@PathVariable Long id) {
        return XppResponseEntity.map(vitalService.getVitalSignsDTOById(id));
    }

    @PostMapping
    @PreAuthorize("@authorizationService.loggedInUserIsNurse()")
    public XppResponseEntity<Boolean> createVitalSigns(@RequestBody @Validated(OnCreate.class) VitalSignsDTO vitalSignsDTO) {
        return XppResponseEntity.map(vitalService.createVitalSigns(vitalSignsDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("@authorizationService.loggedInUserIsNurse()")
    public XppResponseEntity<Boolean> updateVitalSigns(
            @PathVariable Long id,
            @RequestBody @Validated(OnUpdate.class) VitalSignsDTO vitalSignsDTO) {
        return XppResponseEntity.map(vitalService.updateVitalSigns(id, vitalSignsDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@authorizationService.loggedInUserIsNurse()")
    public XppResponseEntity<Boolean> deleteVitalSigns(@PathVariable Long id) {
        return XppResponseEntity.map(vitalService.deleteVitalSigns(id));
    }
}
