package org.health.se7a.patients;

import org.health.se7a.common.OnCreate;
import org.health.se7a.common.OnUpdate;
import org.health.se7a.common.XppResponseEntity;
import lombok.RequiredArgsConstructor;
import org.health.se7a.medications.MedicationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    @PreAuthorize("@authorizationService.loggedInUserIsAdmin()")
    public XppResponseEntity<Page<PatientResponseDTO>> getAllPatients(Pageable pageable) {
        return XppResponseEntity.map(patientService.getAllPatients(pageable));
    }

    @GetMapping("/{id}")
    public XppResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id) {
        return XppResponseEntity.map(patientService.getPatient(id));
    }

    @GetMapping("/public")
    public XppResponseEntity<PatientResponseDTO> getPatientById(@RequestParam String natId) {
        return XppResponseEntity.map(patientService.getPatient(natId));
    }


    @PostMapping
    @PreAuthorize("@authorizationService.loggedInUserIsSecretary()")
    public XppResponseEntity<Boolean> createPatient(@RequestBody @Validated(OnCreate.class) PatientRequestDTO patientRequestDTO) {
        Boolean createdPatient = patientService.createPatient(patientRequestDTO);
        return XppResponseEntity.map(createdPatient);
    }

    @PostMapping("/addMedication")
    @PreAuthorize("@authorizationService.loggedInUserIsNurse()")
    public XppResponseEntity<Boolean> addMedication(@RequestBody @Validated(OnCreate.class) MedicationDTO medicationDTO) {
        Boolean addedMedication = patientService.addMedication(medicationDTO);
        return XppResponseEntity.map(addedMedication);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@authorizationService.loggedInUserIsSecretary()")
    public XppResponseEntity<Boolean> updatePatient(
            @PathVariable Long id,
            @RequestBody @Validated(OnUpdate.class) PatientRequestDTO patientRequestDTO) {
        Boolean updatedPatient = patientService.updatePatient(id, patientRequestDTO);
        return XppResponseEntity.map(updatedPatient);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@authorizationService.userCanViewVitalSigns(#id)")
    public XppResponseEntity<Boolean> deletePatient(@PathVariable Long id) {
        Boolean deletedPatient = patientService.deletePatient(id);
        return XppResponseEntity.map(deletedPatient);
    }
}
