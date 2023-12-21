package fast.bank.api.domain.user.service.validation.registration;

import fast.bank.api.domain.user.dto.UserRegistrationData;
import fast.bank.api.domain.user.repository.UserRepository;
import fast.bank.api.infra.exception.validation.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueUser implements UserRegistrationValidators {

    @Autowired
    private UserRepository repository;

    @Override
    public void validate(UserRegistrationData data) {
        var userExists = repository.existsById(data.registry());
        if(userExists) throw new ValidException("User with this registry already exists!");
    }
}
