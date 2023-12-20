package fast.bank.api.domain.account.service;

import fast.bank.api.domain.account.dto.AccountDetailingData;
import fast.bank.api.domain.account.dto.AccountRegistrationData;
import fast.bank.api.domain.account.model.Account;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.account.service.validation.registration.AccountRegistrationValidators;
import fast.bank.api.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private List<AccountRegistrationValidators> registrationValidators;

    @Transactional
    public AccountDetailingData register(AccountRegistrationData data) {
        registrationValidators.forEach(v -> v.validate(data));

        var user = userRepository.getReferenceById(data.userRegistry());
        var accType = data.accountType();

        var account = new Account(user, 1111, accType, 1500.0, 2600.0);
        accountRepository.save(account);

        return new AccountDetailingData(user.getRegistry(), account.getNumber(), account.getAgency(), account.getBalance(), account.getCreditLimit());
    }

    public void transfer() {

    }

    public void delete() {

    }

    public void activate() {

    }


}
