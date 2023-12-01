package fast.bank.api.domain.user.service.activation;

import fast.bank.api.domain.user.dto.UserDetailingData;
import fast.bank.api.domain.user.repository.UserRepository;
import fast.bank.api.domain.user.service.activation.validation.UserActivationValidators;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserActivationService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private List<UserActivationValidators> validators;

    @Transactional
    public UserDetailingData activate(Long registry) {
        var user = repository.getReferenceById(registry);

        validators.forEach(v -> v.validate(user));

        user.activate();
        return new UserDetailingData(user);
    }
}
