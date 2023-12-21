package fast.bank.api.domain.user.service.validation.activation;

import fast.bank.api.domain.user.model.User;
import fast.bank.api.infra.exception.validation.ValidException;
import org.springframework.stereotype.Component;

@Component
public class ActiveUser implements UserActivationValidators {

    @Override
    public void validate(User user) {
        if(user.getIsActive()) throw new ValidException("User is already active");
    }
}
