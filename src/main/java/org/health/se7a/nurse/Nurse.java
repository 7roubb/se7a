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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
public class Nurse extends BaseEntity implements LoginUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String telNumber;

    @Override
    public LoginType getType() {
        return LoginType.NURSE;
    }

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

}
