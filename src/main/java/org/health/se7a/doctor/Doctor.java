package org.health.se7a.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.health.se7a.common.BaseEntity;
import org.health.se7a.security.model.AccountStatus;
import org.health.se7a.security.model.LoginType;
import org.health.se7a.security.model.LoginUser;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(
        indexes = {
                @Index(name = "idx_doctor_name", columnList = "name"),
                @Index(name = "idx_doctor_telNumber", columnList = "telNumber"),
                @Index(name = "idx_doctor_licenceNumber", columnList = "licenceNumber")
        }
)
public class Doctor extends BaseEntity implements LoginUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String telNumber;

    private String specialty;

    private String licenceNumber;

    @Override
    public LoginType getType() {
        return LoginType.DOCTOR;
    }


    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.ACTIVE;
}
