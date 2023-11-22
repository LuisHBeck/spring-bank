package fast.bank.api.domain.account.service.validation;

import fast.bank.api.domain.account.dto.AccountRegistrationData;
import fast.bank.api.domain.user.repository.UserRepository;
import fast.bank.api.infra.exception.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistingAndActiveUser implements AccountRegistrationValidators {

    @Autowired
    UserRepository repository;

    @Override
    public void validate(AccountRegistrationData data) {
        var userRegistry = data.userRegistry();
        var isValidUser = repository.existsById(userRegistry);
        var isUserActive = repository.findIsActiveById(userRegistry);
        if(!isValidUser || !isUserActive) throw new ValidException("Invalid or inactive user");
    }
}
