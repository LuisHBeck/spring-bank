package fast.bank.api.domain.card.model;

import fast.bank.api.domain.account.model.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "Card")
@Entity(name = "card")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_acc", referencedColumnName = "number")
    private Account account;

    private Long number;

    private LocalDate expirationDate;

    private Integer cvv;
}
