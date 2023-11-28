package fast.bank.api.domain.user.service.registration;

import fast.bank.api.domain.user.dto.UserRegistrationData;

public interface UserRegistrationValidators {
    void validate(UserRegistrationData data);
}
