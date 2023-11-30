package fast.bank.api.domain.account.service.validation;

import fast.bank.api.domain.account.dto.AccountTransferData;
import fast.bank.api.domain.account.service.transfer.AccountTransferValidators;
import fast.bank.api.infra.exception.validation.ValidException;
import org.springframework.stereotype.Component;

@Component
public class TransferorAndReceiverAreDifferent implements AccountTransferValidators {

    @Override
    public void validate(AccountTransferData data) {
        if(data.transferringAccNumber() == data.receiverAccNumber()) throw new ValidException("Account that is transferring can't be the same as that will receive");
    }
}
