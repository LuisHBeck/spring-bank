package fast.bank.api.domain.account.service.transfer.validation;

import fast.bank.api.domain.account.dto.AccountTransactionRequestData;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.infra.exception.validation.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SenderAccHasSufficientBalance implements AccountTransactionValidators {

    @Autowired
    private AccountRepository repository;

    @Override
    public void validate(AccountTransactionRequestData data) {
        var senderAcc = repository.getReferenceById(data.senderAccNumber());
        var balance = senderAcc.getBalance();
        if(data.transferAmount() > balance) throw new ValidException("Insufficient balance for this transfer!");
    }
}
