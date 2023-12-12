package fast.bank.api.domain.account.service.transfer;

import fast.bank.api.domain.account.dto.AccountTransactionRequestData;
import fast.bank.api.domain.account.dto.AccountTransactionResponseData;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.account.service.transfer.validation.AccountTransactionValidators;
import fast.bank.api.domain.statement.model.Statement;
import fast.bank.api.domain.statement.model.TransactionType;
import fast.bank.api.domain.statement.repository.StatementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTransactionService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private List<AccountTransactionValidators> validators;

    @Transactional
    public AccountTransactionResponseData transfer(AccountTransactionRequestData data) {
        validators.forEach(v -> v.validate(data));

        var senderAcc = repository.getReferenceById(data.senderAccNumber());
        var receiverAcc = repository.getReferenceById(data.receiverAccNumber());
        var transferAmount = data.transferAmount();

        senderAcc.discount(transferAmount);
        statementRepository.save(new Statement(null, senderAcc, TransactionType.DEBIT, transferAmount, senderAcc.getBalance()));
        receiverAcc.debit(transferAmount);

        statementRepository.save(new Statement(null, receiverAcc, TransactionType.CREDIT, transferAmount, receiverAcc.getBalance()));

        return new AccountTransactionResponseData(senderAcc.getNumber(), receiverAcc.getNumber(), transferAmount, senderAcc.getBalance());
    }
}
