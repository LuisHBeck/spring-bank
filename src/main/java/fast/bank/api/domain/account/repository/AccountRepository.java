package fast.bank.api.domain.account.repository;

import fast.bank.api.domain.account.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Page<Account> findAllByIsActiveTrue(Pageable pagination);

    boolean existsByNumberAndIsActiveTrue(Long aLong);
}
