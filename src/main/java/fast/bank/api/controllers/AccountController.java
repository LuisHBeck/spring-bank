package fast.bank.api.controllers;

import fast.bank.api.domain.account.dto.AccountDetailingData;
import fast.bank.api.domain.account.dto.AccountRegistrationData;
import fast.bank.api.domain.account.dto.AccountTransferData;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.account.service.registration.AccountRegistrationService;
import fast.bank.api.domain.account.service.transfer.AccountTransferService;
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
    private AccountTransferService transferService;

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

    @PostMapping("/transfer")
    @Transactional
    public ResponseEntity newTransfer(@RequestBody @Valid AccountTransferData data) {
        var transference = transferService.transfer(data);
        return ResponseEntity.ok(transference);
    }

}
