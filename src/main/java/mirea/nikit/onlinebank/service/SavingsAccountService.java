package mirea.nikit.onlinebank.service;

import lombok.RequiredArgsConstructor;
import mirea.nikit.onlinebank.model.Account;
import mirea.nikit.onlinebank.model.SavingsAccount;
import mirea.nikit.onlinebank.repository.AccountRepository;
import mirea.nikit.onlinebank.repository.SavingsAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SavingsAccountService {
    private final SavingsAccountRepository savingsAccountRepository;
    private final AccountRepository accountRepository;

    public void openSavingsAccount(BigDecimal goal, Long account_id) {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccount(accountRepository.findById(account_id).get());
        savingsAccount.setGoal(goal);
        savingsAccount.setBalance(BigDecimal.ZERO);
        savingsAccountRepository.save(savingsAccount);
    }

    @Transactional
    public boolean transferToSavings(Long accountId, BigDecimal amount) {
        Account mainAccount = accountRepository.findById(accountId).orElse(null);

        if (mainAccount != null && mainAccount.getBalance().compareTo(amount) >= 0) {
            SavingsAccount savingsAccount = mainAccount.getSavingsAccount();

            mainAccount.setBalance(mainAccount.getBalance().subtract(amount));
            savingsAccount.setBalance(savingsAccount.getBalance().add(amount));

            accountRepository.save(mainAccount);
            savingsAccountRepository.save(savingsAccount);
            return true;
        }

        return false;
    }

    @Transactional
    public boolean withdrawFromSavings(Long accountId, BigDecimal amount) {
        SavingsAccount savingsAccount = savingsAccountRepository.findById(accountId).orElse(null);

        if (savingsAccount != null && savingsAccount.getBalance().compareTo(amount) >= 0) {
            Account mainAccount = savingsAccount.getAccount();

            savingsAccount.setBalance(savingsAccount.getBalance().subtract(amount));
            mainAccount.setBalance(mainAccount.getBalance().add(amount));

            savingsAccountRepository.save(savingsAccount);
            accountRepository.save(mainAccount);
            return true;
        }

        return false;
    }

    @Transactional
    public boolean checkAndRewardForFullGoal(Long accountId) {
        SavingsAccount savingsAccount = savingsAccountRepository.findById(accountId).orElse(null);

        if (savingsAccount != null) {
            BigDecimal goal = savingsAccount.getGoal();
            BigDecimal currentBalance = savingsAccount.getBalance();

            if (currentBalance.compareTo(goal) > 0 && !savingsAccount.isFullGoalRewardClaimed()) {
                // Логика начисления бонуса на основной аккаунт
                Account mainAccount = savingsAccount.getAccount();
                BigDecimal bonusAmount = goal.multiply(new BigDecimal("0.05"));
                mainAccount.setBalance(mainAccount.getBalance().add(bonusAmount));
                accountRepository.save(mainAccount);
                // Устанавливаем флаг, чтобы бонус не начислялся повторно
                savingsAccount.setFullGoalRewardClaimed(true);
                savingsAccountRepository.save(savingsAccount);

                return true;
            }
        }

        return false;
    }
}
