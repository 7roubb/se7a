package org.health.se7a.document;

import java.util.Optional;

public class DocumentMapper {

    public static MedicalDocumentResponseDTO toDto(MedicalDocument document) {
        return Optional.ofNullable(document)
                .map(d -> MedicalDocumentResponseDTO.builder()
                        .id(d.getId())
                        .fileName(d.getFileName())
                        .fileType(d.getFileType())
                        .fileSize(d.getFileSize())
                        .uploadedAt(d.getUploadedAt())
                        .patientId(d.getPatient() != null ? d.getPatient().getId() : null)
                        .build())
                .orElse(null);
    }

    public static MedicalDocument toEntity(MedicalDocumentResponseDTO dto) {
        return Optional.ofNullable(dto)
                .map(d -> MedicalDocument.builder()
                        .id(d.getId())
                        .fileName(d.getFileName())
                        .fileType(d.getFileType())
                        .fileSize(d.getFileSize())
                        .uploadedAt(d.getUploadedAt())
                        .build())
                .orElse(null);
    }
}
