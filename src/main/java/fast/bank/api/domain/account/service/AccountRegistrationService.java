package fast.bank.api.domain.account.service;

import fast.bank.api.domain.account.dto.AccountRegistrationData;
import fast.bank.api.domain.account.model.Account;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.account.service.validation.AccountRegistrationValidators;
import fast.bank.api.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRegistrationService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private List<AccountRegistrationValidators> validators;

    public Account createAccount(AccountRegistrationData data) {
        validators.forEach(v -> v.validate(data));

        var user = userRepository.getReferenceById(data.userRegistry());
        var accType = data.accountType();

        var account = new Account(null, user, 1111, accType, 1500F, 2600F, true);
        return account;
    }
}
