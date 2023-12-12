package fast.bank.api.domain.account.service.transfer;

import fast.bank.api.domain.account.dto.AccountTransactionRequestData;
import fast.bank.api.domain.account.dto.AccountTransactionResponseData;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.account.service.transfer.validation.AccountTransactionValidators;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTransactionService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private List<AccountTransactionValidators> validators;

    @Transactional
    public AccountTransactionResponseData transfer(AccountTransactionRequestData data) {
        validators.forEach(v -> v.validate(data));

        var receiverAcc = repository.getReferenceById(data.receiverAccNumber());
        var senderAcc = repository.getReferenceById(data.senderAccNumber());
        var transferAmount = data.transferAmount();

        senderAcc.discount(transferAmount);
        receiverAcc.debit(transferAmount);
        return new AccountTransactionResponseData(senderAcc.getNumber(), receiverAcc.getNumber(), transferAmount, senderAcc.getBalance());
    }
}
