package fast.bank.api.domain.account.repository;

import fast.bank.api.domain.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
