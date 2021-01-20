package kz.project.demo.model.entities;


import kz.project.demo.model.audit.AuditModel;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "telegram_users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TelegramUser extends AuditModel {

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "phone_nubmer")
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "authentication_user_id")
    private AuthorizedUser authorizedUser;

}
