package fast.bank.api.domain.card.service.list;

import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.card.dto.CardListData;
import fast.bank.api.domain.card.repository.CardRepository;
import fast.bank.api.domain.statement.service.list.StatementListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CardListingService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Page<CardListData> list(Long accountNumber, Pageable pageable) {
        var account = accountRepository.findById(accountNumber);
        var cards = cardRepository.findAllByAccount(account, pageable);
        return cards.map(CardListData::new);
    }
}
