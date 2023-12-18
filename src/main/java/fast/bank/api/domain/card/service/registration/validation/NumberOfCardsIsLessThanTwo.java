package fast.bank.api.domain.card.service.registration.validation;

import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.card.repository.CardRepository;
import fast.bank.api.infra.exception.validation.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberOfCardsIsLessThanTwo implements CardRegistrationValidators{

    @Autowired
    private CardRepository repository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void validate(Long accountNumber) {
        var account = accountRepository.getReferenceById(accountNumber);
        var cards = repository.countByAccount(account);

        if(cards >= 2) throw new ValidException("You have reached the card limit (2) for your account! Use your active cards!");
    }
}
