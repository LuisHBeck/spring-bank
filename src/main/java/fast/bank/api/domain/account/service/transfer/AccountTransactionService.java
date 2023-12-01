package fast.bank.api.domain.account.service.transfer;

import fast.bank.api.domain.account.dto.AccountTransactionData;
import fast.bank.api.domain.account.repository.AccountRepository;
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
    public AccountTransactionData transfer(AccountTransactionData data) {
        validators.forEach(v -> v.validate(data));

        var receiverAcc = repository.getReferenceById(data.receiverAccNumber());
        var senderAcc = repository.getReferenceById(data.senderAccNumber());
        var transferAmount = data.transferAmount();

        senderAcc.discount(transferAmount);
        receiverAcc.debit(transferAmount);
        return data;
    }
}
