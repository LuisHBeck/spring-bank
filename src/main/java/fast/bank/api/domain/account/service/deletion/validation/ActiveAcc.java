package fast.bank.api.domain.account.service.deletion.validation;

import fast.bank.api.domain.account.model.Account;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.infra.exception.validation.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveAcc implements AccountDeletionValidators {

    @Autowired
    private AccountRepository repository;

    @Override
    public void validate(Account account) {
        var isActiveAcc = account.getIsActive();
        if(!isActiveAcc) throw new ValidException("Account is already inactivate!");
    }
}
