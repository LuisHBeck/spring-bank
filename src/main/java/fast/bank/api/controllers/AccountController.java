package fast.bank.api.controllers;

import fast.bank.api.domain.account.dto.AccountRegistrationData;
import fast.bank.api.domain.account.repository.AccountRepository;
import fast.bank.api.domain.account.service.AccountRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountRegistrationService registrationService;

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid AccountRegistrationData data) {
        var account = registrationService.createAccount(data);
        return ResponseEntity.ok(account);
    }
}
