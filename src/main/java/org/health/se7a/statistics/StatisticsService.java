package org.health.se7a.statistics;

public interface StatisticsService {
    StatisticsResponseDTO getStatistics(StatisticsRequestDTO dto);
    ExportedPdfDTO exportStatisticsAsPdf(StatisticsRequestDTO dto);
}
