package fast.bank.api.domain.account.service.activation.validation;

import fast.bank.api.domain.account.model.Account;

public interface AccountActivationValidators {
    void validate(Account account);
}
