package org.health.se7a.patients;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.health.se7a.doctor.Doctor;
import org.health.se7a.nurse.Nurse;
import org.health.se7a.security.model.AccountStatus;
import org.health.se7a.users.User;
import org.health.se7a.vitalsigns.VitalSigns;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Patients {

    @Id
    @GeneratedValue(generator = "IDGenerator")
    @GenericGenerator(name = "IDGenerator", strategy = "org.health.se7a.common.IDGenerator")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String name;

    private String telNumber;


    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(unique = true, nullable = false)
    private String nationalityID;


    private Long age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany
    @JoinTable(
            name = "doctor_patients",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctor> doctors;

    @ManyToMany
    @JoinTable(
            name = "nurse_patients",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "nurse_id")
    )
    private List<Nurse> nurses;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<MedicalHistory> medicalHistories;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VitalSigns> vitalSigns;
}
