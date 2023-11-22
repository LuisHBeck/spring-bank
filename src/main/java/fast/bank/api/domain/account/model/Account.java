package fast.bank.api.domain.account.model;

import fast.bank.api.domain.user.model.User;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "Account")
@Entity(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {

    @Id
    private Long number;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "registry")
    private User user;

    private Integer agency;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Float balance;

    private Float creditLimit;

    private Boolean isActive;
}
