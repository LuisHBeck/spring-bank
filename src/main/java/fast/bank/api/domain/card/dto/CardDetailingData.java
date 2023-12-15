package fast.bank.api.domain.card.dto;

import fast.bank.api.domain.card.model.Card;

import java.time.LocalDate;

public record CardDetailingData(Long id, Long account, Long number, LocalDate expirationDate, Integer cvv, Double cardLimit) {
    public CardDetailingData(Card card) {
        this(card.getId(), card.getAccount().getNumber(), card.getNumber(), card.getExpirationDate(), card.getCvv(), card.getAccount().getCreditLimit());
    }
}
