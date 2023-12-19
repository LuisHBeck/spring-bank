package fast.bank.api.domain.card.service.transaction;

import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.card.dto.CardTransactionData;
import fast.bank.api.domain.card.repository.CardRepository;
import fast.bank.api.domain.statement.dto.StatementDetailingData;
import fast.bank.api.domain.statement.model.Statement;
import fast.bank.api.domain.statement.model.TransactionType;
import fast.bank.api.domain.statement.repository.StatementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardTransactionService {

    @Autowired
    private CardRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StatementRepository statementRepository;

    @Transactional
    public StatementDetailingData makeTransaction(CardTransactionData data, Long accountNumber) {
        var account = accountRepository.getReferenceById(accountNumber);
        account.cardDiscount(data.amount());

        var statement = new Statement(null, account, TransactionType.CARD, data.amount(), account.getCreditLimit());
        statementRepository.save(statement);
        return new StatementDetailingData(statement);
    }
}
