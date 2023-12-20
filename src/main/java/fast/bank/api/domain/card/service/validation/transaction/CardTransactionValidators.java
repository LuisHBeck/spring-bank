package fast.bank.api.domain.card.service.validation.transaction;

import fast.bank.api.domain.card.dto.CardTransactionData;

public interface CardTransactionValidators {
    void validate(CardTransactionData data, Long cardNumber);
}
