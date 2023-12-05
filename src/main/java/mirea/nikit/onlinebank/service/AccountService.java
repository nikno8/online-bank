package mirea.nikit.onlinebank.service;

import lombok.RequiredArgsConstructor;
import mirea.nikit.onlinebank.model.Account;
import mirea.nikit.onlinebank.model.User;
import mirea.nikit.onlinebank.repository.AccountRepository;
import mirea.nikit.onlinebank.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id){
        return accountRepository.findById(id).orElse(null);
    }

    public void createAccount(Account bankAccount) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = (User) authentication.getPrincipal();
//        bankAccount.setUser(currentUser);
//        currentUser.getAccounts().add(bankAccount);
//        userRepository.save(currentUser);
//        accountRepository.save(bankAccount);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        // Добавляем аккаунт в массив аккаунтов пользователя
        currentUser.getAccounts().add(bankAccount);

        // Устанавливаем связь между аккаунтом и пользователем
        bankAccount.setUser(currentUser);

        // Сохраняем пользователя и его аккаунты в базе данных
        userRepository.save(currentUser);
    }

    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public void addMoney(Long id, BigDecimal amount){
        Account account = accountRepository.findById(id).get();
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

}
