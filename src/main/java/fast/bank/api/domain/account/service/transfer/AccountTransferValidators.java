package fast.bank.api.domain.account.service.transfer;

import fast.bank.api.domain.account.dto.AccountTransferData;

public interface AccountTransferValidators {
    void validate(AccountTransferData data);
}
