package fast.bank.api.domain.account.service.transfer.validation;

import fast.bank.api.domain.account.dto.AccountTransactionRequestData;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.infra.exception.validation.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidAndActiveSenderAndReceiverAcc implements AccountTransactionValidators {

    @Autowired
    private AccountRepository repository;

    @Override
    public void validate(AccountTransactionRequestData data) {
        var senderAccIsActiveAndValid = repository.existsByNumberAndIsActiveTrue(data.senderAccNumber());
        var receiverAccIsActiveAndValid = repository.existsByNumberAndIsActiveTrue(data.receiverAccNumber());

        if(!senderAccIsActiveAndValid) throw new ValidException("Invalid or inactive sender account");
        if(!receiverAccIsActiveAndValid) throw new ValidException("Invalid or inactive receiver account");
    }
}
