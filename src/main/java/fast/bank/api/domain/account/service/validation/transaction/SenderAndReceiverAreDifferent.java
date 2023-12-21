package fast.bank.api.domain.account.service.validation.transaction;

import fast.bank.api.domain.account.dto.AccountTransactionRequestData;
import fast.bank.api.infra.exception.validation.ValidException;
import org.springframework.stereotype.Component;

@Component
public class SenderAndReceiverAreDifferent implements AccountTransactionValidators {

    @Override
    public void validate(AccountTransactionRequestData data) {
        if(data.senderAccNumber() == data.receiverAccNumber()) throw new ValidException("Account that is transferring can't be the same as that will receive");
    }
}
