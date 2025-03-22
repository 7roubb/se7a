package org.health.se7a.nurse;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.health.se7a.common.BaseEntity;
import org.health.se7a.patients.NursePatient;
import org.health.se7a.security.model.AccountStatus;
import org.health.se7a.security.model.LoginType;
import org.health.se7a.security.model.LoginUser;
import org.health.se7a.users.User;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
public class Nurse extends User implements LoginUser {

    @OneToMany(mappedBy = "nurse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NursePatient> nursePatients;

    @Override
    public LoginType getType() {
        return LoginType.NURSE;
    }


}
