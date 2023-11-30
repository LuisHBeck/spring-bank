package fast.bank.api.domain.account.model;

import fast.bank.api.domain.user.model.User;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Account")
@Entity(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long number;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "registry")
    private User user;

    private Integer agency;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Double balance;

    private Double creditLimit;

    private Boolean isActive;

    public Account(User user, int agency, AccountType accType, double balance, double creditLimit) {
        this.user = user;
        this.agency = agency;
        this.accountType = accType;
        this.balance = balance;
        this.creditLimit = creditLimit;
        this.isActive = true;
    }

    public void discount(Double transferAmount) {
        this.balance -= transferAmount;
    }

    public void debit(Double transferAmount) {
        this.balance += transferAmount;
    }

    public void logicalDeletion() {
        this.isActive = false;
    }
}
