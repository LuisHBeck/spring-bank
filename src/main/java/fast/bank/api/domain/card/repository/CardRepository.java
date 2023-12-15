package fast.bank.api.domain.card.repository;

import fast.bank.api.domain.account.model.Account;
import fast.bank.api.domain.card.model.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Page<Card> findAllByAccount(Optional<Account> account, Pageable pageable);

    Card getReferenceByNumber(Long number);
}
