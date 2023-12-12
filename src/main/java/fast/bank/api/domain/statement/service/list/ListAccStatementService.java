package fast.bank.api.domain.statement.service.list;

import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.statement.dto.StatementDetailingData;
import fast.bank.api.domain.statement.repository.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAccStatementService {

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Page<StatementDetailingData> list(Long accountNumber, Pageable pageable) {
        var account = accountRepository.findById(accountNumber);
        var statements = statementRepository.findAllByAccount(account, pageable);
        return statements.map(StatementDetailingData::new);
    }
}
