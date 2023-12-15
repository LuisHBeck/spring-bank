package fast.bank.api.domain.card.dto;

import fast.bank.api.domain.card.model.Card;

public record CardListData(Long id, Long account, Long number, Double cardLimit) {
    public CardListData(Card card) {
        this(card.getId(), card.getAccount().getNumber(), card.getNumber(), card.getAccount().getCreditLimit());
    }
}
