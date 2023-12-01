package fast.bank.api.domain.account.service.validation;

import fast.bank.api.domain.account.model.Account;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.account.service.deletion.AccountDeletionValidators;
import fast.bank.api.infra.exception.validation.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidAndActiveAcc implements AccountDeletionValidators {

    @Autowired
    private AccountRepository repository;

    @Override
    public void validate(Account account) {
        var isValidAndActiveAcc = repository.existsByNumberAndIsActiveTrue(account.getNumber());
        if(!isValidAndActiveAcc) throw new ValidException("Invalid or inactivate account!");
    }
}
