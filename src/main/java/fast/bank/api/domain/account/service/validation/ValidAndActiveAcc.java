package fast.bank.api.domain.account.service.validation;

import fast.bank.api.domain.account.dto.AccountTransferData;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.account.service.transfer.AccountTransferValidators;
import fast.bank.api.infra.exception.validation.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidAndActiveAcc implements AccountTransferValidators {

    @Autowired
    private AccountRepository repository;

    @Override
    public void validate(AccountTransferData data) {
        var transferringAccIsActiveAndValid = repository.existsByNumberAndIsActiveTrue(data.transferringAccNumber());
        var receiverAccIsActiveAndValid = repository.existsByNumberAndIsActiveTrue(data.receiverAccNumber());

        if(!transferringAccIsActiveAndValid) throw new ValidException("Invalid or inactive transferring account");
        if(!receiverAccIsActiveAndValid) throw new ValidException("Invalid or inactive receiver account");
    }
}
