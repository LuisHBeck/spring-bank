package fast.bank.api.domain.account.service.transfer.validation;

import fast.bank.api.domain.account.dto.AccountTransactionData;

public interface AccountTransactionValidators {
    void validate(AccountTransactionData data);
}
