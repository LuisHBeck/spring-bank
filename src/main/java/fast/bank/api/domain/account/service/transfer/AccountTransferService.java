package fast.bank.api.domain.account.service.transfer;

import fast.bank.api.domain.account.dto.AccountTransferData;
import fast.bank.api.domain.account.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTransferService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private List<AccountTransferValidators> validators;

    @Transactional
    public AccountTransferData transfer(AccountTransferData data) {
        validators.forEach(v -> v.validate(data));

        var receiverAcc = repository.getReferenceById(data.receiverAccNumber());
        var transferringAcc = repository.getReferenceById(data.transferringAccNumber());
        var transferAmount = data.transferAmount();

        transferringAcc.discount(transferAmount);
        receiverAcc.debit(transferAmount);
        return data;
    }
}
