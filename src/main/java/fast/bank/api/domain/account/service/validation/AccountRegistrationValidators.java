package fast.bank.api.domain.account.service.validation;

import fast.bank.api.domain.account.dto.AccountRegistrationData;

public interface AccountRegistrationValidators {
    void validate(AccountRegistrationData data);
}
