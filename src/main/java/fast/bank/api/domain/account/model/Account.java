package fast.bank.api.domain.account.model;

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

    private Integer agency;

    private AccountType accountType;

    private Float balance;

    private Float creditLimit;

    private Boolean isActive;
}
