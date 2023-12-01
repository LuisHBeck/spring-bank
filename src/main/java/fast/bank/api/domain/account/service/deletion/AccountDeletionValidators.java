package fast.bank.api.domain.account.service.deletion;

import fast.bank.api.domain.account.model.Account;

public interface AccountDeletionValidators {
    void validate(Account account);
}
