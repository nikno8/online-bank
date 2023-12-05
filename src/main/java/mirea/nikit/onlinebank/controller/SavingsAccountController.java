package mirea.nikit.onlinebank.controller;

import lombok.RequiredArgsConstructor;
import mirea.nikit.onlinebank.model.SavingsAccount;
import mirea.nikit.onlinebank.repository.SavingsAccountRepository;
import mirea.nikit.onlinebank.service.SavingsAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/savings-accounts")
@RequiredArgsConstructor
public class SavingsAccountController {
    private final SavingsAccountRepository savingsAccountRepository;
    private final SavingsAccountService savingsAccountService;
    @PostMapping("/open")
    public void openSavingsAccount(@RequestParam BigDecimal goal, @RequestParam Long account_id) {
        savingsAccountService.openSavingsAccount(goal, account_id);
    }

    @GetMapping("/get/{id}")
    public SavingsAccount getSavingsAccount(@PathVariable Long id) {
        return savingsAccountRepository.findById(id).orElse(null);
    }

    @PostMapping("/transfer/{accountId}")
    public ResponseEntity<String> transferToSavings(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        boolean success = savingsAccountService.transferToSavings(accountId, amount);

        if (success) {
            return ResponseEntity.ok("Transfer to savings successful. Bonus applied if goal reached.");
        } else {
            return ResponseEntity.badRequest().body("Insufficient funds or account not found.");
        }
    }

    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<String> withdrawFromSavings(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        boolean success = savingsAccountService.withdrawFromSavings(accountId, amount);

        if (success) {
            return ResponseEntity.ok("Withdrawal from savings successful.");
        } else {
            return ResponseEntity.badRequest().body("Insufficient funds or savings account not found.");
        }
    }

//    @PostMapping("/check-and-reward-half-goal/{accountId}")
//    public ResponseEntity<String> checkAndRewardForFullGoal(@PathVariable Long accountId) {
//        boolean success = savingsAccountService.checkAndRewardForFullGoal(accountId);
//
//        if (success) {
//            return ResponseEntity.ok("Reward for reaching 100% of savings goal claimed successfully.");
//        } else {
//            return ResponseEntity.badRequest().body("Either goal not reached or reward already claimed.");
//        }
//    }
}
