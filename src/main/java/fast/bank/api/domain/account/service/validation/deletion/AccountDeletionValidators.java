package fast.bank.api.domain.account.service.validation.deletion;

import fast.bank.api.domain.account.model.Account;

public interface AccountDeletionValidators {
    void validate(Account account);
}
