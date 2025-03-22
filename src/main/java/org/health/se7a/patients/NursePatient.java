package org.health.se7a.patients;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.health.se7a.nurse.Nurse;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NursePatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nurse_id", referencedColumnName = "id", nullable = false)
    private Nurse nurse;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patients patient;

    // Additional fields if needed (e.g., shift times, treatment records, etc.)
}