package fast.bank.api.domain.account.service;

import fast.bank.api.domain.account.dto.AccountDetailingData;
import fast.bank.api.domain.account.dto.AccountRegistrationData;
import fast.bank.api.domain.account.dto.AccountTransactionRequestData;
import fast.bank.api.domain.account.dto.AccountTransactionResponseData;
import fast.bank.api.domain.account.model.Account;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.account.service.validation.activation.AccountActivationValidators;
import fast.bank.api.domain.account.service.validation.deletion.AccountDeletionValidators;
import fast.bank.api.domain.account.service.validation.registration.AccountRegistrationValidators;
import fast.bank.api.domain.account.service.validation.transaction.AccountTransactionValidators;
import fast.bank.api.domain.statement.model.Statement;
import fast.bank.api.domain.statement.model.TransactionType;
import fast.bank.api.domain.statement.repository.StatementRepository;
import fast.bank.api.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private List<AccountRegistrationValidators> registrationValidators;

    @Autowired
    private List<AccountTransactionValidators> transactionValidators;

    @Autowired
    private List<AccountDeletionValidators> deletionValidators;

    @Autowired
    private List<AccountActivationValidators> activationValidators;

    @Transactional
    public AccountDetailingData register(AccountRegistrationData data) {
        registrationValidators.forEach(v -> v.validate(data));

        var user = userRepository.getReferenceById(data.userRegistry());
        var accType = data.accountType();

        var account = new Account(user, 1111, accType, 1500.0, 2600.0);
        accountRepository.save(account);

        return new AccountDetailingData(user.getRegistry(), account.getNumber(), account.getAgency(), account.getBalance(), account.getCreditLimit());
    }

    @Transactional
    public AccountTransactionResponseData transfer(AccountTransactionRequestData data) {
        transactionValidators.forEach(v -> v.validate(data));

        var senderAcc = accountRepository.getReferenceById(data.senderAccNumber());
        var receiverAcc = accountRepository.getReferenceById(data.receiverAccNumber());
        var transferAmount = data.transferAmount();

        senderAcc.discount(transferAmount);
        statementRepository.save(new Statement(null, senderAcc, TransactionType.DEBIT, transferAmount, senderAcc.getBalance()));
        receiverAcc.debit(transferAmount);

        statementRepository.save(new Statement(null, receiverAcc, TransactionType.CREDIT, transferAmount, receiverAcc.getBalance()));

        return new AccountTransactionResponseData(senderAcc.getNumber(), receiverAcc.getNumber(), transferAmount, senderAcc.getBalance());
    }

    public void delete(Long number) {
        var account = accountRepository.getReferenceById(number);
        deletionValidators.forEach(v -> v.validate(account));

        account.logicalDeletion();
    }

    @Transactional
    public AccountDetailingData activate(Long number) {
        var account = accountRepository.getReferenceById(number);
        activationValidators.forEach(v -> v.validate(account));
        account.activate();

        return new AccountDetailingData(account);
    }


}
