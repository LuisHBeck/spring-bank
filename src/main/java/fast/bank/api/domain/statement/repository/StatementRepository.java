package fast.bank.api.domain.statement.repository;

import fast.bank.api.domain.account.model.Account;
import fast.bank.api.domain.statement.model.Statement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatementRepository extends JpaRepository<Statement, Long> {
    Page<Statement> findAllByAccount(Optional<Account> account, Pageable pageable);
}
