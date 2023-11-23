package fast.bank.api.domain.user.service;

import fast.bank.api.domain.user.model.User;
import fast.bank.api.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDeletionService {

    @Autowired
    private UserRepository repository;

    public void logicalUserDeletion(Long registry) {
        var user = repository.getReferenceById(registry);
        user.logicalDeletion();
    }
}
