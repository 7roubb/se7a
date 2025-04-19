package org.health.se7a.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @PostMapping
    public StatisticsResponseDTO getStats(@RequestBody StatisticsRequestDTO dto) {
        return statisticsService.getStatistics(dto);
    }

    @PostMapping("/export-pdf")
    public ResponseEntity<byte[]> exportStatsPdf(@RequestBody StatisticsRequestDTO dto) {
        byte[] pdf = statisticsService.exportStatisticsAsPdf(dto);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=stats.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
