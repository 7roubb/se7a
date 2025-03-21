package org.health.se7a.nurse;

import org.health.se7a.common.XppResponseEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nurses")
@RequiredArgsConstructor
public class NurseController {

    private final NurseService nurseService;

    @GetMapping
    @PreAuthorize("@authorizationService.loggedInUserIsAdmin()")
    public XppResponseEntity<Page<NurseDTO>> getAllNurses(Pageable pageable) {
        return XppResponseEntity.map(nurseService.getAllNurses(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("@authorizationService.userCanViewNurseDetails(#id)")
    public XppResponseEntity<NurseDTO> getNurseById(@PathVariable Long id) {
        return XppResponseEntity.map(nurseService.getNurseById(id));
    }

    @PostMapping
    @PreAuthorize("@authorizationService.loggedInUserIsAdmin()")
    public XppResponseEntity<Boolean> createNurse(@RequestBody @Valid NurseDTO nurseDTO) {
        Boolean createdNurse = nurseService.createNurse(nurseDTO);
        return XppResponseEntity.map(createdNurse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@authorizationService.userCanViewNurseDetails(#id)")
    public XppResponseEntity<Boolean> updateNurse(
            @PathVariable Long id,
            @RequestBody @Valid NurseDTO nurseDTO) {
        Boolean updatedNurse = nurseService.updateNurse(id, nurseDTO);
        return XppResponseEntity.map(updatedNurse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@authorizationService.userCanViewNurseDetails(#id)")
    public XppResponseEntity<Boolean> deleteNurse(@PathVariable Long id) {
        Boolean deletedNurse = nurseService.deleteNurse(id);
        return XppResponseEntity.map(deletedNurse);
    }
}
