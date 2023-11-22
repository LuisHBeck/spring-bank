package fast.bank.api.controllers;

import fast.bank.api.domain.user.dto.UserListData;
import fast.bank.api.domain.user.dto.UserRegistrationData;
import fast.bank.api.domain.user.model.User;
import fast.bank.api.domain.user.repository.UserRepository;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity<UserListData> register(@RequestBody @Valid UserRegistrationData data, UriComponentsBuilder uriBuilder) {
        var user = new User(data);
        repository.save(user);
        var uri = uriBuilder.path("api/v1/users/{id}").buildAndExpand(user.getRegistry()).toUri();
        return ResponseEntity.created(uri).body(new UserListData(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserListData>> list(@PageableDefault(size = 10, sort = {"registry"}) Pageable pagination) {
        var page = repository.findAllByIsActiveTrue(pagination).map(UserListData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{registry}")
    public ResponseEntity listById(@PathVariable Long registry) {
        var user = repository.getReferenceById(registry);
        return ResponseEntity.ok(new UserListData(user));
    }
}
