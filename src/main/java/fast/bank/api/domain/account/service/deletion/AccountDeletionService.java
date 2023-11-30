package fast.bank.api.domain.account.service.deletion;

import fast.bank.api.domain.account.model.Account;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.account.service.validation.ValidAndActiveAcc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDeletionService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private ValidAndActiveAcc validAndActiveAcc;

    public void logicalAccountDeletion(Account account) {
        account.logicalDeletion();
    }
}
