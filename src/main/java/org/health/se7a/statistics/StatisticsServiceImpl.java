package org.health.se7a.statistics;

import lombok.RequiredArgsConstructor;
import org.health.se7a.labtests.LabTestRepository;
import org.health.se7a.medications.MedicationRepository;
import org.health.se7a.vitalsigns.VitalSignsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final LabTestRepository labTestRepo;
    private final MedicationRepository medicationRepo;
    private final VitalSignsRepository vitalRepo;

    @Override
    public StatisticsResponseDTO getStatistics(StatisticsRequestDTO dto) {
        LocalDateTime from = dto.getStartDate().atStartOfDay();
        LocalDateTime to = dto.getEndDate().atTime(23, 59, 59);

        long totalLabTests = labTestRepo.countByTestDateBetween(from, to);
        long totalMedications = medicationRepo.countByAdministeredAtBetween(from, to);
        long totalVitalSigns = vitalRepo.countByRecordedAtBetween(from, to);

        return StatisticsResponseDTO.builder()
                .totalLabTests(totalLabTests)
                .totalMedications(totalMedications)
                .totalVitalSigns(totalVitalSigns)
                .range(dto.getStartDate() + " to " + dto.getEndDate())
                .build();
    }

    @Override
    public byte[] exportStatisticsAsPdf(StatisticsRequestDTO dto) {
        StatisticsResponseDTO stats = getStatistics(dto);
        return PdfGenerator.generateAdvancedStatisticsPdf(stats,"/home/osama/Desktop/se7a/src/main/resources/static/images/logo.png");
    }
}
