package fast.bank.api.controllers;

import fast.bank.api.domain.statement.dto.StatementDetailingData;
import fast.bank.api.domain.statement.repository.StatementRepository;
import fast.bank.api.domain.statement.service.list.ListAccStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statement")
public class StatementController {

    @Autowired
    private StatementRepository repository;

    @Autowired
    private ListAccStatementService listAccStatementService;

    @GetMapping("/{account}")
    public ResponseEntity<Page<StatementDetailingData>> list(@PathVariable Long account, @PageableDefault(size = 10, sort = {"id"}) Pageable pagination) {
        var page = listAccStatementService.list(account, pagination);
        return ResponseEntity.ok(page);
    }
}
