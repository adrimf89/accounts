package com.crud.sample.restful.api;

import com.crud.sample.restful.data.AccountFilterParam;
import com.crud.sample.restful.service.AccountService;
import com.crud.sample.restful.vo.AccountVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
@RequiredArgsConstructor
public class AccountAPI {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountVO>> findAll(@ModelAttribute final AccountFilterParam params) {
        return ResponseEntity.ok(accountService.findAll(params));
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody AccountVO account) {
        return ResponseEntity.ok(accountService.create(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        return ResponseEntity.of(accountService.findById(id));
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody AccountVO account) {
        AccountVO updated = accountService.update(account);

        if (updated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        accountService.delete(id);
        return ResponseEntity.ok().build();
    }
}
