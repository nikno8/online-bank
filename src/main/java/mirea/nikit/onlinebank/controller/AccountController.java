package mirea.nikit.onlinebank.controller;

import lombok.RequiredArgsConstructor;
import mirea.nikit.onlinebank.model.Account;
import mirea.nikit.onlinebank.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;


    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping
    public void createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccountById(id);
    }
}
