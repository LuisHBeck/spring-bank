package fast.bank.api.domain.card.service.registration;

import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.card.dto.CardDetailingData;
import fast.bank.api.domain.card.model.Card;
import fast.bank.api.domain.card.repository.CardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CardRegistrationService {

    @Autowired
    private CardRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public CardDetailingData register(Long accountId) {
        Long cardNumber = CardNumberGenerator.generate(12);
        Integer cvv = CardNumberGenerator.generate(3).intValue();
        LocalDate expirationDate = CardExpirationCalculator.calculateExpirationDate(24);
        var account = accountRepository.getReferenceById(accountId);

        var card = new Card(null, account, cardNumber, expirationDate, cvv);
        repository.save(card);

        return new CardDetailingData(card);
    }
}
