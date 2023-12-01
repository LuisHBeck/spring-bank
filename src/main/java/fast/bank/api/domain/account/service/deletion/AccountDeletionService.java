package fast.bank.api.domain.account.service.deletion;

import fast.bank.api.domain.account.model.Account;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.account.service.validation.ValidAndActiveAcc;
import fast.bank.api.domain.account.service.validation.ValidAndActiveSenderAndREceiverAcc;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDeletionService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private List<AccountDeletionValidators> validators;

    @Transactional
    public void logicalAccountDeletion(Long number) {
        var account = repository.getReferenceById(number);
        validators.forEach(v -> v.validate(account));

        account.logicalDeletion();
    }
}
