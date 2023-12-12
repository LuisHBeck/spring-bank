package fast.bank.api.domain.statement.model;

import fast.bank.api.domain.account.model.Account;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Statement")
@Entity(name = "statement")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@ToString
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "acc_id", referencedColumnName = "number")
    private Account account;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Double amount;

    private Double postTransactionBalance;

}
