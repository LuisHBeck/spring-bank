package fast.bank.api.domain.user.service.deletion;

import fast.bank.api.domain.user.model.User;
import fast.bank.api.domain.user.repository.UserRepository;
import fast.bank.api.domain.user.service.deletion.validation.UserDeletionValidators;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDeletionService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private List<UserDeletionValidators> validators;

    @Transactional
    public void logicalUserDeletion(Long registry) {
        validators.forEach(v -> v.validate(registry));
        var user = repository.getReferenceById(registry);
        user.logicalDeletion();
    }
}
