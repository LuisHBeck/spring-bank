package fast.bank.api.domain.account.service.activation;

import fast.bank.api.domain.account.dto.AccountDetailingData;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.account.service.activation.validation.AccountActivationValidators;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountActivationService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private List<AccountActivationValidators> validators;

    @Transactional
    public AccountDetailingData activateAccount(Long number) {
        var account = repository.getReferenceById(number);
        validators.forEach(v -> v.validate(account));
        account.activate();

        return new AccountDetailingData(account);
    }
}
