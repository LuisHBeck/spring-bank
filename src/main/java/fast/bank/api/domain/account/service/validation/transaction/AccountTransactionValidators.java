package fast.bank.api.domain.account.service.validation.transaction;

import fast.bank.api.domain.account.dto.AccountTransactionRequestData;

public interface AccountTransactionValidators {
    void validate(AccountTransactionRequestData data);
}
