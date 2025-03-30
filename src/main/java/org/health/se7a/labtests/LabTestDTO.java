package org.health.se7a.labtests;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LabTestDTO {
    private Long id;
    private String patientNatId;
    private String testName;
    private String result;
    private LocalDateTime testDate;
}
