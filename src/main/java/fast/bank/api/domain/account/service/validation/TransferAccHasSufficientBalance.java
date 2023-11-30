package fast.bank.api.domain.account.service.validation;

import fast.bank.api.domain.account.dto.AccountTransferData;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.account.service.transfer.AccountTransferValidators;
import fast.bank.api.infra.exception.validation.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferAccHasSufficientBalance implements AccountTransferValidators {

    @Autowired
    private AccountRepository repository;

    @Override
    public void validate(AccountTransferData data) {
        var transferAcc = repository.getReferenceById(data.transferringAccNumber());
        var balance = transferAcc.getBalance();
        if(data.transferAmount() > balance) throw new ValidException("Insufficient balance for this transfer!");
    }
}
