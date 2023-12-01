package fast.bank.api.domain.account.service.validation;

import fast.bank.api.domain.account.dto.AccountTransactionData;
import fast.bank.api.domain.account.service.transfer.AccountTransactionValidators;
import fast.bank.api.infra.exception.validation.ValidException;
import org.springframework.stereotype.Component;

@Component
public class SenderAndReceiverAreDifferent implements AccountTransactionValidators {

    @Override
    public void validate(AccountTransactionData data) {
        if(data.senderAccNumber() == data.receiverAccNumber()) throw new ValidException("Account that is transferring can't be the same as that will receive");
    }
}
