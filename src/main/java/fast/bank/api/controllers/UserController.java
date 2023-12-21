package fast.bank.api.controllers;

import fast.bank.api.domain.user.dto.UserDetailingData;
import fast.bank.api.domain.user.dto.UserRegistrationData;
import fast.bank.api.domain.user.repository.UserRepository;
import fast.bank.api.domain.user.service.UserService;
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
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<UserDetailingData> register(@RequestBody @Valid UserRegistrationData data, UriComponentsBuilder uriBuilder) {
        var user = userService.register(data);
        var uri = uriBuilder.path("api/v1/users/{id}").buildAndExpand(user.getRegistry()).toUri();
        return ResponseEntity.created(uri).body(new UserDetailingData(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserDetailingData>> list(@PageableDefault(size = 10, sort = {"registry"}) Pageable pagination) {
        var page = userRepository.findAllByIsActiveTrue(pagination).map(UserDetailingData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{registry}")
    public ResponseEntity listById(@PathVariable Long registry) {
        var user = userRepository.getReferenceById(registry);
        return ResponseEntity.ok(new UserDetailingData(user));
    }

    @DeleteMapping("/{registry}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long registry) {
        userService.delete(registry);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/activate/{registry}")
    @Transactional
    public ResponseEntity activate(@PathVariable Long registry) {
        var user = userService.activate(registry);
        return ResponseEntity.ok(user);
    }
}
