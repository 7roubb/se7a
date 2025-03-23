package org.health.se7a.diagnosis;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.health.se7a.doctor.Doctor;
import org.health.se7a.patients.Patients;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diagnosis_id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patients patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    private LocalDateTime date;
    private String notes;

    @Enumerated(EnumType.STRING)
    private DiagnosisStatus status;
}