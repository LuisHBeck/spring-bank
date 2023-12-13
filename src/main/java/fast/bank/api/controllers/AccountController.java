package fast.bank.api.controllers;

import fast.bank.api.domain.account.dto.AccountDetailingData;
import fast.bank.api.domain.account.dto.AccountRegistrationData;
import fast.bank.api.domain.account.dto.AccountTransactionRequestData;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.account.service.activation.AccountActivationService;
import fast.bank.api.domain.account.service.deletion.AccountDeletionService;
import fast.bank.api.domain.account.service.registration.AccountRegistrationService;
import fast.bank.api.domain.account.service.transfer.AccountTransactionService;
import fast.bank.api.domain.statement.dto.StatementDetailingData;
import fast.bank.api.domain.statement.service.list.ListAccStatementService;
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
    private AccountRepository repository;

    @Autowired
    private AccountRegistrationService registrationService;

    @Autowired
    private AccountDeletionService deletionService;

    @Autowired
    private AccountActivationService activationService;

    @Autowired
    private AccountTransactionService transactionService;

    @Autowired
    private ListAccStatementService listAccStatementService;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid AccountRegistrationData data, UriComponentsBuilder uriBuilder) {
        var account = registrationService.createAccount(data);
        var uri = uriBuilder.path("api/v1/accounts{registry}").build(account.number());
        return ResponseEntity.created(uri).body(account);
    }

    @GetMapping
    public ResponseEntity<Page<AccountDetailingData>> list(@PageableDefault(size = 10, sort = {"number"}) Pageable pagination) {
        var page = repository.findAllByIsActiveTrue(pagination).map(AccountDetailingData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{number}")
    public ResponseEntity listById(@PathVariable Long number) {
        var account = repository.getReferenceById(number);
        return ResponseEntity.ok(new AccountDetailingData(account));
    }

    @DeleteMapping("/{number}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long number) {
        deletionService.logicalAccountDeletion(number);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/activate/{number}")
    @Transactional
    public ResponseEntity activate(@PathVariable Long number) {
        var account = activationService.activateAccount(number);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/transfer")
    @Transactional
    public ResponseEntity newTransfer(@RequestBody @Valid AccountTransactionRequestData data) {
        var transaction = transactionService.transfer(data);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/{account}/statement")
    public ResponseEntity<Page<StatementDetailingData>> list(@PathVariable Long account, @PageableDefault(size = 10, sort = {"id"}) Pageable pagination) {
        var page = listAccStatementService.list(account, pagination);
        return ResponseEntity.ok(page);
    }
}
