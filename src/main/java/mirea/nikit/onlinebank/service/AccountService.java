package mirea.nikit.onlinebank.service;

import lombok.RequiredArgsConstructor;
import mirea.nikit.onlinebank.model.Account;
import mirea.nikit.onlinebank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id){
        return accountRepository.findById(id).orElse(null);
    }

    public void createAccount(Account bankAccount) {
        accountRepository.save(bankAccount);
    }

    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }
}
