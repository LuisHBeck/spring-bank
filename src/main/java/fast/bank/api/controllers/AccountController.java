package fast.bank.api.controllers;

import fast.bank.api.domain.account.dto.AccountRegistrationData;
import fast.bank.api.domain.account.repository.AccountRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository repository;

    @PostMapping
    public void register(@RequestBody @Valid AccountRegistrationData data) {
        System.out.println(data);
    }
}
