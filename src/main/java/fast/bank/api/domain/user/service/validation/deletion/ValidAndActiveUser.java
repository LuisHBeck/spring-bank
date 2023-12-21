package fast.bank.api.domain.user.service.validation.deletion;

import fast.bank.api.domain.user.repository.UserRepository;
import fast.bank.api.infra.exception.validation.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidAndActiveUser implements UserDeletionValidators{

    @Autowired
    private UserRepository repository;


    @Override
    public void validate(Long number) {
        var isValidAndActiveUser = repository.existsByRegistryAndIsActiveTrue(number);

        if(!isValidAndActiveUser) throw new ValidException("Invalid or inactivate user!");
    }
}
