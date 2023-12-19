package fast.bank.api.domain.card.service.transaction.validation;

import fast.bank.api.domain.card.dto.CardTransactionData;
import fast.bank.api.domain.card.repository.CardRepository;
import fast.bank.api.infra.exception.validation.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IsValidCard implements CardTransactionValidators{

    @Autowired
    private CardRepository repository;


    @Override
    public void validate(CardTransactionData data, Long cardNumber) {
        var isValid = repository.existsByNumber(cardNumber);
        if(!isValid) throw new ValidException("Invalid card number!");
    }
}
