package fast.bank.api.controllers;

import fast.bank.api.domain.account.dto.AccountDetailingData;
import fast.bank.api.domain.account.dto.AccountRegistrationData;
import fast.bank.api.domain.account.dto.AccountTransactionRequestData;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.account.service.AccountService;
import fast.bank.api.domain.card.dto.CardDetailingData;
import fast.bank.api.domain.card.dto.CardListData;
import fast.bank.api.domain.card.dto.CardTransactionData;
import fast.bank.api.domain.card.repository.CardRepository;
import fast.bank.api.domain.card.service.CardService;

import fast.bank.api.domain.statement.dto.StatementDetailingData;
import fast.bank.api.domain.statement.repository.StatementRepository;
import fast.bank.api.domain.statement.service.StatementService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CardService cardService;

    @Autowired
    private StatementService statementService;


    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid AccountRegistrationData data, UriComponentsBuilder uriBuilder) {
        var account = accountService.register(data);
        var uri = uriBuilder.path("api/v1/accounts{registry}").build(account.number());
        return ResponseEntity.created(uri).body(account);
    }

    @GetMapping
    public ResponseEntity<Page<AccountDetailingData>> list(@PageableDefault(size = 10, sort = {"number"}) Pageable pagination) {
        var page = accountRepository.findAllByIsActiveTrue(pagination).map(AccountDetailingData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{number}")
    public ResponseEntity listById(@PathVariable Long number) {
        var account = accountRepository.getReferenceById(number);
        return ResponseEntity.ok(new AccountDetailingData(account));
    }

    @DeleteMapping("/{number}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long number) {
        accountService.delete(number);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/activate/{number}")
    @Transactional
    public ResponseEntity activate(@PathVariable Long number) {
        var account = accountService.activate(number);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/transfer")
    @Transactional
    public ResponseEntity newTransfer(@RequestBody @Valid AccountTransactionRequestData data) {
        var transaction = accountService.transfer(data);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/{account}/statement")
    public ResponseEntity<Page<StatementDetailingData>> listStatements(@PathVariable Long account, @PageableDefault(size = 10, sort = {"id"}) Pageable pagination) {
        var page = statementService.list(account, pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{account}/statement/{id}")
    public ResponseEntity detailStatement(@PathVariable Long id) {
        var statement = statementRepository.getReferenceById(id);
        return ResponseEntity.ok(new StatementDetailingData(statement));
    }

    @PostMapping("/{account}/cards/new")
    @Transactional
    public ResponseEntity newCard(@PathVariable Long account, UriComponentsBuilder uriBuilder) {
        var card = cardService.register(account);
        var uri = uriBuilder.path("api/v1/accounts/{account}/cards/{number}").buildAndExpand(card.account(), card.number()).toUri();
        return ResponseEntity.created(uri).body(card);
    }

    @GetMapping("/{account}/cards")
    public ResponseEntity<Page<CardListData>> listCards(@PathVariable Long account, @PageableDefault(size = 10, sort = {"id"}) Pageable pagination) {
        var page = cardService.list(account, pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{account}/cards/{number}")
    public ResponseEntity detailingCard(@PathVariable Long account, @PathVariable Long number) {
        var card = cardRepository.getReferenceByNumber(number);
        return ResponseEntity.ok(new CardDetailingData(card));
    }

    @PostMapping("/{account}/cards/{number}/transaction")
    @Transactional
    public ResponseEntity cardTransaction(@PathVariable Long account, @PathVariable Long number, @RequestBody @Valid CardTransactionData data) {
        var transactionData = cardService.makeTransaction(data, account, number);
        return ResponseEntity.ok(transactionData);
    }
}
