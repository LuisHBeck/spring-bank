package fast.bank.api.domain.user.service.registration;

import fast.bank.api.domain.user.dto.UserRegistrationData;
import fast.bank.api.domain.user.model.User;
import fast.bank.api.domain.user.repository.UserRepository;
import fast.bank.api.domain.user.service.registration.validation.UserRegistrationValidators;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private List<UserRegistrationValidators> validators;

    @Transactional
    public User createUser(UserRegistrationData data) {
        validators.forEach(v -> v.validate(data));

        var user = new User(data);
        repository.save(user);

        return user;
    }
}
