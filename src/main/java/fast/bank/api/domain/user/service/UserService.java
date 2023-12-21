package fast.bank.api.domain.user.service;

import fast.bank.api.domain.user.dto.UserDetailingData;
import fast.bank.api.domain.user.dto.UserRegistrationData;
import fast.bank.api.domain.user.model.User;
import fast.bank.api.domain.user.repository.UserRepository;
import fast.bank.api.domain.user.service.validation.activation.UserActivationValidators;
import fast.bank.api.domain.user.service.validation.deletion.UserDeletionValidators;
import fast.bank.api.domain.user.service.validation.registration.UserRegistrationValidators;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private List<UserRegistrationValidators> registrationValidators;

    @Autowired
    private List<UserDeletionValidators> deletionValidators;

    @Autowired
    private List<UserActivationValidators> activationValidators;

    @Transactional
    public User register(UserRegistrationData data) {
        registrationValidators.forEach(v -> v.validate(data));

        var user = new User(data);
        userRepository.save(user);

        return user;
    }

    @Transactional
    public void delete(Long registry) {
        deletionValidators.forEach(v -> v.validate(registry));

        var user = userRepository.getReferenceById(registry);
        user.logicalDeletion();
    }

    @Transactional
    public UserDetailingData activate(Long registry) {
        var user = userRepository.getReferenceById(registry);

        activationValidators.forEach(v -> v.validate(user));

        user.activate();
        return new UserDetailingData(user);
    }
}
