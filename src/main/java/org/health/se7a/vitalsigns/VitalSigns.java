package org.health.se7a.vitalsigns;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.health.se7a.nurse.Nurse;
import org.health.se7a.patients.Patients;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class VitalSigns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_nat_id", referencedColumnName = "nationalityID", nullable = false)
    @JsonIgnoreProperties("vitalSigns")
    private Patients patient;

    @ManyToOne
    @JoinColumn(name = "nurse_id", nullable = false)
    private Nurse nurse;

    private Double bloodPressure;
    private Integer heartRate;
    private Double temperature;
    private Integer respiratoryRate;
    private LocalDateTime recordedAt;
}
