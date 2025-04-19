package org.health.se7a.document;

import lombok.RequiredArgsConstructor;
import org.health.se7a.common.OnCreate;
import org.health.se7a.common.XppResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class MedicalDocumentController {

    private final DocumentService documentService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("@authorizationService.loggedInUserIsNurse()")
    public XppResponseEntity<Boolean> uploadDocument(
            @Validated(OnCreate.class) MedicalDocumentRequestDTO requestDTO
    ) {
        return XppResponseEntity.map(documentService.addDocument(requestDTO));
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("@authorizationService.loggedInUserIsNurse()")
    public XppResponseEntity<Boolean> updateDocument(
            @Validated(OnCreate.class) MedicalDocumentRequestDTO requestDTO
    ) {
        return XppResponseEntity.map(documentService.updateDocument(requestDTO));
    }

    @DeleteMapping
    @PreAuthorize("@authorizationService.loggedInUserIsNurse()")
    public XppResponseEntity<Boolean> deleteDocument(
            @Validated(OnCreate.class) MedicalDocumentRequestDTO requestDTO
    ) {
        return XppResponseEntity.map(documentService.deleteDocument(requestDTO));
    }

    @GetMapping("/{id}")
    @PreAuthorize("@authorizationService.loggedInUserIsAdminOrNurse()")
    public XppResponseEntity<MedicalDocumentResponseDTO> getDocumentById(@PathVariable Long id) {
        return XppResponseEntity.map(documentService.getDocumentById(id));
    }

    @GetMapping("/patient/{natId}")
    @PreAuthorize("@authorizationService.loggedInUserIsAdminOrNurse()")
    public XppResponseEntity<Page<MedicalDocumentResponseDTO>> getDocumentsByPatientNatId(
            Pageable pageable,
            @PathVariable String natId
    ) {
        return XppResponseEntity.map(documentService.getDocumentByPatientNatId(natId, pageable));
    }
}
