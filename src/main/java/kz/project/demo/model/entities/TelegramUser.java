package kz.project.demo.model.entities;


import kz.project.demo.model.audit.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "telegram_users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TelegramUser extends AuditModel {

    @Column(name = "name")
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "authentication_user_id")
    private AuthorizedUser authorizedUser;

}
