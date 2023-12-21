package fast.bank.api.domain.user.service.validation.activation;

import fast.bank.api.domain.user.model.User;

public interface UserActivationValidators {
    void validate(User user);
}
