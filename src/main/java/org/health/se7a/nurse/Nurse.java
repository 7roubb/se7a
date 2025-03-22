package org.health.se7a.nurse;

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
import org.health.se7a.users.User;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
public class Nurse extends User implements LoginUser {

    @Override
    public LoginType getType() {
        return LoginType.NURSE;
    }


}
