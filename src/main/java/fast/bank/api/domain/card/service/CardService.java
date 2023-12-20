package fast.bank.api.domain.card.service;

import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.card.dto.CardDetailingData;
import fast.bank.api.domain.card.dto.CardListData;
import fast.bank.api.domain.card.dto.CardTransactionData;
import fast.bank.api.domain.card.model.Card;
import fast.bank.api.domain.card.repository.CardRepository;
import fast.bank.api.domain.card.service.validation.registration.CardRegistrationValidators;
import fast.bank.api.domain.card.service.validation.transaction.CardTransactionValidators;
import fast.bank.api.domain.statement.dto.StatementDetailingData;
import fast.bank.api.domain.statement.model.Statement;
import fast.bank.api.domain.statement.model.TransactionType;
import fast.bank.api.domain.statement.repository.StatementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;


@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private List<CardRegistrationValidators> registrationValidators;

    @Autowired
    private List<CardTransactionValidators> transactionValidators;

    @Transactional
    public CardDetailingData register(Long accountNumber) {
        registrationValidators.forEach(v -> v.validate(accountNumber));

        Long cardNumber = cardNumberGenerator(12);
        Integer cvv = cardNumberGenerator(3).intValue();
        LocalDate expirationDate = calculateExpirationDate(24);
        var account = accountRepository.getReferenceById(accountNumber);

        var card = new Card(null, account, cardNumber, expirationDate, cvv);
        cardRepository.save(card);

        return new CardDetailingData(card);
    }

    public Page<CardListData> list(Long accountNumber, Pageable pageable) {
        var account = accountRepository.findById(accountNumber);
        var cards = cardRepository.findAllByAccount(account, pageable);
        return cards.map(CardListData::new);
    }

    @Transactional
    public StatementDetailingData makeTransaction(CardTransactionData data, Long accountNumber, Long cardNumber) {
        transactionValidators.forEach(v -> v.validate(data, cardNumber));

        var account = accountRepository.getReferenceById(accountNumber);
        account.cardDiscount(data.amount());

        var statement = new Statement(null, account, TransactionType.CARD, data.amount(), account.getCreditLimit());
        statementRepository.save(statement);
        return new StatementDetailingData(statement);
    }

    // AUXILIARY FUNCTIONS ->
    private Long cardNumberGenerator(int numberLength) {
        Random random = new Random();
        Long number = 0l;

        for (int i =0; i < numberLength; i++) {
            int digit = random.nextInt(10);
            number = number * 10 + digit;
        }
        return number;
    }

    private LocalDate calculateExpirationDate(int monthsDuration) {
        var today = LocalDate.now();
        return today.plusMonths(12);
    }

}
