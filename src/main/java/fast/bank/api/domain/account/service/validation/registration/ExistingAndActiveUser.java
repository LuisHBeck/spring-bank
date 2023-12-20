package fast.bank.api.domain.account.service.validation.registration;

import fast.bank.api.domain.account.dto.AccountRegistrationData;
import fast.bank.api.domain.user.repository.UserRepository;
import fast.bank.api.infra.exception.validation.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistingAndActiveUser implements AccountRegistrationValidators {

    @Autowired
    UserRepository repository;

    @Override
    public void validate(AccountRegistrationData data) {
        var userRegistry = data.userRegistry();
        var isValidAndActiveUser = repository.existsByRegistryAndIsActiveTrue(userRegistry);
        if(!isValidAndActiveUser) throw new ValidException("Invalid or inactive user");
    }
}
