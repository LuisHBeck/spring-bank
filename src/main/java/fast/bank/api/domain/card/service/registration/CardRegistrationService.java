package fast.bank.api.domain.card.service.registration;

import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.card.dto.CardDetailingData;
import fast.bank.api.domain.card.model.Card;
import fast.bank.api.domain.card.repository.CardRepository;
import fast.bank.api.domain.card.service.registration.validation.CardRegistrationValidators;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CardRegistrationService {

    @Autowired
    private CardRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private List<CardRegistrationValidators> validators;

    @Transactional
    public CardDetailingData register(Long accountNumber) {
        validators.forEach(v -> v.validate(accountNumber));

        Long cardNumber = CardNumberGenerator.generate(12);
        Integer cvv = CardNumberGenerator.generate(3).intValue();
        LocalDate expirationDate = CardExpirationCalculator.calculateExpirationDate(24);
        var account = accountRepository.getReferenceById(accountNumber);

        var card = new Card(null, account, cardNumber, expirationDate, cvv);
        repository.save(card);

        return new CardDetailingData(card);
    }
}
