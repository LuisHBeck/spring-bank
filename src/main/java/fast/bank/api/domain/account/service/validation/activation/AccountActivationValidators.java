package fast.bank.api.domain.account.service.validation.activation;

import fast.bank.api.domain.account.model.Account;

public interface AccountActivationValidators {
    void validate(Account account);
}
